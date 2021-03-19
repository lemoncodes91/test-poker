package com.synacy.poker.hand.handlers;

import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.HighCard;

public class HighCardHandler extends AbstractHandler {

	public HighCardHandler(AbstractHandler next) {
		super(next);
	}
	
	/**
	 * Handler for High Card hand combination
	 */
	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		
		//Since this is the least hand in poker
		//combination is already filtered up in the chain
		//when it reaches this handler, it is already assumed that 
		//this is the worst hand (no combination, etc)
		//get only the first 5 high cards,
		List<Card> highCards = getBestFiveCardCombination(playerCards, communityCards);
		
		if (next != null) {
			return next.handle(playerCards, communityCards);
		}
		
		return new HighCard(highCards);
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.HIGH_CARD;
	}

}
