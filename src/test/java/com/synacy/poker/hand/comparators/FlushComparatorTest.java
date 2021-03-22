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
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.Straight;

public class FlushComparatorTest {
	static FlushComparator comparator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new FlushComparator();
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
	public void test_compare_flush_test_1() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Flush p1Flush = new Flush(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Flush p2Flush = new Flush(p2Cards);
        
        assertEquals(0, comparator.compare(p1Flush, p2Flush));
	}
	
	@Test
	public void test_compare_flush_test_2() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Flush p1Flush = new Flush(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Flush p2Flush = new Flush(p2Cards);
        
        assertTrue(comparator.compare(p1Flush, p2Flush) < 0);
	}
	
	@Test
	public void test_compare_flush_test_3() {
        List<Card> p1Cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );
        Flush p1Flush = new Flush(p1Cards);
        
        List<Card> p2Cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS)
        );
        Flush p2Flush = new Flush(p2Cards);
        
        assertTrue(comparator.compare(p1Flush, p2Flush) > 0);
	}
}
