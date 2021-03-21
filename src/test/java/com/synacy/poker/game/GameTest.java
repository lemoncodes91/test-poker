package com.synacy.poker.game;

import com.synacy.poker.deck.DeckBuilder;
import com.synacy.poker.hand.HandIdentifier;
import com.synacy.poker.hand.WinningHandCalculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {

	@Autowired
	HandIdentifier handIdentifier;
	
	@Autowired
	WinningHandCalculator winningHandCalculator;
	
	
    @Test
    public void afterConstructorInit_eachPlayerHasTwoCards() {
        DeckBuilder deckBuilder = new DeckBuilder();

        Game game = new Game(deckBuilder, handIdentifier, winningHandCalculator);

        assertPlayersHaveTwoCardsEach(game);
    }

    @Test
    public void startNewGame_eachPlayerHasTwoCards() {
        DeckBuilder deckBuilder = new DeckBuilder();

        Game game = new Game(deckBuilder, handIdentifier, winningHandCalculator);

        assertPlayersHaveTwoCardsEach(game);
    }

    private void assertPlayersHaveTwoCardsEach(Game game) {
        game.getPlayers().forEach(player ->
                assertEquals("Players should have 2 cards each",
                        2,
                        player.getHand().size()));
    }

    @Test
    public void nextAction_dealCommunityCards() {
        DeckBuilder deckBuilder = new DeckBuilder();

        Game game = new Game(deckBuilder, handIdentifier, winningHandCalculator);

        game.nextAction();
        assertEquals("Deal three community cards at the start", 3, game.getCommunityCards().size());

        game.nextAction();
        assertEquals("Expecting four community cards", 4, game.getCommunityCards().size());

        game.nextAction();
        assertEquals("Expecting 5 community cards", 5, game.getCommunityCards().size());
    }
}
