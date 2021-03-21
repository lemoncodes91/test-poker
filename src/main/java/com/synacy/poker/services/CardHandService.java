package com.synacy.poker.services;

import java.util.List;
import java.util.Optional;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

public interface CardHandService {
	
	/**
	 * Identifies the hand (e.g {@link FullHouse}, {@link Straight}, etc)
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return {@link Hand}
	 */
	public Hand identifyHand(List<Card> playerCards, List<Card> communityCards);
	
	/**
	 * Calculates the winning hand between players
	 * @return
	 */
	public Optional<List<Hand>> identifyWinningHand(List<Hand> playerHands);

}
