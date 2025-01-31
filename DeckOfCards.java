import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class DeckOfCards {
    //INITIALIZE SECTION
    //random number generator  ---> from Darrell's program.
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references

    Stack<Card> stackedDeck = new Stack<>();
    ArrayList<Card> computerHand = new ArrayList<>();
    ArrayList<Card> playerHand = new ArrayList<Card>();
    Stack<Card> discardPile = new Stack<>();

     // Constructor fills deck of cards
    public DeckOfCards() {
        // Populate deck with Card objects using Face and Suit enums
        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(Face.values()[count % 13], Suit.values()[count / 13]);
        }//end for loop
    }//end DeckOfCards constructor

    //shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        //next call to method dealCard should start at deck[0] again

        for (int first = 0; first < deck.length; first++) {
            //select a random number between 0 and 51
            int second = randomNumbers.nextInt(deck.length);

            //swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }//end for loop
    }//end of shuffle method

    //shuffle array and turn to stack
    public void stackDeck(Stack<Card> stackedDeck){
        //shuffle deck until last card in array is not an 8 (so that first card
        //dealt into discard pile will not be 8)
        do { 
            shuffle();
        } while(deck[deck.length - 1].getFace() == Face.EIGHT);
        //add array cards to deck 
        for (int i = 0; i < deck.length; i++) {
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
        //System.out.println("Top of discard pile: " + discard.peek());
        System.out.println("Your hand: ");
        displayHand(hand);
        System.out.println("You have no matches. 1 card is drawn from deck.\n");
        //draw card
        hand.add(deck.pop());
        return true;
    }//end takeCard method

    //user enters card they want to play
    //check that it is a valid play (both face/suit and in hand)
    //add to discard pile and remove from player hand
    public void playCard (ArrayList<Card> hand, Stack<Card> discard, Scanner input) {
        int choice = 0;
        //user error try/catch
        while(true){
            try {
                System.out.println("\nYour hand: ");
                displayHand(hand);
                System.out.print("What card do you to play? (Input a number): ");
                choice = input.nextInt();     
                System.out.printf("You input %d for card %s.%n \n", 
                choice, hand.get(choice - 1).toString());
                //check if card is playable
                if (discard.peek().getFace() == hand.get(choice-1).getFace() || 
                discard.peek().getSuit() == hand.get(choice-1).getSuit() ||
                hand.get(choice-1).getFace() == Face.EIGHT) {
                //if passes all of those, then remove card from hand and add to discard pile
                discard.push(hand.get(choice-1));
                hand.remove(choice-1);
                break;
            } else {
                System.out.println("Invalid play. " + hand.get(choice - 1) + " doesn't match " + 
                discard.peek() + ". Enter card with same Face or Suit of " + 
                discard.peek().toString());
            }//end if/else
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid entry. Please enter a number 1 - " + hand.size());
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Enter a number.");
                input.nextLine(); //consumes rest of text
            }
        }//end while loop for try/catch
        //if an 8 is played
        if(discard.peek().getFace() == Face.EIGHT) {
            playEight(hand, discard, input);
        }
    }//end playCard method

    public void playEight(ArrayList<Card> hand, Stack<Card> discard, Scanner input){
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
                suit = input.nextInt();
            }//end if statement
            break;
        }//end while loop
        if (suit == 1) {discard.peek().setSuit(Suit.HEARTS);}
        if (suit == 2) {discard.peek().setSuit(Suit.DIAMONDS);}
        if (suit == 3) {discard.peek().setSuit(Suit.CLUBS);}
        if (suit == 4) {discard.peek().setSuit(Suit.SPADES);}
        System.out.println("Top of discard is now " + discard.peek().toString() + "\n");
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
                    System.out.printf("The computer plays a %s \n", discardPile.peek() + ".");
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
    }//end of Computer Plays method

    public void pressALetterToContinue(Scanner input){
        //made so that the game won't jump 20 lines of input if both the plater and computer don't have playable cards
        //doesn't need any checks because it's not important if the computer saves the value
        //just a placeholder
        System.out.println("Enter a letter to continue.");
        String check = input.next();
        System.out.println("");
    }//end of press A Letter method

    //shuffles discard pile and turns into new deck (for if deck runs out of cards)
    public void shuffleDiscard(Stack<Card> stackedDeck, Stack<Card> discard){
        System.out.println("Ran out of cards in the draw pile. Now shuffling"
        + " discard pile to make new draw pile...");
        //empties deck array initialized at beginning of program and changes size
        deck = new Card[discard.size()];
        //put discard pile values into deck array
        for (int i = 0; i < deck.length; i++) {
            deck[i] = discard.pop();
        }//end for loop
        //turn new deck array to stack
        stackDeck(stackedDeck);
        //add one card to discard
        discard.push(stackedDeck.pop());
    }//end shuffleDiscard

    //start the game
    public void startGame() {

        //print introduction
        explainGame();

        //switch deck from array to stack
        stackDeck(stackedDeck);

        //add first card to discard pile
        discardPile.push(stackedDeck.pop());

        //player initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            playerHand.add(stackedDeck.pop());
        }
        //computer initial draw of 5 cards
        for (int i = 0; i < 5; i++) {
            computerHand.add(stackedDeck.pop());
        }

    }//end startGame

    //play a round of the game
    public void playGame(Scanner input) {
        //game loop
        while(!playerHand.isEmpty() && !computerHand.isEmpty()) {
            //so we don't run out of cards to draw
            if (stackedDeck.isEmpty()) {
                shuffleDiscard(stackedDeck, discardPile);    
            }
            //player plays 
            System.out.println("Top of the discard pile is " + discardPile.peek()); 
            //if player doesn't have to take card bc they have no matches, then they can play
            if(!takeCard(playerHand, stackedDeck, discardPile)){
                playCard(playerHand, discardPile, input);
            }//end if
            if(!playerHand.isEmpty()) {//check player didn't just win
                System.out.println("Computer plays...");
                computerPlays(computerHand, discardPile, stackedDeck);
                System.out.println("The computer has " + computerHand.size() + " cards left.\n");
                
                //game ends if computer has 0 cards left
                if(!computerHand.isEmpty()){
                //press y to continue
                pressALetterToContinue(input);
                }//end if
            }//end if
        }//end while game loop

        //the player whose hand is empty wins
        if(playerHand.isEmpty()){
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }//end if/else
    }//end playGame

}//end DeckOfCardsMethod