package com.synacy.poker.hand.handlers;

import static org.junit.Assert.*;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StraightFlushCardHandHandlerTest {

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
	public void test_straight_flush_spade_ace_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.ACE, suit));
		player.add(new Card(CardRank.KING, suit));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, suit));
		community.add(new Card(CardRank.JACK, suit));
		community.add(new Card(CardRank.TEN, suit));
		community.add(new Card(CardRank.FIVE, suit));
		community.add(new Card(CardRank.TWO, suit));
		
		AbstractHandler sfHandler = new StraightFlushCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		//assert hand type
		assertEquals(HandType.STRAIGHT_FLUSH, hand.getHandType());
		//assert display
		assertEquals("Royal Flush", hand.toString());
	}

	@Test
	public void test_straight_flush_spade_ten_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.TEN, suit));
		player.add(new Card(CardRank.NINE, suit));
		
		//Community Cards
		community.add(new Card(CardRank.EIGHT, suit));
		community.add(new Card(CardRank.FIVE, suit));
		community.add(new Card(CardRank.SIX, suit));
		community.add(new Card(CardRank.ACE, suit));
		community.add(new Card(CardRank.SEVEN, suit));
		
		AbstractHandler sfHandler = new StraightFlushCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT_FLUSH, hand.getHandType());
		assertEquals("Straight Flush (10 High)", hand.toString());
	}
	
	@Test
	public void test_straight_flush_spade_king_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.TEN, suit));
		player.add(new Card(CardRank.JACK, suit));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, suit));
		community.add(new Card(CardRank.FIVE, suit));
		community.add(new Card(CardRank.NINE, suit));
		community.add(new Card(CardRank.KING, suit));
		community.add(new Card(CardRank.SEVEN, suit));
		
		AbstractHandler sfHandler = new StraightFlushCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT_FLUSH, hand.getHandType());
		assertEquals("Straight Flush (K High)", hand.toString());
	}
	
	@Test
	public void test_straight_flush_spade_queen_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.TEN, suit));
		player.add(new Card(CardRank.JACK, suit));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, suit));
		community.add(new Card(CardRank.ACE, suit));
		community.add(new Card(CardRank.NINE, suit));
		community.add(new Card(CardRank.EIGHT, suit));
		community.add(new Card(CardRank.SEVEN, suit));
		
		AbstractHandler sfHandler = new StraightFlushCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT_FLUSH, hand.getHandType());
		assertEquals("Straight Flush (Q High)", hand.toString());
	}
	
	@Test
	public void test_straight_flush_spade_five_high() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		CardSuit suit = CardSuit.SPADES;
		
		//Player Cards
		player.add(new Card(CardRank.FIVE, suit));
		player.add(new Card(CardRank.ACE, suit));
		
		//Community Cards
		community.add(new Card(CardRank.TWO, suit));
		community.add(new Card(CardRank.FOUR, suit));
		community.add(new Card(CardRank.THREE, suit));
		community.add(new Card(CardRank.NINE, suit));
		community.add(new Card(CardRank.SEVEN, suit));
		
		AbstractHandler sfHandler = new StraightFlushCardHandHandler(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.STRAIGHT_FLUSH, hand.getHandType());
		assertEquals("Straight Flush (5 High)", hand.toString());
	}
}
