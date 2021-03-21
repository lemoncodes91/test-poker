package com.synacy.poker.hand.handlers;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.StraightFlush;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;

/**
 * Custom {@link Comparator} for determining player hand from highest (best
 * hand) to lowest (worst hand). Also, handles tie breakers by sorting based on
 * the kickers <br/>
 * <br/>
 * Example
 * <ol>
 * <li>{@link OnePair} One Pair (2) - A,K,Q High</li>
 * <li>{@link OnePair} One Pair (2) - K,Q,10 High</li>
 * <li>{@link HighCard} A,K,Q,3,2</li>
 * </ol>
 * 
 * [<strong>One Pair (2) - A,K,Q High</strong>] being the winning hand <br/>
 * [<strong>A,K,Q,3,2</strong>] being the losing hand <br/>
 * <br/>
 * <br/>
 * 
 * @author mikra
 *
 */
@Component
public class HandComparator implements Comparator<Hand> {

	@Override
	public int compare(Hand o1, Hand o2) {
		int compVal = o2.getHandType().ordinal() - o1.getHandType().ordinal();
		boolean isSameHandType = compVal == 0;

		// When HandType is the same
		// examining for kickers
		if (isSameHandType) {
			compVal = o2.getCardValues() - o1.getCardValues();
		}

		return compVal;
	}

}
