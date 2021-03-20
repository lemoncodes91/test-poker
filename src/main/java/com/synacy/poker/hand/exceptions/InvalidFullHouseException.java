package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidFullHouseException extends HandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public InvalidFullHouseException(HandType handType) {
		super(HandType.FULL_HOUSE);
		// TODO Auto-generated constructor stub
	}
	

}
