package com.synacy.poker.hand.exceptions;

import com.synacy.poker.hand.HandType;

public abstract class HandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected abstract HandType getHandType();
	
	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card is not a ");
		builder.append(getHandType().toString());
		
		return builder.toString();
	}
}
