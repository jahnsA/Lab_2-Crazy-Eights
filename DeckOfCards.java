import java.security.SecureRandom;
import java.util.ArrayList; //for random numbers
import java.util.Stack;

public class DeckOfCards {

    //random number generator  ---> from Darrell's program.
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;

    //initialize stacked deck
    Stack<Card> stackedDeck = new Stack<>();

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
    public void stackDeck(){
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
    public void drawCard(ArrayList<Card> hand) {
        hand.add(stackedDeck.pop());
    }//end drawCard

}//end DeckOfCardsMethod
