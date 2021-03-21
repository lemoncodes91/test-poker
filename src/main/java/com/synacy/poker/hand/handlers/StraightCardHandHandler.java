package com.synacy.poker.hand.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidStraightException;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

public class StraightCardHandHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	
	public StraightCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> straightCards = null;
		boolean isStraight = false;
		List<Integer> cardHandIndices = getBestHighCombination(getCardRankMapIndices());
			
		
		if (isStraight(cardHandIndices)) {
			

			//check for steel wheel (five high straight flush)
			if (cardHandIndices.get(0) == CardRank.FIVE.ordinal()) {
				if (cardRankMap[CardRank.ACE.ordinal()] == 1) cardHandIndices.add(CardRank.ACE.ordinal());
			}
			
			straightCards = cardHandIndices.stream()
						   .map(index -> {
							   return combinedCards.stream()
									   			   .filter(card -> card.getRank().ordinal() == index)
									   			   .findFirst()
									   			   .get();
						   })
						   .collect(Collectors.toList());
			
			
	
			
			return new Straight(straightCards);
		} else {
			throw new InvalidStraightException();
		}
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.STRAIGHT;
	}

	/**
	 * Identifies current hand if Straight
	 * 
	 * @return
	 */
	private boolean isStraight(List<Integer> cardHandIndices) {
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
}
