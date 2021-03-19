package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

public class StraightCardHandHandler extends AbstractHandler {

	public StraightCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isStraight = false;
		List<Card> straightCards = Collections.emptyList();
		
		//process logic here
		
		if (isStraight) {
			return new Straight(straightCards);
		} else {
			if (next != null) {
				return next.handle(playerCards, communityCards);
			}
		}
		return null;
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.STRAIGHT;
	}

}
