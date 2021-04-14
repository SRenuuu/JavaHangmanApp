package hangmanapplication;

import java.io.IOException;
import java.util.Scanner;

public class HangmanApplication {

    public static void main(String[] args) throws IOException{
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Hangman, try to guess the word that I pick, you have 6 tries!");
        System.out.println("Are you ready? I hope so, because I am!\n");
        System.out.println("I have picked my word. Below is a picture and below that is your current guess,\nwhich starts off as nothing."
                + " Everytime you guess incorrectly, I add a body part to the picture.\nWhen there is a full person, you lose!");
        // Allows for multiple games
        boolean keepPlaying = true;
        
        while(keepPlaying){
            // Setting up the game
            System.out.println("Alright! Let's play...");
            Hangman game = new Hangman();
            do{
                //Draw the things
                System.out.println("");
                System.out.println(game.drawPicture());
                System.out.println(game.getFormalCurrentGuess());
//                System.out.println(game.mysteryWord);
                
                //Get guess from user
                System.out.print("Enter a character that you think is in the word: ");
                char guess = (sc.next().toLowerCase()).charAt(0);
                
                //Check if character was guessed already
                while(game.isGuessedAlready(guess)){
                    System.out.print("Try again! You've already guessed that character.");
                    guess = (sc.next().toLowerCase()).charAt(0);
                }
                
                //Check guess
                if (game.isGuessCorrect(guess)){
                    System.out.println("Great guess! That character is in the word.");
                    
                }else{
                    System.out.println("Unfortunately, that character is not in the word!");
                }
                
                //Playing the game
                
                              
            }
            while(!game.gameOver());
            System.out.println();
            System.out.println("Do you want to play another game? Enter Y if you do.");
            Character response = (sc.next().toUpperCase()).charAt(0);
            keepPlaying = (response == 'Y');
            
            
        }
    }
    
}
