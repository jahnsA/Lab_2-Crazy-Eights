import java.util.ArrayList;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        //initialize stacked deck
        Stack<Card> stackedDeck = new Stack<>();

        DeckOfCards deck = new DeckOfCards();
        deck.stackDeck(stackedDeck);
        //deal player and computer 5 cards
        ArrayList<Card> computerHand = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        Stack<Card> discardPile = new Stack<>();

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
        System.out.println("Computer hand: " + computerHand);
        
    }//end main method
}//end class Main
