/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Scanner;

/**
 *
 * @author OWNER
 */
public class GameLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        GoFishGame game = new GoFishGame("Go Fish", numPlayers);

        while (!game.gameOver()) {
            game.play();
        }
    }
    
}
