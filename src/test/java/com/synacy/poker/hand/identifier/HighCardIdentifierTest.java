package com.synacy.poker.hand.identifier;

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
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.identifiers.AbstractHandIdentifier;
import com.synacy.poker.hand.identifiers.HighCardIdentifier;

public class HighCardIdentifierTest {

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
	public void test() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.TEN, CardSuit.SPADES));
		player.add(new Card(CardRank.JACK, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, CardSuit.CLUBS));
		community.add(new Card(CardRank.ACE, CardSuit.SPADES));
		community.add(new Card(CardRank.NINE, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.EIGHT, CardSuit.HEARTS));
		community.add(new Card(CardRank.SEVEN, CardSuit.CLUBS));
		
		AbstractHandIdentifier sfHandler = new HighCardIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.HIGH_CARD, hand.getHandType());
		assertEquals("A,Q,J,10,9", hand.toString());
	}

}
