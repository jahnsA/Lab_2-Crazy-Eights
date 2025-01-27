import java.util.ArrayList;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        //initialize section
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
        System.out.println("Discard Pile: " + discardPile.peek());

        //player initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            deck.drawCard(playerHand, stackedDeck);
        }
        System.out.println("Your hand:");
        deck.displayHand(playerHand);
        //computer initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            deck.drawCard(computerHand, stackedDeck);
        }

        //loop until player or computer hand is 0
        while(!playerHand.isEmpty() && !computerHand.isEmpty()) {
            //if player has no playable cards, draw one, then computer plays
            if(deck.takeCard(playerHand, stackedDeck, discardPile) == true) {
                deck.computerPlays(computerHand, discardPile, stackedDeck);
            }
            //user plays card
            deck.playCard(playerHand, discardPile);
            //computer plays
            System.out.println("The computer plays...");
            deck.computerPlays(computerHand, discardPile, stackedDeck);
        }
        if(playerHand.isEmpty()){
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }//end main method
}//end class Main
