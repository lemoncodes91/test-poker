package com.synacy.poker.hand;

import com.synacy.poker.hand.identifiers.AbstractHandIdentifier;
import com.synacy.poker.hand.identifiers.FlushHandIdentifier;
import com.synacy.poker.hand.identifiers.FourOfAKindHandIdentifier;
import com.synacy.poker.hand.identifiers.FullHouseIdentifier;
import com.synacy.poker.hand.identifiers.HighCardIdentifier;
import com.synacy.poker.hand.identifiers.OnePairIdentifier;
import com.synacy.poker.hand.identifiers.StraightFlushIdentifier;
import com.synacy.poker.hand.identifiers.StraightIdentifier;
import com.synacy.poker.hand.identifiers.ThreeOfAKindIdentifier;
import com.synacy.poker.hand.identifiers.TwoPairsIdentifier;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.StraightFlush;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;

public class HandTypeHandler {

	private static AbstractHandIdentifier processor = null;

	/**
	 * Creates the chain of Hand Identifiers or handlers <br/>
	 * <br/>
	 * Cards will be passed down the chain starting from {@link StraightFlush} up to
	 * {@link HighCard}. <br/>
	 * <br/>
	 * 
	 * Chain: <br/>
	 * <ol>
	 * <li>{@link StraightFlush}</li>
	 * <li>{@link FourOfAKind}</li>
	 * <li>{@link FullHouse}</li>
	 * <li>{@link Flush}</li>
	 * <li>{@link Straight}</li>
	 * <li>{@link ThreeOfAKind}</li>
	 * <li>{@link TwoPair}</li>
	 * <li>{@link OnePair}</li>
	 * <li>{@link HighCard}</li>
	 * </ol>
	 * <br/>
	 * <br/>
	 * 
	 */
	private static void init() {
		// This is the very tip of the chain (bottom)
		// terminate the chain with null
		HighCardIdentifier highCard = new HighCardIdentifier(null);

		// This is the middle processors
		OnePairIdentifier onePair = new OnePairIdentifier(highCard);
		TwoPairsIdentifier twoPairs = new TwoPairsIdentifier(onePair);
		ThreeOfAKindIdentifier threeOfAKind = new ThreeOfAKindIdentifier(twoPairs);
		StraightIdentifier straight = new StraightIdentifier(threeOfAKind);
		FlushHandIdentifier flush = new FlushHandIdentifier(straight);
		FullHouseIdentifier fullHouse = new FullHouseIdentifier(flush);
		FourOfAKindHandIdentifier fourOfAKind = new FourOfAKindHandIdentifier(fullHouse);

		// This is the very top of the chain (top)
		// set the next processor in the chain (FourOfAKindHandIdentifier)
		processor = new StraightFlushIdentifier(fourOfAKind);

	}

	/**
	 * Gets the chain of handlers and bootstraps the processing of Player's Hand
	 * (including Community Card) <br/>
	 * <br/>
	 * 
	 * <strong>Chain:</strong> <br/>
	 * <ol>
	 * <li>{@link StraightFlush}</li>
	 * <li>{@link FourOfAKind}</li>
	 * <li>{@link FullHouse}</li>
	 * <li>{@link Flush}</li>
	 * <li>{@link Straight}</li>
	 * <li>{@link ThreeOfAKind}</li>
	 * <li>{@link TwoPair}</li>
	 * <li>{@link OnePair}</li>
	 * <li>{@link HighCard}</li>
	 * </ol>
	 * <br/>
	 * <br/>
	 */
	public static AbstractHandIdentifier getHandlers() {
		if (processor == null) {
			init();
		}
		return processor;
	}
}
