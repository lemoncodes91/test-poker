package com.synacy.poker.hand.comparators;

import java.util.Comparator;

import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.ThreeOfAKind;

public class ThreeOfAKindComparator extends HandCompareStrategy {
	
	/**
	 * When Triplets card's ordinal numbers sum is the same, then evaluate the
	 * kicker
	 * 
	 * @return Same as {@link Comparator}
	 * @see {@link Comparator}
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		ThreeOfAKind trips1 = (ThreeOfAKind) o1;
		ThreeOfAKind trips2 = (ThreeOfAKind) o2;

		int rankValueOfTrips1 = trips1.getThreeOfAKindCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();
		int rankValueOfTrips2 = trips2.getThreeOfAKindCards().stream().mapToInt(card -> card.getRank().ordinal()).sum();

		if (rankValueOfTrips1 == rankValueOfTrips2) {
			
			long countAceTrips1 = trips1.getOtherCards().stream()
													 	 .map(card -> card.getRank())
													 	 .filter(ordinal -> ordinal == CardRank.ACE)
													 	 .count();
			
			long countAceTrips2 = trips2.getOtherCards().stream()
										 	 .map(card -> card.getRank())
										 	 .filter(ordinal -> ordinal == CardRank.ACE)
										 	 .count();
			
			if (countAceTrips1 == 0 && countAceTrips2 == 0) {
				
				int rankValueKickerTrips1 = trips1.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal())
						.sum();
				int rankValueKickerTrips2 = trips2.getOtherCards().stream().mapToInt(card -> card.getRank().ordinal())
						.sum();

				return rankValueKickerTrips2 - rankValueKickerTrips1;
			} else {
				return Long.valueOf(countAceTrips2).intValue() - Long.valueOf(countAceTrips1).intValue();
			}
		}

		return rankValueOfTrips2 - rankValueOfTrips1;
	}

}
