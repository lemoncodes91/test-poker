package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Full_house">What is a Full House?</a>
 */
public class FullHouse extends Hand {

	private List<Card> threeOfAKindCards;
    private List<Card> pairCards;

    public FullHouse(List<Card> threeOfAKindCards, List<Card> pairCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.pairCards = pairCards;
    }

    public HandType getHandType() {
        return HandType.FULL_HOUSE;
    }

    /**
     * @return The name of the hand with rank of the three pair and two pair, e.g.
     * 444AA - Full House (4,A)
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Full House ");
    	builder.append("(");
    	builder.append(this.threeOfAKindCards.get(0).getRank().toString());
    	builder.append(",");
    	builder.append(this.pairCards.get(0).getRank().toString());
    	builder.append(")");
    	
        return builder.toString();
    }
    
	@Override
	public int getCardValues() {
		return Stream.of(this.threeOfAKindCards, this.pairCards)
					 .flatMap(Collection::stream)
					 .mapToInt(card -> card.getRank().ordinal()).sum();
	}


    public List<Card> getThreeOfAKindCards() {
		return threeOfAKindCards;
	}

	public List<Card> getPairCards() {
		return pairCards;
	}
	
}
