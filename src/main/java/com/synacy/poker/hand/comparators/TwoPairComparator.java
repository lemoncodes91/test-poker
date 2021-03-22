package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.TwoPair;

public class TwoPairComparator extends HandCompareStrategy {
	
	private final static int EMPTY_CARD = 0;

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

			//if 2nd pair has equal values 
			//check for the kicker, highest ranking kicker wins.
			if (rankValueSecondPairOfTp1 == rankValueSecondPairOfTp2) {
				int kickerOrindalOfTp1 = !tp1.getOtherCards().isEmpty() ? 
											tp1.getOtherCards().get(0).getRank().ordinal() : EMPTY_CARD;
				int kickerOrindalOfTp2 = !tp2.getOtherCards().isEmpty() ? 
											tp2.getOtherCards().get(0).getRank().ordinal() : EMPTY_CARD;
				return kickerOrindalOfTp2 - kickerOrindalOfTp1;
			}

			return rankValueSecondPairOfTp2 - rankValueSecondPairOfTp1;
		}

		return rankValueFirstPartOfTp2 - rankValueFirstPartOfTp1;
	}

}
