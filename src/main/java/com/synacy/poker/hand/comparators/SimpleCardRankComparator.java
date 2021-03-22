package com.synacy.poker.hand.comparators;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.Straight;
import com.synacy.poker.hand.types.StraightFlush;

/**
 * Simple Rank comparator where it will check each Card Rank regardless of the
 * suit.
 * 
 * @apiNote This is for {@link StraightFlush} or {@link Straight} or any
 *          {@link HandType} that is sequential in behavior 
 * 
 * @author mikram
 *
 */
public class SimpleCardRankComparator extends HandCompareStrategy {

	/**
	 * Compares between hands baed on the card rank values (enum ordinal) (e.g A-12,
	 * K-11, etc). o1 and o2 card list is assumed to be sequential.
	 * 
	 * 
	 * <br/>
	 * <br/>
	 * 
	 * <strong><i>Note: Ordinal values is zero based</i></strong>
	 * 
	 * @param o1 - Player's Hand
	 * @param o2 - Player's Hand
	 * @return Non-negative if o2 is greater than o1, Negative integer if o1 > than
	 *         o2, 0 if both o1 and o2 are equal
	 * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html">
	 *      enum Type </>
	 */
	@Override
	public int compare(Hand o1, Hand o2) {
		return o2.getCardValues() - o1.getCardValues();
	}

}
