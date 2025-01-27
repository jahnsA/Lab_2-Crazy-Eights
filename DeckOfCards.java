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

    //check if player can play any card
    //if not, then pull 1 card from deck and return true
    //return false if there are playable cards
    public boolean takeCard(ArrayList<Card> hand, Stack<Card> deck, Stack<Card> discard) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getFace() == discard.peek().getFace() || 
                    hand.get(i).getSuit() == discard.peek().getSuit() || 
                    hand.get(i).getFace() == Face.EIGHT) {
                return false;
            }// end if statement
        }//end for loop
        System.out.println("You have no matches. 1 card is drawn from deck.");
        //draw card
        hand.add(deck.pop());
        return true;
    }//end takeCard method

    //user enters card they want to play
    //check that it is a valid play (both face/suit and in hand)
    //add to discard pile and remove from player hand
    public static void playCard (ArrayList<Card> hand, Stack<Card> discard, Scanner input) {
        int choice = 0;
        boolean runAgain = true;
        //user error try/catch
        while (runAgain) {
            try {
                System.out.println("Your hand: ");
                displayHand(hand);
                System.out.print("What card do you to play? (Input a number): ");
                choice = input.nextInt();     
                System.out.printf("You input %d for card %s.%n \n", 
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
            //if player picked 8 its a wild card, then doesn't have to match
            if (hand.get(choice-1).getFace() == Face.EIGHT) {
                playEight(hand, discard, input);
                return;
            } else if (discard.peek().getFace() == hand.get(choice-1).getFace() || 
                        discard.peek().getSuit() == hand.get(choice-1).getSuit()) {
                //if passes all of those, then remove card from hand and add to discard pile
                discard.push(hand.get(choice-1));
                hand.remove(choice-1);
                return;
                } else {
                    System.out.println("Invalid play. " + hand.get(choice - 1) + " doesn't match " + 
                    discard.peek() + ". Enter card with same Face or Suit of " + 
                            discard.peek().toString());
                    runAgain = true;
                }//end if/else
        }//end while statement
    }//end playCard method

    public static void playEight(ArrayList<Card> hand, Stack<Card> discard, Scanner input){
        int suit = 0;
        while (true) {
            try {
                System.out.println("You played an 8!");
                System.out.println("What Suit do you want? (Enter a number): ");
                System.out.println("1.) HEARTS 2.) DIAMONDS 3.)CLUBS 4.) SPADES");
                suit = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Enter an Integer.");
                input.nextLine(); //consumes rest of text
            }//end try/catch
            if (suit > 4 || suit < 1) {
                System.out.println("Invalid entry. Enter a number 1-4");
            }//end if statement
            break;
        }//end while loop
        if (suit == 1) {discard.peek().setSuit(Suit.HEARTS);}
        if (suit == 2) {discard.peek().setSuit(Suit.DIAMONDS);}
        if (suit == 3) {discard.peek().setSuit(Suit.CLUBS);}
        if (suit == 4) {discard.peek().setSuit(Suit.SPADES);}
        System.out.println("Top of discard is now " + discard.peek().toString());
    }//end playEight method

    //method called at the beginning of the game that explains the game's rules to the player
    public void explainGame(){
        System.out.println("""
                           Welcome to Crazy 8's where the first person to get rid of all their cards is the winner!
                           The first card of the main deck will be pulled and visible to everyone. The will be the Main Card.
                           
                           The user will go first and you can either voluntarily pull a card from the deck or play a card.
                           To play a card, it must match the Main Card in suit or face.
                           For example, if the Main Card is a 7 of hearts, you can only play a card that is a 7 or is a hearts card.
                           
                           As per the name of the game, eights are wild!
                           If you play an eight, you can change the suit of the Main Card.
                           
                           If unable to play, a card will be pulled from the top of the deck until you get a playable card.
                           """);
    } //end of explain game method
    
    //method called to display a hand
    public static void displayHand(ArrayList<Card> DeckOfCards) {
        
        for (int i = 0; i < DeckOfCards.size(); i++) {
            //print array
            System.out.printf("%d.%s of %s \n", i+1, DeckOfCards.get(i).getFace(), DeckOfCards.get(i).getSuit());
            
        }
    }//end of displayHand method

    //method where computer plays
    public void computerPlays(ArrayList<Card> hand, Stack<Card> discardPile, Stack<Card> mainDeck) {
        int heartsCheck = 0;
        int diamondsCheck = 0;
        int clubsCheck = 0;
        int spadesCheck = 0;
        boolean playedCard = false; //added the playedCard check for the computer to keep track of if any card had been played
        //initialize as false because a card has yet to be played
       
        //first thing the computer does is go through it's array to see if there's a card that matches the top of the discard pile
        if (!(playedCard)) { //only go through the loop if a card hasn't been played yet
            for(int i = 0; i < hand.size(); i++) {
                //keep check of how many suits there are so the computer can choose the one it has most of for an eight
                //keeps track in the first loop in case there is an eight
                switch (hand.get(i).getSuit()) {
                    case HEARTS -> heartsCheck++;
                    
                    case DIAMONDS -> diamondsCheck++;
                    
                    case CLUBS -> clubsCheck++;
                    
                    case SPADES -> spadesCheck++;
    
                    default -> {
                    }
                }
                
                if ((hand.get(i).getFace() == discardPile.peek().getFace()) || (hand.get(i).getSuit() == discardPile.peek().getSuit())){
                    //if the face or the suit matches, play that card
                    if (hand.get(i).getFace() != Face.EIGHT) { //check if eight because the eights loop is after, will discard if there are any eights initially
                        discardPile.push(hand.get(i)); //add card to discard pile
                        hand.remove(i); //remove card from computer's hand
                        System.out.printf("The computer plays a %s \n", discardPile.peek());
                        playedCard = true; //make it true because a card has been played
                        break; //break the for loop
                    }
                    
                }
            }
        } //end of the see if there are any matches for loop

        //if there are no matches, the second thing the computer do will check if there's any eights and play them if so.
        //there are two for loops because eights can be played at any time, and so I added that in there to make the computer thinking more complex
        if (!(playedCard)) {  //only go through the loop if a card hasn't been played yet
            for(int i = 0; i < hand.size(); i++) { // the eight for loop
                if (hand.get(i).getFace() == Face.EIGHT) {//if the card's face is an eight 
                    if ((heartsCheck >= diamondsCheck) && (heartsCheck >= clubsCheck) && (heartsCheck >= spadesCheck) ) {
                        //there are more hearts in the computer's hand, so change suit to hearts
                        discardPile.peek().setSuit(Suit.HEARTS);
                    } else if ((diamondsCheck >= heartsCheck) && (diamondsCheck >= clubsCheck) && (diamondsCheck >= spadesCheck)) {
                        //there are more diamonds in the computer's hand, so change to diamonds
                        discardPile.peek().setSuit(Suit.DIAMONDS);
                    } else if ((clubsCheck >= heartsCheck) && (clubsCheck >= diamondsCheck) && (clubsCheck >= spadesCheck)) {
                        //there are more clubs in the computer's hand, so change to clubs
                        discardPile.peek().setSuit(Suit.CLUBS);
                    } else if ((spadesCheck >= heartsCheck) && (spadesCheck >= diamondsCheck) && (spadesCheck >= clubsCheck)) {
                        //there are more spades in the computer's hand, so change to spades
                        discardPile.peek().setSuit(Suit.SPADES);
                    }
                    
                    discardPile.push(hand.get(i)); //add card to discard pile
                    hand.remove(i); //remove card from haand
                    System.out.printf("The top of the discard pile is now %s \n", discardPile.peek());
                    playedCard = true; //make it true because a card has been played
                    break; //break the for loop 
                } 
                else if ((i == hand.size()-1) && (playedCard == false)){
                    //if the computer reaches the end of the second loop without finding or playing a playable card
                    hand.add(mainDeck.peek()); //adds the top of the main stack to the computer's hand
                    mainDeck.pop(); //remove the top card of the main stack
                    System.out.println("The computer can't play. It has pulled a card from the deck.");
                    break; //break the for loop 
                }
           }
        }
    }
}//end DeckOfCardsMethod
