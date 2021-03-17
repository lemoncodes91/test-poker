package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.OnePair;

public class OnePairProcessor extends HandValueProcessor {

	public OnePairProcessor(HandValueProcessor next) {
		super(next);
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		
		//check if onepair
		boolean isOnePair = false;
		List<Card> pairCards = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		if (isOnePair) {
			return new OnePair(pairCards, otherCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		
		return null;
		
	}

}
