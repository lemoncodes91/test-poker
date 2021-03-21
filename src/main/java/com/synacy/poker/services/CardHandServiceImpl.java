package com.synacy.poker.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandComparator;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.HandTypeHandler;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

/**
 * Service responsible for handling hand identification and hand winning
 * calculations
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
	 * Calculates the winning hand between players Sorted initial via
	 * {@link HandType} ordinals to determine the winning hand. In cases of same
	 * {@link HandType}, Kickers will be considered for calculating best hand. For
	 * actual tie (same {@link HandType} and kickers) both will be winners and pot
	 * will be split
	 * 
	 * @return list of hand that have won or have tied. In cases where only 1 hand
	 *         is the winner, list will have only 1 element. However, when there is
	 *         two winners (tie), both hand will be returned
	 * 
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Texas_hold_%27em#Kickers_and_ties">Kickers
	 *      and Tie</</a>
	 */
	@Override
	public Optional<List<Hand>> identifyWinningHand(List<Hand> playerHands) {
		// Sorted according to winning hand -> losing hand
		List<Hand> sortedHands = playerHands.stream().sorted(comparator).collect(Collectors.toList());
		// first element will always be the winning hand
		Hand winHand = sortedHands.get(0);
		// find other winning hand (for tie)
		List<Hand> winningHands = sortedHands.stream().filter(hand -> {
			return (hand.getHandType() == winHand.getHandType()) && (hand.getCardValues() == winHand.getCardValues());
		}).collect(Collectors.toList());
		return Optional.of(winningHands);
	}
}
