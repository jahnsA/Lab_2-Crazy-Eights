import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //initialize section
        Scanner input = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();
        //deal intial hands and put one card on discard pile
        deck.startGame();
        //play rounds of the game until one hand is zero
        deck.playGame(input);
        input.close();
    }//end main method
}//end class Main
