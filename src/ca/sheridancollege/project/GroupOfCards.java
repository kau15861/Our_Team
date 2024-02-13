/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

// Inside GroupOfCards.java

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT: You might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    // The group of cards, stored in an ArrayList
    private ArrayList<GoFishCard> cards; // Change this line

    private int size; // the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        cards = new ArrayList<>(); // Change this line
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<GoFishCard> getCards() { // Change this line
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

    // Implement other methods as needed for GoFishCard
}

