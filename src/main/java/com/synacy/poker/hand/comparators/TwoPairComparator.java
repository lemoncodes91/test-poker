package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.TwoPair;

public class TwoPairComparator extends HandCompareStrategy {

	/**
	 * When 1st pair card's ordinal numbers sum is the same, evaluate the next pair
	 * cards, and if it is still the same then evaluate the kicker
	 * 
	 * @return Same as {@link Comparator}
	 * @see {@link Comparator}
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		TwoPair tp1 = (TwoPair) o1;
		TwoPair tp2 = (TwoPair) o2;

		int rankValueFirstPartOfTp1 = tp1.getFirstPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
		int rankValueFirstPartOfTp2 = tp2.getFirstPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();

		if (rankValueFirstPartOfTp1 == rankValueFirstPartOfTp2) {
			int rankValueSecondPairOfTp1 = tp1.getSecondPairCards().stream().mapToInt(card -> card.getRank().ordinal())
					.sum();
			int rankValueSecondPairOfTp2 = tp2.getSecondPairCards().stream().mapToInt(card -> card.getRank().ordinal())
					.sum();

			if (rankValueSecondPairOfTp1 == rankValueSecondPairOfTp2) {
				int kickerOfTp1 = tp1.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
				int kickerOfTp2 = tp2.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();

				return kickerOfTp2 - kickerOfTp1;
			}

			return rankValueSecondPairOfTp2 - rankValueSecondPairOfTp1;
		}

		return rankValueFirstPartOfTp2 - rankValueFirstPartOfTp1;
	}

}
