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
        System.out.println("Discard Pile: " + discardPile);

        //initial draw
        for (int i = 0; i < 5; i++) {
            deck.drawCard(playerHand, stackedDeck);
        }
        System.out.println("Player hand: " + playerHand);
        
        /*for (int i = 0; i < 5; i++) {
            computerHand.add(deck.dealCard());
        }
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.dealCard());
        }
        //TEST CODE
        /*System.out.println("Computer hand: " + computerHand);
        System.out.println("Player hand: " + playerHand);*/
        
    }//main method
}//end class Main
