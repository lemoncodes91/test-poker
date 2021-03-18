package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.StraightFlush;

public class FourOfAKindHandler extends AbstractHandler {

	public FourOfAKindHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isFourOfAKind = false;
		List<Card> fourOfAKind = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		//process logic here

		if (isFourOfAKind) {
			return new FourOfAKind(fourOfAKind, otherCards);
		} else {
			if (next != null) {
				return next.handle(playerCards, communityCards);
			}
		}
		
		return null;
	}

}
