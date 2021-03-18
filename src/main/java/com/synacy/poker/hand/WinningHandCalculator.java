package com.synacy.poker.hand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synacy.poker.services.CardHandService;

import java.util.List;
import java.util.Optional;

/**
 * A service class used to calculate the winning hand.
 */
@Component
public class WinningHandCalculator {

	@Autowired
	private CardHandService cardHandService;
	
	/**
	 * @param playerHands
	 * @return The winning {@link Hand} from a list of player hands.
	 */
	public Optional<Hand> calculateWinningHand(List<Hand> playerHands) {
		//TODO
		//return Optional.of(cardHandService.identifyWinningHand(playerHands));
		return Optional.empty();
	}

}
