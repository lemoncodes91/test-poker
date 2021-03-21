package com.synacy.poker.hand.handlers;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.synacy.poker.hand.Hand;

@Component
public class HandComparator implements Comparator<Hand> {

	@Override
	public int compare(Hand o1, Hand o2) {
		int compVal = o2.getHandType().ordinal() - o1.getHandType().ordinal();
		boolean isSameHandType = compVal == 0;
		
		//When HandType is the same
		//examining for kickers
		if (isSameHandType) {
			compVal = o2.getCardValues() - o1.getCardValues();
		}
		
		return compVal;
	}
	
}
