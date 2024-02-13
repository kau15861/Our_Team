package ca.sheridancollege.project;

import java.util.ArrayList;

public class GoFishPlayer extends Player {
    private ArrayList<GoFishCard> hand;

    public GoFishPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public ArrayList<GoFishCard> getHand() {
        return hand;
    }

    // Implement additional methods or properties as needed for GoFishPlayer

    @Override
    public void play() {
        // Implement the logic for a player's turn in Go Fish
    }
}