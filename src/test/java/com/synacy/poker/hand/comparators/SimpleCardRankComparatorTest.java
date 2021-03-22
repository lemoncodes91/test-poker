package com.synacy.poker.hand.comparators;

import static org.junit.Assert.*;

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
import com.synacy.poker.hand.types.Straight;

public class SimpleCardRankComparatorTest {

	static SimpleCardRankComparator comparator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new SimpleCardRankComparator();
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
	public void test_compare_straight_test_1() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertEquals(0, comparator.compare(p1Straight, p2Straight));
	}
	
	@Test
	public void test_compare_straight_test_2() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.SPADES),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertTrue(comparator.compare(p1Straight, p2Straight) > 0);
	}

	@Test
	public void test_compare_straight_test_3() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.SPADES),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertTrue(comparator.compare(p1Straight, p2Straight) < 0);
	}

	@Test
	public void test_compare_straight_flush_test_1() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertEquals(0, comparator.compare(p1Straight, p2Straight));
	}
	
	@Test
	public void test_compare_straight_flush_test_2() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertTrue(comparator.compare(p1Straight, p2Straight) > 0);
	}

	@Test
	public void test_compare_straight_flush_test_3() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Straight p1Straight = new Straight(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );
        Straight p2Straight = new Straight(p2Cards);
        
        assertTrue(comparator.compare(p1Straight, p2Straight) < 0);
	}
}
