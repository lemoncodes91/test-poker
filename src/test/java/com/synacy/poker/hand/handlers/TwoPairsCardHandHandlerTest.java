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

public class TwoPairsCardHandHandlerTest {

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
	public void test_two_pair_1() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		player.add(new Card(CardRank.SEVEN, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.KING, CardSuit.HEARTS));
		community.add(new Card(CardRank.KING, CardSuit.CLUBS));
		community.add(new Card(CardRank.TWO, CardSuit.SPADES));
		community.add(new Card(CardRank.ACE, CardSuit.CLUBS));
		community.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
		
		AbstractHandler sfHandler = new TwoPairsCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals("Two Pair (K,7) - A High", hand.toString());
	}

}
