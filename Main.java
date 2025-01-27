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
        //game loop
        while(!playerHand.isEmpty() && !computerHand.isEmpty()) {
            //player plays 
            System.out.println("Top of the discard pile is " + discardPile.peek()); //two print outs
            //if player doesn't have to take card bc they have no matches, then they can play
            if(!deck.takeCard(playerHand, stackedDeck, discardPile)){
                deck.playCard(playerHand, discardPile, input);
            }//end if
            if(!playerHand.isEmpty()) {//check player didn't just win
                System.out.println("Computer plays...");
                deck.computerPlays(computerHand, discardPile, stackedDeck);
                System.out.println("The computer has " + computerHand.size() + " cards left.\n");
                
                //game ends if computer has 0 cards left
                if(!computerHand.isEmpty()){
                //press y to continue
                deck.pressALetterToContinue(input);
                }//end if
            }//end if
        }//end while game loop

        //the player whose hand is empty wins
        if(playerHand.isEmpty()){
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }//end if/else
        input.close();
    }//end main method
}//end class Main
