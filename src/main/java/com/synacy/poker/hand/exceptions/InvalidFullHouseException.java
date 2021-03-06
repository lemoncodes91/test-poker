package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidFullHouseException extends HandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public HandType getHandType() {
		return HandType.FULL_HOUSE;
	}

}
