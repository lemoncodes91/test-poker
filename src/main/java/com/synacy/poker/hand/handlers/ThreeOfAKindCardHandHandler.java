package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.ThreeOfAKind;

public class ThreeOfAKindCardHandHandler extends AbstractHandler {

	public ThreeOfAKindCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isThreeOfAKind = false;
		List<Card> threeOfAKindCards = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		//process logic here
		
		if (isThreeOfAKind) {
			return new ThreeOfAKind(threeOfAKindCards, otherCards);
		} else {
			if (next != null) {
				return next.handle(playerCards, communityCards);
			}
		}
		return null;
	}

}
