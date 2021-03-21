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
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidFlushException;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FullHouse;

public class FlushCardHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);

	public FlushCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand identifyHand(List<Card> combinedCards) throws HandException {
		boolean isIdentifiable = false;
		List<Card> flushCards = null;

		//Get the index of the Suit to form a flush
		//e.g {5,2,0,0} 
		//corresponds to the ordinal of  CardSuit (e.g SPADE(0), CLUBS(1), etc)
		int indexOfSuit = IntStream.range(0, cardSuitMap.length)
								   .filter(index -> cardSuitMap[index] >= MAX_HAND_CARDS)
								   .findFirst()
								   .orElse(INDEX_NOT_FOUND);
								   
		if (indexOfSuit != INDEX_NOT_FOUND) {
			
			flushCards = combinedCards.stream()
					.filter(card -> {
						return card.getSuit().ordinal() == indexOfSuit;
					})
					.sorted((card1, card2) -> {
						return card2.getRank().ordinal() - card1.getRank().ordinal();
					})
					.collect(Collectors.toList());
				  
			return new Flush(flushCards);

		} else {
			logger.debug("This is not a "+getHandType().toString());
			throw new InvalidFlushException();
		}

	}
	
	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.FLUSH;
	}

}
