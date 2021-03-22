package com.synacy.poker.hand.comparators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.types.OnePair;

public class OnePairComparatorTest {

	static OnePairComparator comparator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new OnePairComparator();
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
	public void test_compare_1() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.TWO, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.ACE, CardSuit.CLUBS),
				new Card(CardRank.KING, CardSuit.DIAMONDS), new Card(CardRank.QUEEN, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.TWO, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.ACE, CardSuit.CLUBS),
				new Card(CardRank.KING, CardSuit.DIAMONDS), new Card(CardRank.QUEEN, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertEquals(0, comparator.compare(p1OnePair, p2OnePair));
	}

	@Test
	public void test_compare_2() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.FOUR, CardSuit.CLUBS),
				new Card(CardRank.FOUR, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.SEVEN, CardSuit.CLUBS),
				new Card(CardRank.EIGHT, CardSuit.DIAMONDS), new Card(CardRank.NINE, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) > 0);
	}

	@Test
	public void test_compare_3() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.QUEEN, CardSuit.CLUBS),
				new Card(CardRank.QUEEN, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.ACE, CardSuit.CLUBS),
				new Card(CardRank.JACK, CardSuit.DIAMONDS), new Card(CardRank.TEN, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) < 0);
	}

	// for kickers
	@Test
	public void test_compare_4() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.ACE, CardSuit.CLUBS),
				new Card(CardRank.JACK, CardSuit.DIAMONDS), new Card(CardRank.TEN, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) < 0);
	}

	// for kickers
	@Test
	public void test_compare_5() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.ACE, CardSuit.CLUBS),
				new Card(CardRank.JACK, CardSuit.DIAMONDS), new Card(CardRank.TEN, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) > 0);
	}

	// for kickers
	@Test
	public void test_compare_6() {
		List<Card> p1Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p1Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		List<Card> p2Kickers = Arrays.asList(new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS), new Card(CardRank.FOUR, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) == 0);
	}
	
	// for kickers high A
	@Test
	public void test_compare_7() {
		List<Card> p1Pair = Arrays.asList(
				new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		
		List<Card> p1Kickers = Arrays.asList(
				new Card(CardRank.KING, CardSuit.CLUBS),
				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
				new Card(CardRank.JACK, CardSuit.SPADES));

		OnePair p1OnePair = new OnePair(p1Pair, p1Kickers);

		List<Card> p2Pair = Arrays.asList(
				new Card(CardRank.TEN, CardSuit.CLUBS),
				new Card(CardRank.TEN, CardSuit.HEARTS));
		
		List<Card> p2Kickers = Arrays.asList(
				new Card(CardRank.TWO, CardSuit.CLUBS),
				new Card(CardRank.THREE, CardSuit.DIAMONDS),
				new Card(CardRank.ACE, CardSuit.SPADES));

		OnePair p2OnePair = new OnePair(p2Pair, p2Kickers);

		assertTrue(comparator.compare(p1OnePair, p2OnePair) > 0);
	}
}
