package com.synacy.poker.hand.handlers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidFullHouseException;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;

public class FullHouseCardHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(FourOfAKindHandler.class);
	private static final int TRIPLETS = 3;
	private static final int PAIR = 2;

	public FullHouseCardHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> threeOfAKindCards = null;
		List<Card> pairCards = null;
		List<Integer> cardIndices = getCardRankMapIndices();
		
		//Find the index of the bitmap  that has QUADS(4) value
		//e.g {0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 3, 0, 0}
		// index is 10, has TRIPLETS (Q)
		int indexWithTriplets = cardIndices.stream()
										   .filter(index -> cardRankMap[index] == TRIPLETS)
										   .findFirst()
										   .orElse(INDEX_NOT_FOUND);
		
		//Find the index of the bitmap  that has QUADS(4) value
		//e.g {0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 3, 0, 0}
		// index is 6, has PAIR (8)
		int indexWithPairs = cardIndices.stream()
										.filter(index -> cardRankMap[index] == PAIR)
										.findFirst()
										.orElse(INDEX_NOT_FOUND);
		
		if (indexWithTriplets != INDEX_NOT_FOUND && indexWithPairs != INDEX_NOT_FOUND) {
			
			//find the four of a kind in the combinedcards
			threeOfAKindCards = combinedCards.stream()
									   .filter(card -> {
										   return card.getRank().ordinal() == indexWithTriplets;
									   })
									   .collect(Collectors.toList());
			
			//find the kicker in the combinedcards				
			pairCards = combinedCards.stream()
									   .filter(card -> {
										   return card.getRank().ordinal() == indexWithPairs;
									   })
									   .collect(Collectors.toList());
			return new FullHouse(threeOfAKindCards, pairCards);
		} else {
			logger.warn("This is not a "+getHandType().toString());
			throw new InvalidFullHouseException();
		}
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.FULL_HOUSE;
	}

}
