package com.synacy.poker.hand.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;

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
	 * Abstract process will be defined per processor`
	 * 
	 * @param playerCards
	 * @param communityCards
	 * @return
	 */
	public abstract Hand handle(List<Card> playerCards, List<Card> communityCards);
	
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
	public void mapCardHard(List<Card> playerCards, List<Card> communityCards) {
		//reset the bitmaps
		Arrays.fill(cardRankMap, 0);
		Arrays.fill(cardSuitMap, 0);
		
		Stream.concat(playerCards.stream(), communityCards.stream())
			  .forEach((card) -> {
				  cardRankMap[card.getRank().ordinal()]++;
				  cardSuitMap[card.getSuit().ordinal()]++;
			  });
	}
	
	/**
	 * Checks whether player and community cards are identifiable
	 * @return
	 */
	public boolean isCardHandIdentifieable() {
		return Arrays.stream(cardRankMap).filter(value -> value != 0).count() > MAX_HAND_CARDS;
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
}
