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

        //switch deck from array to stack
        deck.stackDeck(stackedDeck);

        //add first card to discard pile
        discardPile.push(stackedDeck.pop());
        System.out.println("Discard Pile: " + discardPile.peek());

        //player initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            deck.drawCard(playerHand, stackedDeck);
        }
        System.out.println("Player hand: " + playerHand);
        //computer initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            deck.drawCard(computerHand, stackedDeck);
        }
    }//end main method
}//end class Main
