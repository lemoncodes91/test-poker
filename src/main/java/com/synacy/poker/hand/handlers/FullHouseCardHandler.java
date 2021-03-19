package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;

public class FullHouseCardHandler extends AbstractHandler {

	public FullHouseCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isFullHouse = false;
		List<Card> threeOfAKindCards = Collections.emptyList();
		List<Card> pairCards = Collections.emptyList();
		
		//process logic here
		
		if (isFullHouse) {
			return new FullHouse(threeOfAKindCards, pairCards);
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
		return HandType.FULL_HOUSE;
	}

}
