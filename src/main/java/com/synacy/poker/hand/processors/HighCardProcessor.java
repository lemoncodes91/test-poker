package com.synacy.poker.hand.processors;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.HighCard;

public class HighCardProcessor extends HandValueProcessor {

	public HighCardProcessor(HandValueProcessor next) {
		super(next);
	}
	
	/**
	 * Processor for HighCard type
	 */
	@Override
	public Hand process(List<Card> playerCards, List<Card> communityCards) {
		
		//check if onepair
		boolean isHighCard = true;
		List<Card> highCards = Stream.concat(
										playerCards.stream(), 
										communityCards.stream()) // combined both cards
									 // sort card rank by priority
									 .sorted((card1, card2) -> card1.getRank().getPriority() - card2.getRank().getPriority())
									 .collect(Collectors.toList());
				
		if (isHighCard) {
			//send only first 5 cards from the combined cards list
			return new HighCard(highCards.stream().limit(MAX_HAND_CARDS).collect(Collectors.toList()));
		} else {
			if (next != null) {
				return next.process(playerCards, communityCards);
			}
		}
		
		return null;
	}

}
