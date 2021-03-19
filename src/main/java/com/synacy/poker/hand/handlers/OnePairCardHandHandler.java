package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.OnePair;

public class OnePairCardHandHandler extends AbstractHandler {

	public OnePairCardHandHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		
		//check if onepair
		boolean isOnePair = false;
		List<Card> pairCards = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		if (isOnePair) {
			return new OnePair(pairCards, otherCards);
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
		return HandType.ONE_PAIR;
	}

}
