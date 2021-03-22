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
import com.synacy.poker.hand.identifiers.FourOfAKindHandIdentifier;

public class FourOfAKindHandIdentifierTest {

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
	public void test_four_of_a_kind_1() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.QUEEN, CardSuit.SPADES));
		player.add(new Card(CardRank.QUEEN, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.QUEEN, CardSuit.HEARTS));
		community.add(new Card(CardRank.QUEEN, CardSuit.CLUBS));
		community.add(new Card(CardRank.JACK, CardSuit.SPADES));
		community.add(new Card(CardRank.FIVE, CardSuit.CLUBS));
		community.add(new Card(CardRank.TWO, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new FourOfAKindHandIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.FOUR_OF_A_KIND, hand.getHandType());
		assertEquals("Quads (Q) - J High", hand.toString());
	}

	@Test
	public void test_four_of_a_kind_2() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.JACK, CardSuit.SPADES));
		player.add(new Card(CardRank.FOUR, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.FOUR, CardSuit.HEARTS));
		community.add(new Card(CardRank.FOUR, CardSuit.CLUBS));
		community.add(new Card(CardRank.FOUR, CardSuit.SPADES));
		community.add(new Card(CardRank.FIVE, CardSuit.CLUBS));
		community.add(new Card(CardRank.TWO, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new FourOfAKindHandIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.FOUR_OF_A_KIND, hand.getHandType());
		assertEquals("Quads (4) - J High", hand.toString());
	}
	
	@Test
	public void test_four_of_a_kind_3() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.KING, CardSuit.SPADES));
		player.add(new Card(CardRank.KING, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.KING, CardSuit.HEARTS));
		community.add(new Card(CardRank.KING, CardSuit.CLUBS));
		community.add(new Card(CardRank.THREE, CardSuit.SPADES));
		community.add(new Card(CardRank.FIVE, CardSuit.CLUBS));
		community.add(new Card(CardRank.TWO, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new FourOfAKindHandIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.FOUR_OF_A_KIND, hand.getHandType());
		assertEquals("Quads (K) - 5 High", hand.toString());
	}
	
	@Test
	public void test_four_of_a_kind_4() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		player.add(new Card(CardRank.TWO, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.SEVEN, CardSuit.HEARTS));
		community.add(new Card(CardRank.KING, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		community.add(new Card(CardRank.TWO, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new FourOfAKindHandIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(HandType.FOUR_OF_A_KIND, hand.getHandType());
		assertEquals("Quads (7) - K High", hand.toString());
	}
	
	@Test
	public void test_four_of_a_kind_err_1() {
		List<Card> player  = new ArrayList<Card>();
		List<Card> community  = new ArrayList<Card>();
		
		//Player Cards
		player.add(new Card(CardRank.SEVEN, CardSuit.SPADES));
		player.add(new Card(CardRank.SEVEN, CardSuit.DIAMONDS));
		
		//Community Cards
		community.add(new Card(CardRank.TWO, CardSuit.HEARTS));
		community.add(new Card(CardRank.TWO, CardSuit.CLUBS));
		community.add(new Card(CardRank.ACE, CardSuit.SPADES));
		community.add(new Card(CardRank.TWO, CardSuit.CLUBS));
		community.add(new Card(CardRank.SEVEN, CardSuit.HEARTS));
		
		AbstractHandIdentifier sfHandler = new FourOfAKindHandIdentifier(null);
		Hand hand = sfHandler.handle(player, community);
		assertEquals(null, hand);
	}
}
