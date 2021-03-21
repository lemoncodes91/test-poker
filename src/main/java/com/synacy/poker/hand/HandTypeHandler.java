package com.synacy.poker.hand.handlers;

import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.StraightFlush;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;

public class HandTypeHandler {

	private static AbstractHandler processor = null;

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
		AbstractHandler highCard = new HighCardHandler(null);

		// This is the middle processors
		AbstractHandler onePair = new OnePairCardHandHandler(highCard);
		AbstractHandler twoPairs = new TwoPairsCardHandHandler(onePair);
		AbstractHandler threeOfAKind = new ThreeOfAKindCardHandHandler(twoPairs);
		AbstractHandler straight = new StraightCardHandHandler(threeOfAKind);
		AbstractHandler flush = new FlushCardHandler(straight);
		AbstractHandler fullHouse = new FullHouseCardHandler(flush);
		AbstractHandler fourOfAKind = new FourOfAKindHandler(fullHouse);

		// This is the very top of the chain (top)
		// set the next processor in the chain
		processor = new StraightFlushCardHandHandler(fourOfAKind);

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
	public static AbstractHandler getHandlers() {
		if (processor == null) {
			init();
		}
		return processor;
	}
}
