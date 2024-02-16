package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class GoFishGame extends Game {
    private GroupOfCards deck;
    private ArrayList<GoFishPlayer> players;
    private boolean gameOver;

    public GoFishGame(String name, int numPlayers) {
        super(name);
        players = new ArrayList<>();
        deck = new GroupOfCards(52); 
        initialize_The_Deck();
        initialize_The_Players(numPlayers);
        handleCards();
        gameOver = false;
    }

    private void initialize_The_Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.getCards().add(new GoFishCard(rank, suit));
            }
        }
        deck.shuffle();
    }

    private void initialize_The_Players(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new GoFishPlayer("Player " + i));
        }
    }

    private void handleCards() {
        final int cardsToDeal = 5;
        for (GoFishPlayer player : players) {
            for (int i = 0; i < cardsToDeal; i++) {
                GoFishCard card = deck.getCards().remove(0);
                player.getHand().add(card);
            }
        }
    }

    @Override
    public void play() {
        for (GoFishPlayer player : players) {
            having_Turn(player);
            show_Pairs(player);
            declareWinner();
        }
        
    }

    private void having_Turn(GoFishPlayer currentPlayer) {
        System.out.println("Current player: " + currentPlayer.getName());
        System.out.println("Your hand: " + currentPlayer.getHand());

        Scanner scanner = new Scanner(System.in);
        String rankToAskFor;
        do {
            System.out.println("Enter the rank that you want to ask for: ");
            rankToAskFor = scanner.nextLine();
        } while (! checking_Rank(rankToAskFor));

        GoFishPlayer otherPlayer =ranked_Player(rankToAskFor, currentPlayer);

        if (otherPlayer != null) {
            moving_Cards(currentPlayer, otherPlayer, rankToAskFor);
        } else {
            System.out.println("Go Fish!!!!! Draw a card.....");
            taking_Card(currentPlayer);
        }
    }

    private boolean checking_Rank(String rank) {
        String[] validRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String validRank : validRanks) {
            if (validRank.equalsIgnoreCase(rank)) {
                return true;
            }
        }
        System.out.println("Invalid rank. Please enter a valid rank.");
        return false;
    }

    private GoFishPlayer ranked_Player(String rank, GoFishPlayer currentPlayer) {
        for (GoFishPlayer player : players) {
            if (!player.equals(currentPlayer) &&  player_with_rank(player, rank)) {
                return player;
            }
        }
        return null;
    }

    private boolean player_with_rank(GoFishPlayer player, String rank) {
        for (GoFishCard card : player.getHand()) {
            if (card.toString().contains(rank)) {
                return true;
            }
        }
        return false;
    }

    private void moving_Cards(GoFishPlayer currentPlayer, GoFishPlayer otherPlayer, String rank) {
        System.out.println(otherPlayer.getName() + " has the requested rank. Take the cards.........");

        ArrayList<GoFishCard> pairs = new ArrayList<>();

        for (int i = otherPlayer.getHand().size() - 1; i >= 0; i--) {
            GoFishCard card = otherPlayer.getHand().get(i);
            if (card.toString().contains(rank)) {
                currentPlayer.getHand().add(card);
                otherPlayer.getHand().remove(card);
                pairs.add(card);
            }
        }

        if (!pairs.isEmpty()) {
            System.out.println("Pairs found by " + currentPlayer.getName() + ": " + pairs);
        }
    }

    private void taking_Card(GoFishPlayer currentPlayer) {
        if (!deck.getCards().isEmpty()) {
            GoFishCard card = deck.getCards().remove(0);
            currentPlayer.getHand().add(card);
            System.out.println("You drew: " + card);
        }
    }

    private void show_Pairs(GoFishPlayer currentPlayer) {
        ArrayList<GoFishCard> hand = currentPlayer.getHand();
        ArrayList<GoFishCard> pairs = new ArrayList<>();

        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).toString().equals(hand.get(j).toString())) {
                    pairs.add(hand.get(i));
                    pairs.add(hand.get(j));
                }
            }
        }

        if (!pairs.isEmpty()) {
            System.out.println("Pairs found by " + currentPlayer.getName() + ": " + pairs);
        }
    }

    @Override
    public void declareWinner() {
        for (GoFishPlayer player : players) {
            if (player.getHand().isEmpty()) {
                System.out.println("Game is over! " + player.getName() + " is the winner!");
                gameOver = true;
                return;
            }
        }
    }

    public boolean gameOver() {
        return gameOver;
    }

    
}
