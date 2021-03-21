package com.synacy.poker.hand.comparators;

import com.synacy.poker.hand.Hand;

public class SimpleCardRankComparator extends HandCompareStrategy {

	@Override
	public int compare(Hand o1, Hand o2) {
		// TODO Auto-generated method stub
		return o2.getCardValues() - o1.getCardValues();
	}

}
