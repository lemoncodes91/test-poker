package com.synacy.poker.hand.processors;

import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;

public abstract class HandValueProcessor {

	public final static int MAX_HAND_CARDS = 5;
	public HandValueProcessor next;
	
	public HandValueProcessor(HandValueProcessor next) {
		this.next = next;
	}
	
	/**
	 * Abstract process will be defined per processor`
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public abstract Hand process(List<Card> playerCards, List<Card> communityCards);
}
