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
import com.synacy.poker.hand.types.FullHouse;

public class FullHouseComparatorTest {
	
	static FullHouseComparator comparator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new FullHouseComparator();
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
        List<Card> p1Trips = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> p1Pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        FullHouse p1FullHouse = new FullHouse(p1Trips, p1Pair);
        
        List<Card> p2Trips = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> pw2Pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        FullHouse p2FullHouse = new FullHouse(p2Trips, pw2Pair);
        
        assertEquals(0, comparator.compare(p1FullHouse, p2FullHouse));
	}

	@Test
	public void test_compare_2() {
        List<Card> p1Trips = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> p1Pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        FullHouse p1FullHouse = new FullHouse(p1Trips, p1Pair);
        
        List<Card> p2Trips = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );
        List<Card> pw2Pair = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.HEARTS)
        );

        FullHouse p2FullHouse = new FullHouse(p2Trips, pw2Pair);
        
        assertTrue(comparator.compare(p1FullHouse, p2FullHouse) > 0);
	}
	
	@Test
	public void test_compare_3() {
        List<Card> p1Trips = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );
        List<Card> p1Pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        FullHouse p1FullHouse = new FullHouse(p1Trips, p1Pair);
        
        List<Card> p2Trips = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );
        List<Card> pw2Pair = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.HEARTS)
        );

        FullHouse p2FullHouse = new FullHouse(p2Trips, pw2Pair);
        
        assertTrue(comparator.compare(p1FullHouse, p2FullHouse) < 0);
	}
	
	@Test
	public void test_compare_4() {
        List<Card> p1Trips = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );
        List<Card> p1Pair = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.HEARTS)
        );

        FullHouse p1FullHouse = new FullHouse(p1Trips, p1Pair);
        
        List<Card> p2Trips = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );
        List<Card> pw2Pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        FullHouse p2FullHouse = new FullHouse(p2Trips, pw2Pair);
        
        assertTrue(comparator.compare(p1FullHouse, p2FullHouse) > 0);
	}
}
