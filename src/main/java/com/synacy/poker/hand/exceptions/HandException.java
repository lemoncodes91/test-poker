package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public abstract class HandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HandType type;
	
	public HandException(HandType handType) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card is not yet identifiable as ");
		builder.append(type.toString());
		
		return builder.toString();
	}
}
