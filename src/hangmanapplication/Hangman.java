package hangmanapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    String mysteryWord;
    StringBuilder currentGuess;
    ArrayList <Character> previousGuesses = new ArrayList<>();
    
    int maxTryCount = 6;
    int currentTry = 0;
    
    ArrayList <String> wordDict = new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    
    public Hangman() throws IOException{
        initializeStream();
        mysteryWord = pickWord();
        currentGuess = initializeCurrentGuess();
        
    }
    
    public void initializeStream() throws IOException{
        try {
            File file = new File(".\\word_dict\\dictionary1.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while(line != null){
                wordDict.add(line);
                        
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
            
        }catch (IOException e){
            System.out.println("Could not initialize stream");
        }
    }
        
    public String pickWord(){
        Random rand = new Random();
        int wordIndex = Math.abs(rand.nextInt() % wordDict.size());
        return wordDict.get(wordIndex);     
    }
    
    public StringBuilder initializeCurrentGuess(){
        StringBuilder current = new StringBuilder();
        for (int i = 0 ; i < mysteryWord.length()*2; i++){
            if(i % 2 == 0){
                current.append("_");
            }else{
                current.append(" ");
            }
        }
        return current;
    }
    
    public String getFormalCurrentGuess(){
        return "Current Guess: "+currentGuess.toString();
    }
    
    public boolean isGuessedAlready(char guess) {
        return previousGuesses.contains(guess);
    }
    
    boolean isGuessCorrect(char guess) {
        boolean correctGuess = false;
        for(int i=0;i<mysteryWord.length();i++){
            if(mysteryWord.charAt(i)==guess){
                currentGuess.setCharAt(i*2, guess);
                correctGuess=true;
                
            }
        }
        if(!correctGuess){
            currentTry++;
        }
        previousGuesses.add(guess);
                
        return correctGuess;
    }
    
    public String drawPicture(){
        switch(currentTry){
            case 0 : return drawHang();
            case 1 : return drawHead();
            case 2 : return drawLeftHand();
            case 3 : return drawRightHand();                    
            case 4 : return drawLeftLeg();
            default : return drawFullMan();
        }
    
    }
    
    public boolean gameOver() {
        if (didWin()){
            System.out.println();
            System.out.println("Congrats! You won! You guessed the right word!");
            return true;
            
        }else if (didLose()) {
            System.out.println();
            System.out.println("Sorry, you lost! You spent all your 6 tries. The mystery word is "+mysteryWord);
            return true;
        }
        return false;
    }
        
    private boolean didWin() {
        String guess = getCurrentGuessWithoutSpaces();
        return guess.equals(mysteryWord);
    }

    private boolean didLose() {
        return currentTry>=maxTryCount;
    }
    
    
    private String getCurrentGuessWithoutSpaces() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");
    }


    private String drawHang() {
        return  " - - - - -\n"+
                "|         \n"+
                "|         \n" +
                "|         \n"+
                "|         \n" +
                "|         \n" +
                "|\n" +
                "|\n";
        
    }

    private String drawHead() {
         return  " - - - - -\n"+
                "|         |\n"+
                "|         O\n" +
                "|          \n"+
                "|          \n" +
                "|          \n" +
                "|\n" +
                "|\n";
    }

    private String drawLeftHand() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       /|\n"+
                "|         \n" +
                "|         \n" +
                "|\n" +
                "|\n";
    }

    private String drawRightHand() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       /|\\\n"+
                "|          \n" +
                "|          \n" +
                "|\n" +
                "|\n";
    }

    private String drawLeftLeg() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       /|\\\n"+
                "|        |\n" +
                "|       / \n" +
                "|\n" +
                "|\n";
    }


    private String drawFullMan() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       /|\\\n"+
                "|        |\n" +
                "|       / \\\n" +
                "|\n" +
                "|\n";
    }



}
