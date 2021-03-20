package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Three_of_a_kind">What is a Three of a Kind?</a>
 */
public class ThreeOfAKind extends Hand {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    public ThreeOfAKind(List<Card> threeOfAKindCards, List<Card> otherCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.THREE_OF_A_KIND;
    }

    /**
     * @return The name of the hand plus kickers in descending rank, e.g. Trips (4) - A,2 High
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Trips ");
    	builder.append("("+this.threeOfAKindCards.get(0).getRank().toString()+")");
    	builder.append(" - ");
    	builder.append(this.otherCards.get(0).getRank().toString());
    	builder.append(",");
    	builder.append(this.otherCards.get(1).getRank().toString());
    	builder.append(" High");
        return builder.toString();
    }

}
