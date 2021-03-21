package com.synacy.poker.hand.handlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.identifiers.AbstractHandIdentifier;
import com.synacy.poker.hand.identifiers.ThreeOfAKindIdentifier;

public class ThreeOfAKindIdentifierTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_three_of_a_kind_1() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		player.add(new Card(CardRank.SEVEN, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.SEVEN, CardSuit.HEARTS));
		community.add(new Card(CardRank.KING, CardSuit.CLUBS));
		community.add(new Card(CardRank.TWO, CardSuit.SPADES));
		community.add(new Card(CardRank.ACE, CardSuit.CLUBS));
		community.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new ThreeOfAKindIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals("Trips (7) - A,K High", hand.toString());
	}
}
