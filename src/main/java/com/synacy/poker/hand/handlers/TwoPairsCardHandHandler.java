package com.synacy.poker.hand.handlers;

import java.util.Collections;
import java.util.List;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;

public class TwoPairsCardHandHandler extends AbstractHandler {

	public TwoPairsCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		boolean isTwoPairs = false;
		List<Card> firstPairCards = Collections.emptyList();
		List<Card> secondPairCards = Collections.emptyList();
		List<Card> otherCards = Collections.emptyList();
		
		//process logic here
		
		if (isTwoPairs) {
			return new TwoPair(firstPairCards, secondPairCards, otherCards);
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
		return HandType.TWO_PAIR;
	}

}
