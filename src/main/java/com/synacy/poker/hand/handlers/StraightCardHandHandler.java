package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidStraightException;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

public class StraightCardHandHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	
	public StraightCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {
		boolean isStraight = false;
		List<Card> straightCards = Collections.emptyList();
			
		if (isStraight) {
			
			return new Straight(straightCards);
		} else {
			throw new InvalidStraightException();
		}
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.STRAIGHT;
	}

}
