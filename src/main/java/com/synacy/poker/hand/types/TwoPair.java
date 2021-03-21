package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Two_pair">What is a Two Pair?</a>
 */
public class TwoPair extends Hand {

	private List<Card> firstPairCards;
    private List<Card> secondPairCards;
    private List<Card> otherCards;

    public TwoPair(List<Card> firstPairCards, List<Card> secondPairCards, List<Card> otherCards) {
        this.firstPairCards = firstPairCards;
        this.secondPairCards = secondPairCards;
        this.otherCards = otherCards;
    }
	
    /**
     * @return The name of the hand with kicker ranked in descending order, e.g. Two Pair (4,3) - A High
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Two Pair ");
    	builder.append("(");
    	builder.append(this.firstPairCards.get(0).getRank().toString());
    	builder.append(",");
    	builder.append(this.secondPairCards.get(0).getRank().toString());
    	builder.append(")");
    	builder.append(" - ");
    	builder.append(this.otherCards.get(0).getRank().toString());
    	builder.append(" High");
        return builder.toString();
    }

	@Override
	public int getCardValues() {
		return Stream.of(this.firstPairCards, this.secondPairCards, this.otherCards)
					 .flatMap(Collection::stream)
					 .mapToInt(card -> card.getRank().ordinal()).sum();
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.TWO_PAIR;
	}
	
}
