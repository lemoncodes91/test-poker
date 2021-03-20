package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidHandException extends HandException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public InvalidHandException(HandType handType) {
		super(handType);
	}
}
