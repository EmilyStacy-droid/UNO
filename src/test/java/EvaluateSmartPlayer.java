import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EvaluateSmartPlayer {

    @Test

    public void take_turn_returns_the_most_suitable_card() {
        //arrange: check player's hand in playable
        List<SmartPlayer> players = new ArrayList<SmartPlayer>();
        Game game = new Game(2);
        Deck deck = new Deck();
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        //set a topCard with a color
        game.setFirstCard();
        game.setTopCard(new Card(Faces.Five, Colors.Red), Colors.Red);
        playerHand.add(new Card(Faces.Eight, Colors.Yellow));
        playerHand.add(new Card(Faces.Eight, Colors.Red));
        playerHand.add(new Card(Faces.Five, Colors.Red));
        SmartPlayer player = new SmartPlayer(playerHand);
        players.add(player);

        //act


        //assert

    }

}




