package com.synacy.poker.hand.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidStraightFlushException;
import com.synacy.poker.hand.types.StraightFlush;

public class StraightFlushCardHandHandler extends AbstractHandler {

	private static final Logger logger = LoggerFactory.getLogger(StraightFlushCardHandHandler.class);
	
	public StraightFlushCardHandHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	public Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> straightFlushCards = null;
		boolean isFlush = false;
		boolean isStraight = false;
				
		isFlush = isFlush();
		isStraight = isStraight();
		
		if (isStraight && isFlush) {
			int cardSuitIndex = getCardSuit();
			List<Integer> cardHandIndices = getBestHighCombination(getCardRankMapIndices());

			//check for steel wheel (five high straight flush)
			if (cardHandIndices.get(0) == CardRank.FIVE.ordinal()) {
				if (cardRankMap[CardRank.ACE.ordinal()] == 1) cardHandIndices.add(CardRank.ACE.ordinal());
			}
			
			straightFlushCards = cardHandIndices.stream()
								   .map(index -> {
									   return combinedCards.stream()
											   			   .filter(card -> card.getRank().ordinal() == index && 
											   			   					card.getSuit().ordinal() == cardSuitIndex)
											   			   .findFirst()
											   			   .get();
								   })
								   .collect(Collectors.toList());
		
			return new StraightFlush(straightFlushCards);
		} else {
			logger.debug("This is not a "+getHandType().toString());
			throw new InvalidStraightFlushException();
		}
	}
	
	private boolean isFlush() {
		return IntStream.range(0, cardSuitMap.length).anyMatch(value -> cardSuitMap[value] >= MAX_HAND_CARDS);
	}
	
	/**
	 * Identifies current hand if Straight
	 * 
	 * @return
	 */
	private boolean isStraight() {
		List<Integer> cardHandIndices = getBestHighCombination(getCardRankMapIndices());
		//TRUE if reaches max number of cards (5)
		boolean isStraight =  cardHandIndices.size() == MAX_HAND_CARDS;
		
		//if current hand is not empty and does not match MAX_HAND_CARDS(5) but is straight
		//check for Five High
		if (!cardHandIndices.isEmpty() && cardHandIndices.get(0) == CardRank.FIVE.ordinal()) {
			isStraight = true;
		} 
		
		return isStraight;
	}
	
	/**
	 * Gets the index of the card suit for flush
	 * @return
	 */
	private int getCardSuit() {
		return IntStream.range(0, cardSuitMap.length).filter(value -> cardSuitMap[value] != 0).findFirst().getAsInt();
	}
	
	/**
	 * Gets the indices of the best 5 cards combinations from the identified bit mapping
	 * @param cardRankIndexes
	 * @return
	 */
	private List<Integer> getBestHighCombination(List<Integer> cardRankIndexes) {
		List<Integer> bestCardCombination = new ArrayList<Integer>();
		
		for(int i = cardRankMap.length - 1; i >= 0; i--) {
			int currentCount = cardRankMap[i];
			int nextCount = 0;
			
			if (currentCount != 0) {
				
				bestCardCombination.add(i);	
				if ((i - 1) >= 0) {
					nextCount = cardRankMap[i - 1];
					if ((nextCount == 0 || nextCount > 1)&& bestCardCombination.size() < MAX_HAND_CARDS) {
						bestCardCombination.clear();
					}
				}
			}
			
		}
		
		return bestCardCombination.stream().limit(MAX_HAND_CARDS).collect(Collectors.toList());
	}

	@Override
	public HandType getHandType() {
		return HandType.STRAIGHT_FLUSH;
	}
	

}
