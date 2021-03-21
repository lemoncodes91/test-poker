package com.synacy.poker.hand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.synacy.poker.services.CardHandService;

/**
 * A service class used to calculate the winning hand.
 */
@Component
public class WinningHandCalculator {

	@Autowired
	@Qualifier("MikramPokerHandlingService-1")
	CardHandService cardService;

	/**
	 * @param playerHands
	 * @return The winning {@link Hand} from a list of player hands.
	 */
	public Optional<List<Hand>> calculateWinningHand(List<Hand> playerHands) {
		return cardService.identifyWinningHand(playerHands);
	}

}
