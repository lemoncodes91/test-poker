package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FullHouse;

public class FullHouseComparator extends HandCompareStrategy {

	/**
	 * When a trips card's ordinal numbers sum is the same, then evaluate the pair
	 * 
	 * @return Same as {@link Comparator}
	 * @see {@link Comparator}
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		FullHouse fh1 = (FullHouse) o1;
		FullHouse fh2 = (FullHouse) o2;
		
		int rankValueFirstTripsFh1 = fh1.getThreeOfAKindCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
		int rankValueFirstTripsFh2 = fh2.getThreeOfAKindCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
		
		if (rankValueFirstTripsFh1 == rankValueFirstTripsFh2) {
			int rankValueOfSecondPairFh1 = fh1.getPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
			int rankValueOfSecondPairFh2 = fh2.getPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
			
			return rankValueOfSecondPairFh2 - rankValueOfSecondPairFh1;
		}
		
		
		return rankValueFirstTripsFh2 - rankValueFirstTripsFh1;
	}

}
