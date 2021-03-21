package com.synacy.poker.hand.comparators;

import com.synacy.poker.hand.Hand;

public abstract class HandCompareStrategy {
	
	public abstract int compare(Hand o1, Hand o2);
	

}
