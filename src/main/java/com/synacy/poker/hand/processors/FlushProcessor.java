package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FullHouse;

public class FlushProcessor extends HandValueProcessor {

	public FlushProcessor(HandValueProcessor next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		boolean isFlush = false;
		List<Card> flushCards = Collections.emptyList();
		
		//process logic here
		
		if (isFlush) {
			return new Flush(flushCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		return null;
	}

}
