package com.synacy.poker.hand.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.types.HighCard;

public class HighCardHandler extends AbstractHandler {

	public HighCardHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {

		// Since this is the least hand in poker
		// combination is already filtered up in the chain
		// when it reaches this handler, it is already assumed that
		// this is the worst hand (no combination, etc)
		// get only the first 5 high cards,
		List<Card> highCards = combinedCards.stream()
				// sort card rank by priority
				.sorted((card1, card2) -> card2.getRank().ordinal() - card1.getRank().ordinal())
				// send only first 5 cards from the combined cards list
				.limit(MAX_HAND_CARDS).collect(Collectors.toList());
		return new HighCard(highCards);
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.HIGH_CARD;
	}

}
