package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight">What is a Straight?</a>
 */
public class Straight extends Hand {

    private List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.STRAIGHT;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return The name of the hand and the high card, e.g. Straight (A High)
     */
    @Override
    public String toString() {
    	StringBuilder builder  = new StringBuilder();
    	builder.append("Straight ");
    	builder.append("(");
    	builder.append(this.cards.get(0).getRank().toString());
    	builder.append(" High");
    	builder.append(")");
        return builder.toString();
    }

	@Override
	public int getCardValues() {
		return this.cards.stream().mapToInt(card -> card.getRank().ordinal()).sum();
	}
}
