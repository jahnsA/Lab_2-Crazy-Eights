//Abby Jahns
//01.24.25
//seperate work space for code
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class AbbyMethods {

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        
        deck.stackDeck();
        ArrayList<Card> computerHand = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> discardPile = new ArrayList<Card>();

        //initial draw
        for (int i = 0; i < 5; i++) {
            deck.drawCard(playerHand);
        }
        System.out.println("Player hand: " + playerHand);
        
        playCard(playerHand, discardPile);

        /*game loop 
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
        */
        
    }//end main method

    /* not tested!
    //check if player can play any card
    //if not, then pull 1 card from deck
    public static void checkCards(ArrayList<Card> hand, Stack<Card> deck) {
        boolean takeCard = false;
        for (int i = 0; i > hand.size() - 1; i++) {
            if (hand.get(i).getFace() != topDiscard.getFece() || 
                    hand.get(i).getSuit() != topDiscard.getSuit()) {
                takeCard = true;
            }
        }//end for loop
        if (takeCard) {
            System.out.println("You have no matches. 1 card is drawn from deck.");
            hand.add(deck.pop());
        }
    }//end checkCards method
    */

    //user enters card they want to play
    //check that it is a valid play (both face/suit and in hand)
    //add to discard pile and remove from player hand
    public static void playCard(ArrayList<Card> hand, ArrayList<Card> discard) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean runAgain = true;
        //user error try/catch
        while (runAgain) {
            try {
                System.out.print("What card do you to play? (Input a number): ");
                choice = input.nextInt();     
                System.out.printf("You input %d for card %s.%n", 
                    choice, hand.get(choice - 1).toString());
                runAgain = false;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a number 1 - " + hand.size());
                runAgain = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Enter a number.");
                input.nextLine(); //consumes rest of text
                runAgain = true;
            }
            //if doesn't match discard card, then error message
            /*
            //!need card value for top discard stored somewhere accessable in DeckOfCards.java!
            //assuming that if there is no playable card this method will not be called
            //need different method called checkIfNoMatch, or something like that
            if(topDiscard.getFace != hand.get(choice-1).getFace() || 
                    topDiscard.getSuit != hand.get(choice-1).getSuit()) {
                System.out.println("Invalid play. Enter card with same Face or Suit of " + 
                        topDiscard.toString());
                runAgain = true;
            }
            */
        }//end while statement

        //if passes all of those, then remove card from hand and add to discard pile
        discard.add(hand.get(choice-1));
        hand.remove(choice-1);
        System.out.println("Your hand: " + hand);

    }//end playCard method
}//end class methods
