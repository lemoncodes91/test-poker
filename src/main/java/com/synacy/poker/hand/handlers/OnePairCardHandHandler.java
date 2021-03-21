package com.synacy.poker.hand.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidOnePairException;
import com.synacy.poker.hand.types.OnePair;

public class OnePairCardHandHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(OnePairCardHandHandler.class);
	private static final int TWO_PAIR = 2;
	
	public OnePairCardHandHandler(AbstractHandler next) {
		super(next);
	}

	@Override
	public HandType getHandType() {
		// TODO Auto-generated method stub
		return HandType.ONE_PAIR;
	}

	@Override
	protected Hand identifyHand(List<Card> combinedCards) throws HandException {
		List<Card> pairCards = null;
		List<Card> otherCards = null;
		List<Integer> cardIndices = getCardRankMapIndices();
		
		int twoPairIndices = cardIndices.stream()
							  .filter(index -> cardRankMap[index] == TWO_PAIR)
							  .findFirst()
							  .orElse(INDEX_NOT_FOUND);
		
		if (twoPairIndices != INDEX_NOT_FOUND) {
			List<Integer> otherIndices = cardIndices.stream()
										  .filter(index -> cardRankMap[index] != TWO_PAIR)
										  .sorted((index1, index2) -> index2 -index1)
										  .limit(MAX_HAND_CARDS - TWO_PAIR)
										  .collect(Collectors.toList());
			pairCards = combinedCards.stream()
									 .filter(card -> card.getRank().ordinal() == twoPairIndices)
									 .collect(Collectors.toList());
			
			otherCards = combinedCards.stream()
									  .filter(card -> {
										  return otherIndices.stream().anyMatch(index -> card.getRank().ordinal() == index);
									  })
									  .sorted((card1, card2) -> card2.getRank().ordinal() - card1.getRank().ordinal()) 
									  .collect(Collectors.toList());
									 
			return new OnePair(pairCards, otherCards);
		} else {
			logger.debug("This is not a "+getHandType().toString());
			throw new InvalidOnePairException();
		}
	}

}
