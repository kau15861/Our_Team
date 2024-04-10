package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class GoFishGame extends Game {
    private GroupOfCards deck;
    public ArrayList<GoFishPlayer> players;
    private boolean gameOver;
    private int roundsPlayed;

    public GoFishGame(String name, int numPlayers) {
        super(name);
        players = new ArrayList<>();
        deck = new GroupOfCards(52); 
        initializeTheDeck();
        initializeThePlayers(numPlayers);
        handleCards();
        gameOver = false;
        roundsPlayed = 0;
    }

    private void initializeTheDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.getCards().add(new GoFishCard(rank, suit));
            }
        }
        deck.shuffle();
    }

    private void initializeThePlayers(int numPlayers) {
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
        while (roundsPlayed < 5) {
            System.out.println("\nRound " + (roundsPlayed + 1) + "\n");
            for (GoFishPlayer player : players) {
                if (!gameOver()) {
                    havingTurn(player);
                    showPairs(player);
                } else {
                    break;
                }
            }
            declareRoundWinner();
            roundsPlayed++;
        }
        declareWinner();
    }

    private void havingTurn(GoFishPlayer currentPlayer) {
        System.out.println("Current player: " + currentPlayer.getName());
        System.out.println("Your hand: " + currentPlayer.getHand());

        Scanner scanner = new Scanner(System.in);
        String rankToAskFor;
        do {
            System.out.println("Enter the rank that you want to ask for: ");
            rankToAskFor = scanner.nextLine();
        } while (!checkingRank(rankToAskFor));

        GoFishPlayer otherPlayer = rankedPlayer(rankToAskFor, currentPlayer);

        if (otherPlayer != null) {
            movingCards(currentPlayer, otherPlayer, rankToAskFor);
        } else {
            System.out.println("Go Fish!!!!! Draw a card.....");
            takingCard(currentPlayer);
        }
    }

    public boolean checkingRank(String rank) {
        String[] validRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String validRank : validRanks) {
            if (validRank.equalsIgnoreCase(rank)) {
                return true;
            }
        }
        System.out.println("Invalid rank. Please enter a valid rank.");
        return false;
    }

    private GoFishPlayer rankedPlayer(String rank, GoFishPlayer currentPlayer) {
        for (GoFishPlayer player : players) {
            if (!player.equals(currentPlayer) && playerWithRank(player, rank)) {
                return player;
            }
        }
        return null;
    }

    public boolean playerWithRank(GoFishPlayer player, String rank) {
        for (GoFishCard card : player.getHand()) {
            if (card.toString().contains(rank)) {
                return true;
            }
        }
        return false;
    }

    private void movingCards(GoFishPlayer currentPlayer, GoFishPlayer otherPlayer, String rank) {
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

    private void takingCard(GoFishPlayer currentPlayer) {
        if (!deck.getCards().isEmpty()) {
            GoFishCard card = deck.getCards().remove(0);
            currentPlayer.getHand().add(card);
            System.out.println("You drew: " + card);
        }
    }

    private void showPairs(GoFishPlayer currentPlayer) {
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

    private void declareRoundWinner() {
        for (GoFishPlayer player : players) {
            System.out.println(player.getName() + " made " + countPairs(player) + " pairs in this round.");
        }
    }

    @Override
    public void declareWinner() {
        int maxPairs = 0;
        GoFishPlayer winner = null;

        for (GoFishPlayer player : players) {
            int pairs = countPairs(player);
            if (pairs > maxPairs) {
                maxPairs = pairs;
                winner = player;
            }
        }

        if (maxPairs > 0) {
            System.out.println("Game is over! " + winner.getName() + " is the winner with " + maxPairs + " pairs!");
        } else {
            System.out.println("Game is over! It's a tie!");
        }
        gameOver = true;
    }

    private int countPairs(GoFishPlayer player) {
        int pairs = 0;
        ArrayList<GoFishCard> hand = player.getHand();

        // Create a HashMap to count occurrences of each rank
        HashMap<String, Integer> rankCounts = new HashMap<>();

        // Count occurrences of each rank
        for (GoFishCard card : hand) {
            String rank = card.getRank();
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
        }

        // Check if any rank occurs at least twice (forming a pair)
        for (int count : rankCounts.values()) {
            pairs += count / 2; // Increase pairs count by the number of sets of two
        }

        return pairs;
    }

    public boolean gameOver() {
        return gameOver;
    }
}
