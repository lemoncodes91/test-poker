package com.synacy.poker.hand.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.StraightFlush;

public class StraightFlushCardHandHandler extends AbstractHandler {


	public StraightFlushCardHandHandler(AbstractHandler next) {
		super(next);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Hand handle(List<Card> playerCards, List<Card> communityCards) {
		List<Card> straightFlushCards = null;
		
		//process logic her
		
		//map the players card and community cards
		//populates cardRankMap and cardRankSuit
		mapCardHard(playerCards, communityCards);
		
		if (isCardHandIdentifieable()) {
			boolean isFlush = IntStream.range(0, cardSuitMap.length).anyMatch(value -> cardSuitMap[value] >= MAX_HAND_CARDS);
			boolean isStraight = false;
			List<Integer> cardRankIndexes = new ArrayList<Integer>();
			
			//determine the consecutive 1s on mapping 
			//reverse traverse
			for(int i = cardRankMap.length - 1; i >= 0; i--) {
				int currentCount = cardRankMap[i];
				int nextCount = 0;
				
				if (currentCount != 0) {
					
					cardRankIndexes.add(i);	
					if ((i - 1) >= 0) {
						nextCount = cardRankMap[i - 1];
						if ((nextCount == 0 || nextCount > 1)&& cardRankIndexes.size() < MAX_HAND_CARDS) {
							cardRankIndexes.clear();
						}
					}
				}
				
			}
			
			if (cardRankIndexes.isEmpty()) {
				isStraight = false;
			}
			
			//specific to streel wheel five high
			boolean isFiveHigh = isStraight && cardRankIndexes.get(0) == CardRank.FIVE.ordinal();
			//if fivehigh, determine if card combination has Ace
			if (isFiveHigh) {
				if (cardRankMap[CardRank.ACE.ordinal()] == 1) {
					cardRankIndexes.add(CardRank.ACE.ordinal());
				}
			}
			
			//cardStraightCount is 5, then it is straight 
			isStraight = cardRankIndexes.size() >= MAX_HAND_CARDS;
			
			
			if (isStraight && isFlush) {
				
				int cardSuitIndex = IntStream.range(0, cardSuitMap.length).filter(value -> cardSuitMap[value] != 0).findFirst().getAsInt();
	
				straightFlushCards = cardRankIndexes.stream()
													.limit(MAX_HAND_CARDS)
													.map(indexes -> {
														CardSuit[] suits = CardSuit.values();
														CardSuit suit = Arrays.stream(suits)
																			  .filter(s -> s.ordinal() == cardSuitIndex)
																			  .findFirst()
																			  .get();
														
														CardRank[] ranks = CardRank.values();
														CardRank rank = Arrays.stream(ranks)
																			  .filter(r -> r.ordinal() == indexes)
																			  .findFirst()
																			  .get();
														return new Card(rank, suit);
													})
													.collect(Collectors.toList());
				
				
				return new StraightFlush(straightFlushCards);
			} else {
				if (next != null) {
					return next.handle(playerCards, communityCards);
				}
			}
		} else {
			if (next != null) {
				return next.handle(playerCards, communityCards);
			}
		}
		
		return null;
	}

}
