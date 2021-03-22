package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Flush">What is a flush?</a>
 */
public class Flush extends Hand {

    private List<Card> cards;

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.FLUSH;
    }

    /**
     * @return Returns the name of the hand and the highest card, e.g. Flush (K High)
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Flush ");
    	builder.append("(");
    	builder.append(getCards().get(0).getRank().toString());
    	builder.append(" High)");
        return builder.toString();
    }
    
	@Override
	public int getCardValues() {
		return this.cards.stream().mapToInt(card -> card.getRank().ordinal()).sum();
	}

    public List<Card> getCards() {
        return cards;
    }
}
