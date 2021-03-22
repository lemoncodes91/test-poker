package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.Flush;

public class FlushComparator extends HandCompareStrategy {

	private final static int INVALID_NUMBER = 0;
	
	@Override
	public int compare(Hand o1, Hand o2) {
		Flush flushHand1 = (Flush) o1;
		Flush flushHand2 = (Flush) o2;
		
		int hand1HighestCard = flushHand1.getCards()
				.stream()
				.map(card -> card.getRank().ordinal())
				.max(Comparator.comparing(ordinal -> ordinal))
				.orElse(INVALID_NUMBER);
		
		int hand2HighestCard = flushHand2.getCards()
				.stream()
				.map(card -> card.getRank().ordinal())
				.max(Comparator.comparing(ordinal -> ordinal))
				.orElse(INVALID_NUMBER);
		
		return hand2HighestCard - hand1HighestCard;
	}
}
