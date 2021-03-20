package com.synacy.poker.hand.handlers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FullHouse;

public class FlushCardHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);

	public FlushCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		logger.info("Start handling for " + getHandType().toString());
		boolean isIdentifiable = false;
		List<Card> flushCards = null;
		

		// process logic here
		mapCardHand(playerCards, communityCards);

		isIdentifiable = isCardHandIdentifieable();

		// if combination card can be processed
		// (e.g player's cards + comunity's cards >= 5 cards)
		if (isIdentifiable) {
			//Get the index of the Suit to form a flush
			//e.g {5,2,0,0} 
			//corresponds to the ordinal of  CardSuit (e.g SPADE(0), CLUBS(1), etc)
			int indexOfSuit = IntStream.range(0, cardSuitMap.length)
									   .filter(index -> cardSuitMap[index] == MAX_HAND_CARDS)
									   .findFirst()
									   .orElse(INDEX_NOT_FOUND);
									   
			if (indexOfSuit != INDEX_NOT_FOUND) {
				List<Card> combined = Stream.of(playerCards, communityCards)
										  .flatMap(Collection::stream)
										  .collect(Collectors.toList());
				
				flushCards = combined.stream()
						.filter(card -> {
							return card.getSuit().ordinal() == indexOfSuit;
						})
						.sorted((card1, card2) -> {
							return card2.getRank().ordinal() - card1.getRank().ordinal();
						})
						.collect(Collectors.toList());
					  
				return new Flush(flushCards);

			} else {
				StringBuilder builder = new StringBuilder();
				builder.append("Card is not a Full House, ");

				if (next != null) {
					builder.append("trying next handler - ");
					builder.append(next.getClass().getName());
				} else {
					builder.append("no more tries.");
				}

				logger.debug(builder.toString());
			}

		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("Card is not yet identifiable as Full House, ");

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
		return HandType.FLUSH;
	}

}
