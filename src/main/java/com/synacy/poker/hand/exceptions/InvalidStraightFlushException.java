package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidStraightFlushException extends HandException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public InvalidStraightFlushException(HandType handType) {
		super(HandType.STRAIGHT_FLUSH);
		// TODO Auto-generated constructor stub
	}
	
	private HandType getHandType() {
		return HandType.STRAIGHT_FLUSH;
	}
	
}
