package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.OnePair;

public class OnePairComparator extends HandCompareStrategy {

	/**
	 * When a pair card's ordinal numbers sum is the same, then evaluate the kicker
	 * 
	 * @return Same as {@link Comparator}
	 * @see {@link Comparator}
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		OnePair op1 = (OnePair) o1;
		OnePair op2 = (OnePair) o2;

		int totalRankValueOfOp1 = op1.getPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
		int totalRankValueOfOp2 = op2.getPairCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();

		if (totalRankValueOfOp1 == totalRankValueOfOp2) {
			int totalValueOfKickersOfOp1 = op1.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal())
					.sum();
			int totalValueOfKickersOfOp2 = op2.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal())
					.sum();
			return totalValueOfKickersOfOp2 - totalValueOfKickersOfOp1;
		}

		return totalRankValueOfOp2 - totalRankValueOfOp1;
	}

}
