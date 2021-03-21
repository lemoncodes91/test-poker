package com.synacy.poker.hand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.synacy.poker.card.Card;
import com.synacy.poker.services.CardHandService;

/**
 * A service that is to used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Component
public class HandIdentifier {
	
	@Autowired
	@Qualifier("MikramPokerHandlingService-1")
	private CardHandService cardHandService;
	
    /**
     * Given the player's cards and the community cards, identifies the player's hand.
     *
     * @param playerCards
     * @param communityCards
     * @return The player's {@link Hand} or `null` if no Hand was identified.
     */
    public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {
        return cardHandService.identifyHand(playerCards, communityCards);
    }

}
