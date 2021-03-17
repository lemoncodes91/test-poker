package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;

public class FullHouseProcessor extends HandValueProcessor {

	public FullHouseProcessor(HandValueProcessor next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		boolean isFullHouse = false;
		List<Card> threeOfAKindCards = Collections.emptyList();
		List<Card> pairCards = Collections.emptyList();
		
		//process logic here
		
		if (isFullHouse) {
			return new FullHouse(threeOfAKindCards, pairCards);
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		return null;
	}

}
