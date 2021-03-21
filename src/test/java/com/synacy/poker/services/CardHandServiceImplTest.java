package com.synacy.poker.services;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.handlers.HandComparator;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.Straight;
import com.synacy.poker.hand.types.StraightFlush;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CardHandServiceImplTest {
	
	@Autowired
	CardHandServiceImpl cardService;
		

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
	public void identifyHand_royalFlush() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.ACE, CardSuit.SPADES),
				new Card(CardRank.KING, CardSuit.SPADES)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.SPADES),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.ACE, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Royal Flush", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straightFlush() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.SPADES),
				new Card(CardRank.JACK, CardSuit.SPADES)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.EIGHT, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.SPADES),
				new Card(CardRank.ACE, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Straight Flush (Q High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fourOfAKind() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.EIGHT, CardSuit.SPADES),
				new Card(CardRank.EIGHT, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
				new Card(CardRank.EIGHT, CardSuit.HEARTS),
				new Card(CardRank.SEVEN, CardSuit.SPADES),
				new Card(CardRank.SIX, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FourOfAKind);
		assertEquals("Quads (8) - 7 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fullHouse() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.EIGHT, CardSuit.SPADES),
				new Card(CardRank.EIGHT, CardSuit.DIAMONDS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.SEVEN, CardSuit.HEARTS),
				new Card(CardRank.SEVEN, CardSuit.SPADES),
				new Card(CardRank.EIGHT, CardSuit.CLUBS),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.ACE, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FullHouse);
		assertEquals("Full House (8,7)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_flush() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.SEVEN, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.SPADES)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.SPADES),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.ACE, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Flush);
		assertEquals("Flush (Q High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straight() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.EIGHT, CardSuit.SPADES),
				new Card(CardRank.KING, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (K High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_threeOfAKind() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.TWO, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.DIAMONDS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof ThreeOfAKind);
		assertEquals("Trips (2) - Q,J High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_twoPair() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.TWO, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.DIAMONDS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof TwoPair);
		assertEquals("Two Pair (9,2) - Q High", identifiedHand.toString());
	}
	
	/**
	 * Test for K,9 High Two Pairs
	 * @author mikram
	 */
	@Test
	public void identifyHand_twoPair_1() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.KING, CardSuit.SPADES),
				new Card(CardRank.KING, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.ACE, CardSuit.DIAMONDS),
				new Card(CardRank.FIVE, CardSuit.SPADES),
				new Card(CardRank.FIVE, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.SPADES),
				new Card(CardRank.NINE, CardSuit.DIAMONDS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof TwoPair);
		assertEquals("Two Pair (K,9) - A High", identifiedHand.toString());
	}
	

	@Test
	public void identifyHand_onePair() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.TWO, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.THREE, CardSuit.SPADES),
				new Card(CardRank.SIX, CardSuit.DIAMONDS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof OnePair);
		assertEquals("One Pair (2) - Q,J,10 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_highCard() {
		List<Card> playerCards = Arrays.asList(
				new Card(CardRank.ACE, CardSuit.SPADES),
				new Card(CardRank.TWO, CardSuit.CLUBS)
		);

		List<Card> communityCards = Arrays.asList(
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES),
				new Card(CardRank.TEN, CardSuit.SPADES),
				new Card(CardRank.THREE, CardSuit.SPADES),
				new Card(CardRank.SIX, CardSuit.DIAMONDS)
		);

		Hand identifiedHand = cardService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof HighCard);
		assertEquals("A,Q,J,10,6", identifiedHand.toString());
	}

}
