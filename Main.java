import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
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

        //game loop
        while(playerHand.size() == 0 || computerHand.size() == 0) {
            //computer plays or you play
            //show hand, give options
            //or pull from deck
        }
        if (playerHand.size() == 0) {
            System.out.println("You Win!");
        }else {
            System.out.println("You Lose!");
        }
        
    }//main method
}//end class Main
