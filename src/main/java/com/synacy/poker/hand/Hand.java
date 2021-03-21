package com.synacy.poker.hand;

import java.util.List;

import com.synacy.poker.card.Card;

/**
 * The base class of the different Hands such as {@link com.synacy.poker.hand.types.Flush},
 * {@link com.synacy.poker.hand.types.FullHouse}, etc.
 */
public abstract class Hand {

    /**
     * @return The {@link HandType}
     */
    public abstract HandType getHandType();
    
    /**
     * Determining the card values with respect to the kicker 
     * 
     * @return sum of the card ranks (e.g A,K,Q,J,10 = 50) 
     */
    public abstract int getCardValues();

}
