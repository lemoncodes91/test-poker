package com.synacy.poker.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.handlers.HandComparator;
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
@Qualifier("MikramPokerHandlingService-1")
public class CardHandServiceImpl implements CardHandService {


	@Autowired
	private HandComparator comparator;

	/**
	 * Identifies the hand (e.g {@link FullHouse}, {@link Straight}, etc)
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return {@link Hand}
	 */
	@Override
	public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {
		return HandTypeHandler.getHandlers().handle(playerCards, communityCards);
	} 
	
	/**
	 * Calculates the winning hand between players
	 * @return
	 */
	@Override
	public Optional<List<Hand>> identifyWinningHand(List<Hand> playerHands) {
		//Sorted according to winning hand -> losing hand
		List<Hand> sortedHands = playerHands.stream().sorted(comparator).collect(Collectors.toList());
		//first element will always be the winning hand
		Hand winHand = sortedHands.get(0);
		//find other winning hand (for tie)
		List<Hand> winningHands = sortedHands.stream()
											.filter(hand -> {
												return  (hand.getHandType() == winHand.getHandType()) &&
														 (hand.getCardValues() == winHand.getCardValues());
											})
											.collect(Collectors.toList());
		return Optional.of(winningHands);
	}
}
