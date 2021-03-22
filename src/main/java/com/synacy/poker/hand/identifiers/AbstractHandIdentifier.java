package com.synacy.poker.hand.identifiers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.exceptions.HandException;
import com.synacy.poker.hand.exceptions.InvalidHandException;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.StraightFlush;

public abstract class AbstractHandIdentifier {
	private static final Logger logger = LoggerFactory.getLogger(AbstractHandIdentifier.class);

	// rank lowest to highest
	// 2 3 4 5 6 7 8 9 10 J Q K A
	// CardRank ordinal will be the index
	protected int[] cardRankMap = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	// card suit mapping
	// Spades, Clubs, Diamonds, Hearts
	// CardSuit ordinal will be the index
	protected int[] cardSuitMap = { 0, 0, 0, 0 };

	public final static int INDEX_NOT_FOUND = -1;
	public final static int MAX_HAND_CARDS = 5;
	public AbstractHandIdentifier next;

	public AbstractHandIdentifier(AbstractHandIdentifier next) {
		this.next = next;
	}

	/**
	 * Custom handling of HandType (e.g {@link StraightFlush}, {@link HighCard})
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	protected abstract Hand identifyHand(List<Card> combinedCards) throws HandException;

	/**
	 * Gets the HandType of the Handler (e.g {@link StraightFlush},
	 * {@link HighCard})
	 * 
	 * @return
	 */
	protected abstract HandType getHandType();

	/**
	 * Handles the processing for a HandType (e.g {@link StraightFlush},
	 * {@link HighCard})
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		logger.debug("Start handling for " + getHandType().toString());

		try {
			// combines player and community card
			List<Card> combinedCards = Stream.of(playerCards, communityCards).flatMap(Collection::stream)
					.collect(Collectors.toList());

			// map card hads to bitmap
			mapCardHand(combinedCards);

			// if combination card can be processed
			// (e.g player's cards + comunity's cards >= 5 cards)
			checkHand();

			return identifyHand(combinedCards);
		} catch (HandException e) {
			logger.warn(e.getMessage());
		} finally {
			logger.debug("End handling for " + getHandType().toString());
		}

		return next(playerCards, communityCards);
	}

	/**
	 * Map the Player's Card and Community Cards Internal mapping of Card Rank and
	 * Card Suit
	 * 
	 * @param playerCards
	 * @param communityCards
	 */
	protected void mapCardHand(List<Card> combinedCards) {

		resetCardMapping();

		combinedCards.stream().forEach((card) -> {
			cardRankMap[card.getRank().ordinal()]++;
			cardSuitMap[card.getSuit().ordinal()]++;
		});
	}

	/**
	 * Get the indices of the card rank bitmap
	 * 
	 * @return
	 */
	public List<Integer> getCardRankMapIndices() {
		boolean isStraightOrStraightFlush = getHandType() == HandType.STRAIGHT_FLUSH
				|| getHandType() == HandType.STRAIGHT;
		List<Integer> cardRankIndexes = IntStream.range(0, cardRankMap.length).filter(value -> {
			// When Straight or Straight Flush
			// Values on the map should only have 1 per Rank
			if (isStraightOrStraightFlush)
				return cardRankMap[value] == 1;
			// Otherwise, it can be other than Straight or Straight Flush
			// (e.g) Pair, Two Pair, Three Pair, etc
			else
				return cardRankMap[value] != 0;
		}).boxed().collect(Collectors.toList());

		return cardRankIndexes;
	}

	/**
	 * Checks whether player and community cards are identifiable
	 * 
	 * @return
	 */
	protected void checkHand() throws HandException {
		boolean isCurrentHandlerHighCard = getHandType() == HandType.HIGH_CARD;
		boolean isOkHand = IntStream.range(0, cardRankMap.length).filter(index -> cardRankMap[index] != 0)
				.sum() > MAX_HAND_CARDS;

		if (!isOkHand && !isCurrentHandlerHighCard) {
			throw new InvalidHandException();
		}
	}

	/**
	 * Moves to the next handler on the chain Otherwise, chain is terminated
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public Hand next(List<Card> playerCards, List<Card> communityCards) {
		if (next != null) {
			return next.handle(playerCards, communityCards);
		}

		return null;
	}

	/**
	 * Resets the cardRank and cardSuit bitmaps for every evaluation
	 */
	private void resetCardMapping() {
		// reset the bitmaps
		Arrays.fill(cardRankMap, 0);
		Arrays.fill(cardSuitMap, 0);
	}
}
