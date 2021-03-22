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
import com.synacy.poker.hand.types.ThreeOfAKind;

public class ThreeOfAKindComparatorTest {

	static ThreeOfAKindComparator comparator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparator = new ThreeOfAKindComparator();
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
	public void test_three_of_a_kind_1() {
        List<Card> trips1 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers1 = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind1 = new ThreeOfAKind(trips1, kickers1);
        
        List<Card> trips2 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers2 = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind2 = new ThreeOfAKind(trips2, kickers2);
        
        assertEquals(0, comparator.compare(threeOfAKind1, threeOfAKind2));
	}
	
	@Test
	public void test_three_of_a_kind_2() {
        List<Card> trips1 = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.SPADES)
        );
        List<Card> kickers1 = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind1 = new ThreeOfAKind(trips1, kickers1);
        
        List<Card> trips2 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers2 = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind2 = new ThreeOfAKind(trips2, kickers2);
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) > 0);
	}
	
	@Test
	public void test_three_of_a_kind_3() {
        List<Card> trips1 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers1 = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind1 = new ThreeOfAKind(trips1, kickers1);
        
        List<Card> trips2 = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.SPADES)
        );
        List<Card> kickers2 = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind2 = new ThreeOfAKind(trips2, kickers2);
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) < 0);
	}

	
	//kickers
	@Test
	public void test_three_of_a_kind_kickers_1() {
        List<Card> trips1 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers1 = Arrays.asList(
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind1 = new ThreeOfAKind(trips1, kickers1);
        
        List<Card> trips2 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers2 = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind2 = new ThreeOfAKind(trips2, kickers2);
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) > 0);
	}
	
	//kickers
	@Test
	public void test_three_of_a_kind_kickers_2() {
        List<Card> trips1 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers1 = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind1 = new ThreeOfAKind(trips1, kickers1);
        
        List<Card> trips2 = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> kickers2 = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        ThreeOfAKind threeOfAKind2 = new ThreeOfAKind(trips2, kickers2);
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) < 0);
	}
}
