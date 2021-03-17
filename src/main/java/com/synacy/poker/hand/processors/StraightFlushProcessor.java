package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.StraightFlush;

public class StraightFlushProcessor extends HandValueProcessor {

	public StraightFlushProcessor(HandValueProcessor next) {
		super(next);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		boolean isStraightFlush = false;
		List<Card> straightFlushCards = Collections.emptyList();
		
		//process logic here
		
		if (isStraightFlush) {
			return new StraightFlush(straightFlushCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		
		return null;
	}

}
