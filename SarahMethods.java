import java.util.ArrayList;
public class SarahMethods {
    //explain game

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

    public void displayCard(Card main) {
        //display one card
        System.out.println(main);
    }


}
