package com.synacy.poker.hand;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.synacy.poker.hand.comparators.FourOfAKindComparator;
import com.synacy.poker.hand.comparators.FullHouseComparator;
import com.synacy.poker.hand.comparators.HandCompareStrategy;
import com.synacy.poker.hand.comparators.OnePairComparator;
import com.synacy.poker.hand.comparators.SimpleCardRankComparator;
import com.synacy.poker.hand.comparators.ThreeOfAKindComparator;
import com.synacy.poker.hand.comparators.TwoPairComparator;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;

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
			//since both hands are the same type, pass either of the hand type 
			//to fire the related comparator of hand type
			return getComparator(o1.getHandType()).compare(o1, o2);
		}

		return compVal;
	}
	
	
	private HandCompareStrategy getComparator(HandType type) {
		HandCompareStrategy comparator = null;
		switch (type) {
			case STRAIGHT_FLUSH:
				comparator = new SimpleCardRankComparator();
				break;
			case FOUR_OF_A_KIND:
				comparator = new FourOfAKindComparator();
				break;
			case FULL_HOUSE:
				comparator = new FullHouseComparator();
				break;
			case FLUSH:
				comparator = new SimpleCardRankComparator();
				break;
			case STRAIGHT:
				comparator = new SimpleCardRankComparator();
				break;
			case THREE_OF_A_KIND:
				comparator = new ThreeOfAKindComparator();
				break;
			case TWO_PAIR:
				comparator = new TwoPairComparator();
				break;
			case ONE_PAIR:
				comparator = new OnePairComparator();
				break;
			case HIGH_CARD:
				comparator = new SimpleCardRankComparator();
				break;
			default:
				break;
		}
		return comparator;
	}

}
