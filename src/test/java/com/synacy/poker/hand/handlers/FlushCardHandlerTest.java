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
import com.synacy.poker.hand.HandType;

public class FlushCardHandlerTest {

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
	public void test_flush_1() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.QUEEN, suit));
		player.add(new Card(CardRank.KING, suit));
		
		//Community Cards
		community.add(new Card(CardRank.ACE, suit));
		community.add(new Card(CardRank.TWO, suit));
		community.add(new Card(CardRank.FIVE, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, suit));
		community.add(new Card(CardRank.SIX, CardSuit.CLUBS));
		
		AbstractHandler sfHandler = new FlushCardHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.FLUSH, hand.getHandType());
		assertEquals("Flush (A High)", hand.toString());
	}

}
