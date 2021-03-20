package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidTwoPairException extends HandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.TWO_PAIR;
	}

}
