package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#High_card">What is a High Card?</a>
 */
public class HighCard extends Hand {

    private List<Card> cards;

    public HighCard(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.HIGH_CARD;
    }

    /**
     * @return The cards ordered by descending rank, e.g. A,K,Q,3,2
     */
    @Override
    public String toString() {
    	return this.cards.stream()
							 // sort card rank by priority
							 .sorted((card1, card2) -> card2.getRank().ordinal() - card1.getRank().ordinal())
    						 .map(card -> {
    							 return card.getRank().toString();
    						 })
    						 .collect(Collectors.joining(","));
    }
    
	@Override
	public int getCardValues() {
		return this.cards.stream().mapToInt(card -> card.getRank().ordinal()).sum();
	}

}
