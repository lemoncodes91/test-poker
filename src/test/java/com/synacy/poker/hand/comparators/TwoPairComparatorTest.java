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
import com.synacy.poker.hand.types.TwoPair;

public class TwoPairComparatorTest {

	static TwoPairComparator comparator = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 comparator = new TwoPairComparator();
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

	//First Pair
	@Test
	public void test_compare_1() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertEquals(0, comparator.compare(p1TwoPair, p2TwoPair));
	}
	
	//First Pair
	@Test
	public void test_compare_2() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) > 0);
	}
	
	//First Pair
	@Test
	public void test_compare_3() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) < 0);
	}

	//Second Pair
	@Test
	public void test_compare_4() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) > 0);
	}
	
	//Second Pair
	@Test
	public void test_compare_5() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) < 0);
	}
	
	//Second Pair equal 
	//eval kicker
	@Test
	public void test_compare_6() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) < 0);
	}
	
	//Second Pair equal 
	//eval kicker
	@Test
	public void test_compare_7() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) < 0);
	}
	
	//Second Pair equal 
	//eval kicker
	@Test
	public void test_compare_8() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) < 0);
	}
	
	//Second Pair equal 
	//eval kicker
	@Test
	public void test_compare_9() {
		
		//P1 Hand
        List<Card> p1FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p1SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p1Kicker = Arrays.asList(
        );

        TwoPair p1TwoPair = new TwoPair(p1FirstPair, p1SecondPair, p1Kicker);
        
		//P2 Hand
        List<Card> p2FirstPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> p2SecondPair = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS)
        );
        List<Card> p2Kicker = Arrays.asList(
        		new Card(CardRank.KING, CardSuit.CLUBS)
        );

        TwoPair p2TwoPair = new TwoPair(p2FirstPair, p2SecondPair, p2Kicker);
        
        assertTrue(comparator.compare(p1TwoPair, p2TwoPair) > 0);
	}
}
