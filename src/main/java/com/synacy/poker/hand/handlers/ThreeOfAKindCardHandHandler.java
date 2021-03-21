package com.synacy.poker.hand.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidThreeOfAKindException;
import com.synacy.poker.hand.types.ThreeOfAKind;

public class ThreeOfAKindCardHandHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(ThreeOfAKindCardHandHandler.class);
	private static final int TRIPLETS = 3;
	private static final int TWO_HIGH_PAIR = 2;
	
	public ThreeOfAKindCardHandHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> threeOfAKindCards = null;
		List<Card> otherCards = null;
		List<Integer> cardIndices = getCardRankMapIndices();

		//Find the indices of the 2 Pair cards
		//e.g {0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 3, 0, 0}
		// index  is 10
		int indexWithTriplets = cardIndices.stream()
									   .filter(index -> cardRankMap[index] == TRIPLETS)
									   .findFirst()
									   .orElse(INDEX_NOT_FOUND);
		if (indexWithTriplets != INDEX_NOT_FOUND) {
			
			//Get the remaining high kicker indices
			List<Integer> twoHighKickers = cardIndices.stream()
												    .sorted((index1, index2) -> index2 - index1)
												    .filter(index -> index != indexWithTriplets)
												    .limit(TWO_HIGH_PAIR)
												    .collect(Collectors.toList());
			//Get the 3 of a kind cards
			threeOfAKindCards = combinedCards.stream()
											 .filter(card -> card.getRank().ordinal() == indexWithTriplets)
										    .collect(Collectors.toList());
			
			//Extract the two High Kicker from the combined cards 
			otherCards = combinedCards.stream()
									  .filter(card -> {
										  return twoHighKickers.stream().anyMatch(index -> card.getRank().ordinal() == index);
									  })
									  .sorted((card1, card2) -> card2.getRank().ordinal() - card1.getRank().ordinal())
									  .collect(Collectors.toList());
			
			return new ThreeOfAKind(threeOfAKindCards, otherCards);
		} else {
			logger.debug("This is not a "+getHandType().toString());
			throw new InvalidThreeOfAKindException();
		}
	}

	@Override
	public HandType getHandType() {
		return HandType.THREE_OF_A_KIND;
	}

}
