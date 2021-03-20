package com.synacy.poker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.handlers.HandTypeHandler;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

/**
 * Service responsible for handling hand identification and hand winning calculations
 * 
 * @author mikram
 *
 */
@Service
public class CardHandService {


	/**
	 * Identifies the hand (e.g {@link FullHouse}, {@link Straight}, etc)
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return {@link Hand}
	 */
	public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {
		return HandTypeHandler.getHandlers().handle(playerCards, communityCards);
	} 
	
	/**
	 * Calculates the winning hand between players
	 * @return
	 */
	public Hand identifyWinningHand(List<Hand> playerHands) {
		return null;
	}
}
