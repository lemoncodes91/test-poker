package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FullHouse;

public class FlushCardHandler extends AbstractHandler {

	public FlushCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isFlush = false;
		List<Card> flushCards = Collections.emptyList();
		
		//process logic here
		
		if (isFlush) {
			return new Flush(flushCards);
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
		return HandType.FLUSH;
	}

}
