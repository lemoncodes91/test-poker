package com.synacy.poker.hand.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidTwoPairException;
import com.synacy.poker.hand.types.TwoPair;

public class TwoPairsCardHandHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(TwoPairsCardHandHandler.class);
	private static final int TWO_PAIR = 2;

	public TwoPairsCardHandHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> firstPairCards = null;
		List<Card> secondPairCards = null;
		List<Card> otherCards = null;
		List<Integer> cardIndices = getCardRankMapIndices();

		// Find the indices of the 2 Pair cards
		// e.g {0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 2, 0, 0}
		// indices is 6, 7, TWO PARS (K, 8)
		List<Integer> twoPairIndices = cardIndices.stream().filter(index -> cardRankMap[index] == TWO_PAIR)
				.sorted((index1, index2) -> index2 - index1).collect(Collectors.toList());

		if (!twoPairIndices.isEmpty() && twoPairIndices.size() >= TWO_PAIR) {
			// get the high card kicker
			// kicker will be highest card that does not below to the 2 pair
			int highCardKicker = cardIndices.stream().filter(index -> {
				return twoPairIndices.stream().noneMatch(twoPairIndex -> index == twoPairIndex);
			}).sorted((index1, index2) -> index2 - index1).findFirst().orElse(-1);
			// get the high pair card
			firstPairCards = combinedCards.stream().filter(card -> card.getRank().ordinal() == twoPairIndices.get(0))
					.collect(Collectors.toList());

			// get the next high pair card
			secondPairCards = combinedCards.stream().filter(card -> card.getRank().ordinal() == twoPairIndices.get(1))
					.collect(Collectors.toList());

			// get the kicker card
			otherCards = combinedCards.stream().filter(card -> card.getRank().ordinal() == highCardKicker)
					.collect(Collectors.toList());
			return new TwoPair(firstPairCards, secondPairCards, otherCards);
		} else {
			logger.debug("This is not a " + getHandType().toString());
			throw new InvalidTwoPairException();
		}
	}

	@Override
	public HandType getHandType() {
		return HandType.TWO_PAIR;
	}

}
