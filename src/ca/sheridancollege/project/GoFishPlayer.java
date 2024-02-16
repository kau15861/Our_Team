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

    

    @Override
    public void play() {
        
    }
}