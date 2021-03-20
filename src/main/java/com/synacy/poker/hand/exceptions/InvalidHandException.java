package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public class InvalidHandException extends HandException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card is not yet identifiable ");
		
		return builder.toString();
	}

	@Override
	protected HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.INVALID_HAND;
	}
}
