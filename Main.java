import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        //initialize section
        Scanner input = new Scanner(System.in);
        Stack<Card> stackedDeck = new Stack<>();
        ArrayList<Card> computerHand = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        Stack<Card> discardPile = new Stack<>();
        DeckOfCards deck = new DeckOfCards();

        //print introduction
        deck.explainGame();

        //switch deck from array to stack
        deck.stackDeck(stackedDeck);

        //add first card to discard pile
        discardPile.push(stackedDeck.pop());

        //player initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            playerHand.add(stackedDeck.pop());
        }
        //computer initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            computerHand.add(stackedDeck.pop());
        }

        //loop until player or computer hand is 0
        while(!playerHand.isEmpty() && !computerHand.isEmpty()) {
            System.out.println("Top of the discard pile is " + discardPile.peek());
            //if player has no playable cards, draw one, then computer plays
            while(deck.takeCard(playerHand, stackedDeck, discardPile) == true) {
                deck.computerPlays(computerHand, discardPile, stackedDeck);
            }
            //user plays card
            deck.playCard(playerHand, discardPile, input);
            //computer plays
            deck.computerPlays(computerHand, discardPile, stackedDeck);
            System.out.println("The computer has " + computerHand.size() + " cards left.\n");
        }//end while loop
        if(playerHand.isEmpty()){
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }//end main method
}//end class Main
