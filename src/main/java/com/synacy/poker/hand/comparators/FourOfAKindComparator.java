package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FourOfAKind;

public class FourOfAKindComparator extends HandCompareStrategy {

	/**
	 * When Quads card's ordinal numbers sum is the same, then evaluate the kicker
	 * 
	 * @return Same as {@link Comparator}
	 * @see {@link Comparator}
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		FourOfAKind fourOfAKind1 = (FourOfAKind) o1;
		FourOfAKind fourOfAKind2 = (FourOfAKind) o2;

		int rankValueofFourOfAKind1 = fourOfAKind1.getFourOfAKindCards().stream()
				.mapToInt(card -> card.getRank().ordinal()).sum();
		int rankValueofFourOfAKind2 = fourOfAKind2.getFourOfAKindCards().stream()
				.mapToInt(card -> card.getRank().ordinal()).sum();

		if (rankValueofFourOfAKind1 == rankValueofFourOfAKind2) {
			int kickerOfFourOfAKind1 = fourOfAKind1.getOtherCards().get(0).getRank().ordinal();
			int kickerOfFourOfAKind2 = fourOfAKind2.getOtherCards().get(0).getRank().ordinal();

			return kickerOfFourOfAKind2 - kickerOfFourOfAKind1;
		}

		return rankValueofFourOfAKind2 - rankValueofFourOfAKind1;
	}

}
