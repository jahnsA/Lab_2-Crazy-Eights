import java.util.ArrayList;
//import java.util.Scanner;
import java.util.Stack;

public class SarahMethods {
    //explain game

    public static void main(String[] args) {
        //initialize section
        Stack<Card> stackedDeck = new Stack<>();
        ArrayList<Card> computerHand = new ArrayList<Card>();
        //ArrayList<Card> playerHand = new ArrayList<Card>();
        Stack<Card> discardPile = new Stack<>();
        DeckOfCards deck = new DeckOfCards();

        //switch deck from array to stack
        deck.stackDeck(stackedDeck);

        //add first card to discard pile
        discardPile.push(stackedDeck.pop());
        System.out.println("Discard Pile: " + discardPile.peek());

        Card test = new Card(Face.ACE, Suit.CLUBS);
        test = discardPile.peek(); //initilaze the thing to the top of the discard pile

        //computer initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            deck.drawCard(computerHand, stackedDeck);
        }
        
        for (int i = 0; i<5; i++) {
            deck.displayHand(computerHand);
            deck.computerPlays(computerHand, discardPile, stackedDeck);
        }
        

        

        
    }
    

    //method where computer plays
    public void computerPlays(ArrayList<Card> hand, Stack<Card> discardPile, Card mainCard) {
        
        //if checks method was false, then draw 1 card
        int heartsCheck = 0;
        int diamondsCheck = 0;
        int clubsCheck = 0;
        int spadesCheck = 0;

        //play the first card in the array that matches to the discardPile top card
        for(int i = 0; i < hand.size(); i++) {
            //have a check for the eight
            switch (hand.get(i).getSuit()) {
                case HEARTS:
                    heartsCheck++;
                    break;
                
                case DIAMONDS:
                    diamondsCheck++;
                    break;
                
                case CLUBS:
                    clubsCheck++;
                    break;
                
                case SPADES:
                    spadesCheck++;
                    break;

                default:
                    break;
            }
        
            if (hand.get(i).getFace() == Face.EIGHT) {

                if ((heartsCheck >= diamondsCheck) && (heartsCheck >= clubsCheck) && (heartsCheck >= spadesCheck) ) {
                    //there are more hearts in the computer's hand
                    discardPile.peek().setSuit(Suit.HEARTS);
                } else if ((diamondsCheck >= heartsCheck) && (diamondsCheck >= clubsCheck) && (diamondsCheck >= spadesCheck)) {
                    //there are more diamonds in the computer's hand
                    discardPile.peek().setSuit(Suit.DIAMONDS);
                } else if ((clubsCheck >= heartsCheck) && (clubsCheck >= diamondsCheck) && (clubsCheck >= spadesCheck)) {
                    //there are more clubs in the computer's hand
                    discardPile.peek().setSuit(Suit.CLUBS);
                } else if ((spadesCheck >= heartsCheck) && (spadesCheck >= diamondsCheck) && (spadesCheck >= clubsCheck)) {
                    //there are more spades in the computer's hand
                    discardPile.peek().setSuit(Suit.SPADES);
                }
                
                discardPile.push(hand.get(i));
                System.out.printf("The top of the discard pile is %s \n", discardPile.peek());
                hand.remove(i);
            }
            else if ((hand.get(i).getFace() == mainCard.getFace()) || (hand.get(i).getSuit() == mainCard.getSuit())){
                //if the face or the suit matches, play that card
                discardPile.push(hand.get(i));
                System.out.printf("The top of the discard pile is %s \n", discardPile.peek());
                hand.remove(i);
            }
            //check to see if the computer can play
            
            //add switch to count for the amount of suits there are in the computer's hand 
            
            
        //if plays an eight, whatver it has the most of, change it to that suit
        }
    }

    
    public void explainGame(){
        System.out.println("Welcome to Crazy 8's where the first person to get rid of all their cards is the winner!\n" +
        "The first card of the main deck will be pulled and visible to everyone. The will be the Main Card.\n" +
        "\nThe user will go first and you can either voluntarily pull a card from the deck or play a card.\n" +
        "To play a card, it must match the Main Card in suit or face.\n" +
        "For example, if the Main Card is a 7 of hearts, you can only play a card that is a 7 or is a hearts card.\n" +
        "\nAs per the name of the game, eights are wild!\n" +
        "If you play an eight, you can change the suit of the Main Card.\n" +
        "\nIf unable to play, a card will be pulled from the top of the deck until you get a playable card.\n");

       
    }
    //display cards

    public void displayHand(ArrayList<Card> DeckOfCards) {
        
        for (int i = 0; i < DeckOfCards.size(); i++) {
            //print array
            System.out.println(DeckOfCards.get(i));
        }

    }



}
