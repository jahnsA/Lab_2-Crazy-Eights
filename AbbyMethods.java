//Abby Jahns
//01.24.25
//seperate work space for code

public class AbbyMethods {

    public static void main(String[] args) {

    DeckOfCards testDeck = new DeckOfCards();
    testDeck.shuffle();
    System.out.println(testDeck[51].toString());

    //game loop
    while(playerHand.length == 0 || computerHand.length == 0) {
        //computer plays or you play
        //show hand, give options
        //or pull from deck
    }
    if (playerHand.length == 0) {
        System.out.println("You Win!");
    }else {
        System.out.println("You Lose!");
    }


    checkForEight();
        
    }//end main method

    //check if first card pulled for discard pile is 8
    //this is assuming the cards are going into a stack, FILO
    //shuffle if true
    public static void checkForEight () {
        while (deck[51].getface() == 8) {
            deck.shuffle();
        }
    }//end checkForEight method

}//end class methods
