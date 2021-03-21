package com.synacy.poker.hand;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinningHandCalculatorTest {
	
	@Autowired
	WinningHandCalculator calculator;

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

	//1 winner
	@Test
	public void test_winning_1_winner() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_Pair());
		//Player2
		winningHands.add(prepHand_TwoPair());
		//Player3
		winningHands.add(prepHand_HighCard());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 1 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
				  .allMatch(hand -> winningHands.stream()
						  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
						  												&& wHand.getCardValues() == hand.getCardValues())));
	}

	//2 tie, split pot test 1
	@Test
	public void test_winning_2_winner_tie_split_pot_1() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_Pair());
		//Player2
		winningHands.add(prepHand_TwoPair());
		//Player3
		winningHands.add(prepHand_TwoPair());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(2, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}

	//scenario One Pair
	@Test
	public void test_winning_2_winner_tie_split_pot_2() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_Pair_high_5());
		//Player3
		winningHands.add(prepHand_Pair_high_6());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_high_Q() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.CLUBS)
        );

        return new HighCard(cards);
	}
	
	private Hand prepHand_Pair_high_5() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS)
        );

        return new OnePair(firstPair, kicker);
	}
	
	private Hand prepHand_Pair_high_6() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS)
        );

        return new OnePair(firstPair, kicker);
	}
	
	//scenario Two Pair
	@Test
	public void test_winning_2_winner_tie_split_pot_3() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_TwoPair_high_Q());
		//Player3
		winningHands.add(prepHand_TwoPair_high_J());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_TwoPair_high_Q() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        
        List<Card> secondPair = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        return new TwoPair(firstPair, secondPair, kicker);
	}
	
	private Hand prepHand_TwoPair_high_J() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        
        List<Card> secondPair = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );

        return new TwoPair(firstPair, secondPair, kicker);
	}
	
	//scenario Three Pair
	@Test
	public void test_winning_2_winner_tie_split_pot_4() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_ThreePair_high_K());
		//Player3
		winningHands.add(prepHand_ThreePair_high_Q());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_ThreePair_high_K() {
        List<Card> threeOfAKind = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );

        return new ThreeOfAKind(threeOfAKind, kicker);
	}
	
	private Hand prepHand_ThreePair_high_Q() {
        List<Card> threeOfAKind = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );

        return new ThreeOfAKind(threeOfAKind, kicker);
	}
	
	//scenario Full House
	@Test
	public void test_winning_2_winner_tie_split_pot_5() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_FullHouse_high_K());
		//Player3
		winningHands.add(prepHand_FullHouse_high_Q());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_FullHouse_high_K() {
        List<Card> threeOfAKind = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        
        List<Card> pair = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        return new ThreeOfAKind(threeOfAKind, pair);
	}
	
	private Hand prepHand_FullHouse_high_Q() {
        List<Card> threeOfAKind = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        
        List<Card> pair = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        return new ThreeOfAKind(threeOfAKind, pair);
	}
	
	//scenario quads
	@Test
	public void test_winning_2_winner_tie_split_pot_6() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_Quads_high_Q());
		//Player3
		winningHands.add(prepHand_Quads_high_K());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_Quads_high_Q() {
        List<Card> quads = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        return new FourOfAKind(quads, kicker);
	}
	
	private Hand prepHand_Quads_high_K() {
        List<Card> quads = Arrays.asList(
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS)
        );

        return new FourOfAKind(quads, kicker);
	}
	
	//scenario scene1
	@Test
	public void test_winning_2_winner_tie_split_pot_7() {
		List<Hand> playerHands = new ArrayList<Hand>();
		List<Hand> winningHands = new ArrayList<Hand>();
		//Player1
		playerHands.add(prepHand_high_Q());
		//Player2
		playerHands.add(prepHand_OnePair_high_AQJ());
		//Player3
		winningHands.add(prepHand_Trips_high_J10());
		
		playerHands.addAll(winningHands);
		
		List<Hand> winningHand = calculator.calculateWinningHand(playerHands).orElse(Collections.emptyList());
		
		//assert 2 winner
		assertEquals(1, winningHand.size());
		assertTrue(winningHand.stream()
							  .allMatch(hand -> winningHands.stream()
									  						.anyMatch(wHand -> wHand.getHandType() == hand.getHandType() 
									  												&& wHand.getCardValues() == hand.getCardValues())));
	}
	
	private Hand prepHand_OnePair_high_AQJ() {
        List<Card> pair = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.JACK, CardSuit.DIAMONDS)
        );

        return new OnePair(pair, kicker);
	}
	
	private Hand prepHand_Trips_high_J10() {
        List<Card> quads = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS)
        );
        
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );

        return new ThreeOfAKind(quads, kicker);
	}
	
	private Hand prepHand_Pair() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );

        return new OnePair(firstPair, kicker);
	}
	
	private Hand prepHand_TwoPair() {
        List<Card> firstPair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );
        List<Card> secondPair = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.DIAMONDS)
        );
        List<Card> kicker = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        return new TwoPair(firstPair, secondPair, kicker);
	}
	
	private Hand prepHand_FullHouse() {
        List<Card> trips = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.SPADES)
        );
        List<Card> pair = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        return new FullHouse(trips, pair);
	}
	
	private Hand prepHand_HighCard() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        return new HighCard(cards);
	}
}
