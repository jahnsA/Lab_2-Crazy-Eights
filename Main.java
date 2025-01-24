import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        //deal player and computer 5 cards
        ArrayList<Card> computerHand = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        for (int i = 0; i < 5; i++) {
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
