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

public class StraightCardHandHandlerTest {

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
	public void test_STRAIGHT_ace_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.ACE, CardSuit.SPADES));
		player.add(new Card(CardRank.KING, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, CardSuit.CLUBS));
		community.add(new Card(CardRank.JACK, CardSuit.SPADES));
		community.add(new Card(CardRank.TEN, CardSuit.HEARTS));
		community.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
		community.add(new Card(CardRank.TWO, CardSuit.SPADES));
		
		AbstractHandler sfHandler = new StraightCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		//assert hand type
		assertEquals(HandType.STRAIGHT, hand.getHandType());
		//assert display
		assertEquals("Straight (A High)", hand.toString());
	}
	
	@Test
	public void test_STRAIGHT_ten_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.TEN, CardSuit.HEARTS));
		player.add(new Card(CardRank.NINE, CardSuit.CLUBS));
		
		//Community Cards
		community.add(new Card(CardRank.EIGHT, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.FIVE, CardSuit.SPADES));
		community.add(new Card(CardRank.SIX, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.ACE, CardSuit.HEARTS));
		community.add(new Card(CardRank.SEVEN, CardSuit.CLUBS));
		
		AbstractHandler sfHandler = new StraightCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT, hand.getHandType());
		assertEquals("Straight (10 High)", hand.toString());
	}
	
	@Test
	public void test_STRAIGHT_king_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.TEN, CardSuit.SPADES));
		player.add(new Card(CardRank.JACK, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, CardSuit.HEARTS));
		community.add(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.NINE, CardSuit.CLUBS));
		community.add(new Card(CardRank.KING, CardSuit.HEARTS));
		community.add(new Card(CardRank.SEVEN, CardSuit.DIAMONDS));
		
		AbstractHandler sfHandler = new StraightCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT, hand.getHandType());
		assertEquals("Straight (K High)", hand.toString());
	}
	
	@Test
	public void test_STRAIGHT_queen_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.TEN, CardSuit.SPADES));
		player.add(new Card(CardRank.JACK, CardSuit.HEARTS));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, CardSuit.CLUBS));
		community.add(new Card(CardRank.ACE, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.NINE, CardSuit.SPADES));
		community.add(new Card(CardRank.EIGHT, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		
		AbstractHandler sfHandler = new StraightCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT, hand.getHandType());
		assertEquals("Straight (Q High)", hand.toString());
	}
	
	@Test
	public void test_STRAIGHT_five_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		//Player Cards
		player.add(new Card(CardRank.FIVE, CardSuit.SPADES));
		player.add(new Card(CardRank.ACE, CardSuit.CLUBS));
		
		//Community Cards
		community.add(new Card(CardRank.TWO, CardSuit.HEARTS));
		community.add(new Card(CardRank.FOUR, CardSuit.DIAMONDS));
		community.add(new Card(CardRank.THREE, CardSuit.HEARTS));
		community.add(new Card(CardRank.NINE, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, CardSuit.HEARTS));
		
		AbstractHandler sfHandler = new StraightCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT, hand.getHandType());
		assertEquals("Straight (5 High)", hand.toString());
	}

}
