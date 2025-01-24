//Abby Jahns
//01.24.25
//seperate work space for code

public class AbbyMethods {

    public static void main(String[] args) {

    DeckOfCards testDeck = new DeckOfCards();
    testDeck.shuffle();
    System.out.println(testDeck[51].toString());

    checkForEight();
        
    }//end main method

    //check if first card pulled for discard pile is 8
    //shuffle if true
    //?Keep calling this until 8 is not the last card?
    public static void checkForEight () {
        while (deck[51].getface() == 8) {
            deck.shuffle();
        }
    }//end checkForEight method

}//end class methods
