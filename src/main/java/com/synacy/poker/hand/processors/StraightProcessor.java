package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.Straight;

public class StraightProcessor extends HandValueProcessor {

	public StraightProcessor(HandValueProcessor next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		boolean isStraight = false;
		List<Card> straightCards = Collections.emptyList();
		
		//process logic here
		
		if (isStraight) {
			return new Straight(straightCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		return null;
	}

}
