package com.synacy.poker.hand.handlers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidFlushException;
import com.synacy.poker.hand.types.Flush;

public class FlushCardHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	private static final int MAX_RANK_PER_CARD = 1;
	private static final int MAX_NUMBER_OF_NON_UNIQUE = 0;
	
	public FlushCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> flushCards = null;

		//Get the index of the Suit to form a flush
		//e.g {5,2,0,0} 
		//corresponds to the ordinal of  CardSuit (e.g SPADE(0), CLUBS(1), etc)
		int indexOfSuit = IntStream.range(0, cardSuitMap.length)
								   .filter(index -> cardSuitMap[index] >= MAX_HAND_CARDS)
								   .findFirst()
								   .orElse(INDEX_NOT_FOUND);
		
		//Determines that ranks should be uniques and no pairs, trips or quads should be present 
		//in the hand. 
		//e.g {0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 1, 1, 0} <-- returns 1 (four of a kind)
		//e.g {0, 0, 0, 0, 1, 0, 2, 2, 0, 1, 1, 0, 1} <-- returns 2 (two pairs)
		//e.g {0, 0, 0, 0, 0, 0, 3, 2, 0, 1, 1, 0, 0} <-- returns 2 (full house)
		//e.g {0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 0, 0} <-- returns 1 (three of a kind)
		//e.g {0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 0} <-- returns 2 (one pair)
		//e.g {0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0} <-- returns 0 (all occurence is 1)
		long indexOfNonUnqiueOrdinal =  IntStream.range(0, cardRankMap.length)
												.filter(index -> cardRankMap[index] > MAX_RANK_PER_CARD)
												.count();
								    
		if (indexOfSuit != INDEX_NOT_FOUND && indexOfNonUnqiueOrdinal == MAX_NUMBER_OF_NON_UNIQUE) {
			
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
