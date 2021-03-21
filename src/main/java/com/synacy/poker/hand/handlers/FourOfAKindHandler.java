package com.synacy.poker.hand.handlers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidFourOfAKindException;
import com.synacy.poker.hand.types.FourOfAKind;

public class FourOfAKindHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	private static final int QUADS = 4;
	
	public FourOfAKindHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	public Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> fourOfAKind = null;
		List<Card> otherCards = null;

		List<Integer> cardIndices = getCardRankMapIndices();
		
		//Find the index of the bitmap  that has QUADS(4) value
		//e.g {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0}
		// index is 7, has QUADS (4)
		int indexWithQuads = cardIndices.stream()
				                        .filter(index -> {
				                        	return cardRankMap[index] == QUADS;
				                        })
				                        .findFirst()
				                        .orElse(INDEX_NOT_FOUND);

		if (indexWithQuads != INDEX_NOT_FOUND) {
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
		}else {
			logger.debug("This is not a " + getHandType().toString());
			throw new InvalidFourOfAKindException();
		}
	}
	
	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.FOUR_OF_A_KIND;
	}

}
