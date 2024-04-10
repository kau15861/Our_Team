package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

public class GoFishGameTest {

    @Test
    public void testCheckingRankWithGoodInput() {
        GoFishGame game = new GoFishGame("Test Game", 2);
        boolean result = game.checkingRank("Jack");// with good input 
        assertEquals(true, result);
    }

    @Test
    public void testCheckingRankWithBadInput() {
        GoFishGame game = new GoFishGame("Test Game", 2);
        boolean result = game.checkingRank("invalid");// with bad input enter other than cards
        assertEquals(false, result);
    }
    
    @Test
    public void testCheckingRankWithBoundaryInput() {
        GoFishGame game = new GoFishGame("Test Game", 2);
        boolean result = game.checkingRank(" ");// no value
        assertEquals(false, result);
    }
    
    @Test
    public void testPlayerWithRankWithGoodInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        GoFishPlayer player = new GoFishPlayer("Test Player");
        player.getHand().add(new GoFishCard("5", "Hearts"));
        boolean result = game.playerWithRank(player, "5");
        assertEquals(true, result);
    }

    @Test
    public void testPlayerWithRankWithBadInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        GoFishPlayer player = new GoFishPlayer("Test Player");
        player.getHand().add(new GoFishCard("Jack", "Spades"));
        boolean result = game.playerWithRank(player, "10");
        assertEquals(false, result);
    }

    @Test
    public void testPlayerWithRankWithBoundaryInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        GoFishPlayer player = new GoFishPlayer("Test Player");
        boolean result = game.playerWithRank(player, "2");
        assertEquals(false, result); // Assuming player's hand is empty
    }
    
    @Test
    public void testGameOverWithGoodInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        boolean result = game.gameOver();
        assertEquals(false, result);
    }
    
    @Test
    public void testGameOverWithBadInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        boolean result = game.gameOver();
        assertEquals(false, result); // Expected to be false since the game should not be over
    }
    
    @Test
    public void testGameOverWithBoundaryInput() {
        GoFishGame game = new GoFishGame("Go Fish", 4);
        GoFishPlayer player1 = new GoFishPlayer("Test Player 1");
        GoFishPlayer player2 = new GoFishPlayer("Test Player 2");

        // Let player 1 have some cards
        player1.getHand().add(new GoFishCard("King", "Spades"));
        game.players.add(player1);

        // Let player 2 have an empty hand
        player2.getHand().clear();
        game.players.add(player2);

        boolean result = game.gameOver();
        assertEquals(false, result); // Expected to be false since only one player has cards
    }  
}
