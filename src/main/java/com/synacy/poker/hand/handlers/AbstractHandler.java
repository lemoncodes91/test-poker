package com.synacy.poker.hand.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.StraightFlush;

public abstract class AbstractHandler {

	// rank lowest to highest
	// 2 3 4 5 6 7 8 9 10 J Q K A
	// CardRank ordinal will be the index
	public int[] cardRankMap =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	// card suit mapping
	// Spades, Clubs, Diamonds, Hearts
	// CardSuit ordinal will be the index
	public int[] cardSuitMap =  {0, 0, 0, 0};
	
	public final static int MAX_HAND_CARDS = 5;
	public AbstractHandler next;
	
	public AbstractHandler(AbstractHandler next) {
		this.next = next;
	}
	
	/**
	 * Handles the processing for a HandType
	 * (e.g {@link StraightFlush}, {@link HighCard})
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public abstract Hand handle(List<Card> playerCards, List<Card> communityCards);
	
	/**
	 * Gets the HandType of the Handler
	 * (e.g {@link StraightFlush}, {@link HighCard})
	 * @return
	 */
	public abstract HandType getHandType();
	
	/**
	 * Get the best Five Card Combination from players cards (2) and community cards (5)
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return List of {@link Card}
	 */
	public List<Card> getBestFiveCardCombination(List<Card> playerCards, List<Card> communityCards){
			   // combined both cards
		return Stream.concat( playerCards.stream(), communityCards.stream())
					 // sort card rank by priority
					 .sorted((card1, card2) -> card1.getRank().getPriority() - card2.getRank().getPriority())
					 //send only first 5 cards from the combined cards list
					 .limit(MAX_HAND_CARDS)
					 .collect(Collectors.toList());
	}
	
	/**
	 * Map the Player's Card and Community Cards 
	 * Internal mapping of Card Rank and Card Suit 
	 * 
	 * @param playerCards
	 * @param communityCards
	 */
	public void mapCardHand(List<Card> playerCards, List<Card> communityCards) {
		
		resetCardMapping();
		
		Stream.concat(playerCards.stream(), communityCards.stream())
			  .forEach((card) -> {
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
		 boolean isStraightOrStraightFlush = getHandType() == HandType.STRAIGHT_FLUSH ||  getHandType() == HandType.STRAIGHT; 
		 List<Integer> cardRankIndexes = IntStream.range(0, cardRankMap.length)
				 .filter(value -> {
					 //When Straight or Straight Flush
					 //Values on the map should only have 1 per Rank
					 if (isStraightOrStraightFlush)
						 return cardRankMap[value] ==  1;
					 //Otherwise, it can be other than Straight or Straight Flush
					 //(e.g) Pair, Two Pair, Three Pair, etc
					 else 
						 return cardRankMap[value] !=  0;
				 }).boxed().collect(Collectors.toList());
		 
		return cardRankIndexes;
	}
	
	/**
	 * Checks whether player and community cards are identifiable
	 * @return
	 */
	public boolean isCardHandIdentifieable() {
		
		//return Arrays.stream(cardRankMap).filter(value -> value != 0).count() > MAX_HAND_CARDS;
		return IntStream.range(0, cardRankMap.length).filter(index -> cardRankMap[index] != 0).sum() > MAX_HAND_CARDS;
	}
	
	/**
	 * Combine  and sort by Card Rank Priority
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public List<Card> combineAndSortByPriority(List<Card> playerCards, List<Card> communityCards){ 
		   // combined both cards
		return Stream.concat( playerCards.stream(), communityCards.stream())
				 // sort card rank by priority
				 .sorted((card1, card2) -> card1.getRank().getPriority() - card2.getRank().getPriority())
				 .collect(Collectors.toList());
	}
	
	/**
	 * Moves to the next handler on the chain
	 * Otherwise, chain is terminated
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
		//reset the bitmaps
		Arrays.fill(cardRankMap, 0);
		Arrays.fill(cardSuitMap, 0);
	}
}
