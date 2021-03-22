package com.synacy.poker.hand.comparators;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.types.FourOfAKind;

public class FourOfAKindComparatorTest {

	static FourOfAKindComparator comparator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new FourOfAKindComparator();
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
        List<Card> p1Quads = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );
        List<Card> p1Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p1FourOfAKind = new FourOfAKind(p1Quads, p1Kicker);

        List<Card> p2Quads = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );
        List<Card> p2Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p2FourOfAKind = new FourOfAKind(p2Quads, p2Kicker);
        
        assertEquals(0, comparator.compare(p1FourOfAKind, p2FourOfAKind));
	}
	
	@Test
	public void test_compare_2() {
        List<Card> p1Quads = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.FOUR, CardSuit.HEARTS)
        );
        List<Card> p1Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p1FourOfAKind = new FourOfAKind(p1Quads, p1Kicker);

        List<Card> p2Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p2Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p2FourOfAKind = new FourOfAKind(p2Quads, p2Kicker);
        
        assertTrue(comparator.compare(p1FourOfAKind, p2FourOfAKind) > 0);
	}
	
	@Test
	public void test_compare_3() {
        List<Card> p1Quads = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.SEVEN, CardSuit.HEARTS)
        );
        List<Card> p1Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p1FourOfAKind = new FourOfAKind(p1Quads, p1Kicker);

        List<Card> p2Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p2Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p2FourOfAKind = new FourOfAKind(p2Quads, p2Kicker);
        
        assertTrue(comparator.compare(p1FourOfAKind, p2FourOfAKind) < 0);
	}

	//kicker
	@Test
	public void test_compare_4() {
        List<Card> p1Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p1Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p1FourOfAKind = new FourOfAKind(p1Quads, p1Kicker);

        List<Card> p2Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p2Kicker = Collections.singletonList(
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        FourOfAKind p2FourOfAKind = new FourOfAKind(p2Quads, p2Kicker);
        
        assertTrue(comparator.compare(p1FourOfAKind, p2FourOfAKind) < 0);
	}
	
	//kicker
	@Test
	public void test_compare_6() {
        List<Card> p1Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p1Kicker = Collections.singletonList(
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        FourOfAKind p1FourOfAKind = new FourOfAKind(p1Quads, p1Kicker);

        List<Card> p2Quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );
        List<Card> p2Kicker = Collections.singletonList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        FourOfAKind p2FourOfAKind = new FourOfAKind(p2Quads, p2Kicker);
        
        assertTrue(comparator.compare(p1FourOfAKind, p2FourOfAKind) > 0);
	}
}
