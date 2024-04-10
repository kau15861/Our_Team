/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Scanner;





public class GameLauncher {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain;
        
        do {
            int numPlayers = -1;
        
            // Validate and prompt for a valid number of players
            do {
                System.out.println("Enter the number of players (minimum 2): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Please enter a valid number!");
                    continue;
                }
                try {
                    numPlayers = Integer.parseInt(input);
                    if (numPlayers < 2) {
                        System.out.println("Minimum 2 players required!");
                        numPlayers = -1; // Set numPlayers to an invalid value to continue the loop
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number!");
                    numPlayers = -1; // Set numPlayers to an invalid value to continue the loop 
                }
            } while (numPlayers < 2);
        
            // Proceed with the game setup
            GoFishGame game = new GoFishGame("Go Fish", numPlayers);
            
            // Play the game
            while (!game.gameOver()) {
                game.play();
            }
            
            // Ask if the user wants to play again
            System.out.println("Do you want to play again? (Yes/No): ");
            String playAgainInput = scanner.nextLine().trim().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        } while (playAgain);
    }
}
