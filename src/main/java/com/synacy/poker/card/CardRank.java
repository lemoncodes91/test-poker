package com.synacy.poker.card;

/**
 * The rank of a {@link Card} from <em>2</em> to <em>Ace</em>. No jokers.
 */
public enum CardRank {

	TWO("2", 13), THREE("3", 12), FOUR("4", 11), FIVE("5", 10), SIX("6", 9),
	SEVEN("7", 8), EIGHT("8", 7), NINE("9", 6), TEN("10", 5),
	JACK("J", 4), QUEEN("Q", 3), KING("K", 2), ACE("A", 1);

	private String value;
	private int priority;

	CardRank(String value, int priority) {
		this.value = value;
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return value;
	}

}
