package com.synacy.poker.hand.handlers;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.FourOfAKind;

public class FourOfAKindHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	private static final int QUADS = 4;
	
	public FourOfAKindHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		logger.info("Start handling for "+ getHandType().toString());
		boolean isIdentifiable = false;
		List<Card> combinedCards = null;
		List<Card> fourOfAKind = null;
		List<Card> otherCards = null;
		
		mapCardHand(playerCards, communityCards);
		
		
		isIdentifiable = isCardHandIdentifieable();
		
		// if combination card can be processed  
		// (e.g player's cards + comunity's cards >= 5 cards)
		if (isIdentifiable) {
			
			List<Integer> cardIndices = getCardRankMapIndices();
			
			//Find the index of the bitmap  that has QUADS(4) value
			//e.g {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0}
			// index is 7, has QUADS (4)
			int indexWithQuads = cardIndices.stream()
					                        .filter(index -> {
					                        	return cardRankMap[index] == QUADS;
					                        })
					                        .findFirst()
					                        .orElse(0);

			if (indexWithQuads != 0) {
				/*
				 * Find the highest available rank on hand
				 * e.g {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0}
				 * index is 10 
				 */
				int indexKicker = cardIndices.stream()
											 .sorted(Comparator.reverseOrder())
											 .filter(index -> index != indexWithQuads)
											 .findFirst()
											 .orElse(0);
				//combines player and community card
				combinedCards = Stream.of(playerCards, communityCards)
									.flatMap(Collection::stream)
									.collect(Collectors.toList());
				
				//find the four of a kind in the combinedcards
				fourOfAKind = combinedCards.stream()
										   .filter(card -> {
											   return card.getRank().ordinal() == indexWithQuads;
										   })
										   .collect(Collectors.toList());
				
				//find the kicker in the combinedcards				
				otherCards = combinedCards.stream()
										   .filter(card -> {
											   return card.getRank().ordinal() == indexKicker;
										   })
										   .collect(Collectors.toList());
				
				return new FourOfAKind(fourOfAKind, otherCards);
			} else {
				StringBuilder builder = new StringBuilder();
				builder.append("Card is not a Four of a Kind, ");
				
				if (next != null) {
					builder.append("trying next handler - ");
					builder.append(next.getClass().getName());
				} else {
					builder.append("no more tries.");
				}
				
				logger.debug(builder.toString());
			}
		}else {
			StringBuilder builder = new StringBuilder();
			builder.append("Card is not yet identifiable as Four of a Kind, ");
			
			if (next != null) {
				builder.append("trying next handler - ");
				builder.append(next.getClass().getName());
			} else {
				builder.append("no more tries.");
			}
			
			logger.debug(builder.toString());
		}
		
		return next(playerCards, communityCards);
	}
	
	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.FOUR_OF_A_KIND;
	}

}
