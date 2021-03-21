package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#One_pair">What is a One Pair?</a>
 */
public class OnePair extends Hand {

    private List<Card> pairCards;
    private List<Card> otherCards;

    public OnePair(List<Card> pairCards, List<Card> otherCards) {
        this.pairCards = pairCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.ONE_PAIR;
    }

    /**
     * @return The name of the hand plus kickers ordered by descending rank, e.g. One Pair (2) - A,K,Q High,
     * or the name of the hand and rank if there are no community cards yet in play, e.g. One Pair (2)
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("One Pair ");
    	builder.append("("+this.pairCards.get(0).getRank().toString()+")");
    	
    	if (!otherCards.isEmpty()) {
    		builder.append(" - ");
    		builder.append(String.join(",", otherCards.stream()
							   				 .map(card -> card.getRank().toString())
							   				 .collect(Collectors.toList()))); 
    		builder.append(" High");
    	}
    	
        return builder.toString();
    }
    
	@Override
	public int getCardValues() {
		return Stream.of(this.pairCards, this.otherCards)
					 .flatMap(Collection::stream)
					 .mapToInt(card -> {
						 return card.getRank().ordinal();
					 }).sum();
	}

}
