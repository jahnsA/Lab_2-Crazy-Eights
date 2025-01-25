import java.security.SecureRandom;
import java.util.ArrayList; //for random numbers
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class DeckOfCards {

    //random number generator  ---> from Darrell's program.
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;


    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references
    private int currentCard = 0; //index of next Card to be dealt (0 -51)

     // Constructor fills deck of cards
     public DeckOfCards() {
        // Populate deck with Card objects using Face and Suit enums
        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(Face.values()[count % 13], Suit.values()[count / 13]);
        }
    } 
    //shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        //next call to method dealCard should start at deck[0] again
        currentCard = 0;

        for (int first = 0; first < deck.length; first++) {
            //select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            //swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }//end for loop
    }//end of shuffle method

    //deals one card
    //can delete later
    /*public Card dealCard() {  
        //determine whether Cards remain to be dealt
        if (currentCard < deck.length) {
            return deck[currentCard++]; //return current Card in array
        }
        else {
            return null; //return null to indicate that all Cards were dealt
        }//end if/else
    }//end of dealCard method*/

    //turn array to stack
    public void stackDeck(Stack<Card> stackedDeck){
        shuffle();
        //shuffle deck until last card in array is not an 8 (so that first card
        //dealt into discard pile will not be 8)
        while(deck[51].getFace() == Face.EIGHT){
            shuffle();
        }//end while loop
        //add array cards to deck 
        for (int i = 0; i < 52; i++) {
            stackedDeck.push(deck[i]);
        }//end for loop
    }//end stackDeck

    //add a card to a hand (can use this method for discard pile)
    public void drawCard(ArrayList<Card> hand, Stack<Card> stackedDeck) {
        hand.add(stackedDeck.pop());
    }//end drawCard

    //not tested!
    //check if player can play any card
    //if not, then pull 1 card from deck
    //return false if no matches, don't run playCard method
    public boolean checkCards(ArrayList<Card> hand, Stack<Card> deck, Stack<Card> discard) {
        boolean takeCard = false;
        for (int i = 0; i > hand.size() - 1; i++) {
            if (hand.get(i).getFace() != discard.peek().getFace() || 
                    hand.get(i).getSuit() != discard.peek().getSuit()) {
                takeCard = true;
            }
        }//end for loop
        if (takeCard) {
            System.out.println("You have no matches. 1 card is drawn from deck.");
            drawCard(hand, deck);
            return false;
        }
        return true;
    }//end checkCards method

    //user enters card they want to play
    //check that it is a valid play (both face/suit and in hand)
    //add to discard pile and remove from player hand
    public static void playCard (ArrayList<Card> hand, ArrayList<Card> discard) {
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
            System.out.printf("%d. A %s of $s \n", i+1, DeckOfCards.get(i).getFace(), DeckOfCards.get(i).getSuit());
            //();
        }

    }

}//end DeckOfCardsMethod
