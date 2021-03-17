package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.ThreeOfAKind;

public class ThreeOfAKindProcessor extends HandValueProcessor {

	public ThreeOfAKindProcessor(HandValueProcessor next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		boolean isThreeOfAKind = false;
		List<Card> threeOfAKindCards = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		//process logic here
		
		if (isThreeOfAKind) {
			return new ThreeOfAKind(threeOfAKindCards, otherCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		return null;
	}

}
