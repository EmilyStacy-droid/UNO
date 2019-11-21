import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluateSmartPlayer {

    @Test

    public void take_turn_returns_the_most_suitable_card() {
        //arrange: check player's hand in playable
        Game game = new Game(2);
        Deck deck = new Deck();
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        //set a topCard with a color
        var firstCard = game.setFirstCard();
        firstCard = new Card(Faces.Seven, Colors.Red);
        var firstCardColor = firstCard.getColors();
        playerHand.add(new Card(Faces.Seven, Colors.Red));
        playerHand.add(new Card(Faces.Seven, Colors.Yellow));
        playerHand.add(new Card(Faces.Four, Colors.Yellow));
        playerHand.add(new Card(Faces.Three, Colors.Blue));
        playerHand.add(new Card(Faces.Four, Colors.Green));
        playerHand.add(new Card(Faces.DrawFour, Colors.Wild));
        SmartPlayer player = new SmartPlayer(playerHand);
        var actualCard = playerHand.get(2); //it didn't pick up this card
        game.setTopCard(firstCard, firstCardColor);
        player.takeTurn(game);
        game.isPlayable(actualCard);
        //act

        var actualCardColor = player.declareColor(actualCard, game);
        //assert
        assertFalse(firstCardColor == actualCardColor);

    }

    @Test

    public void play_Card_stops_next_player_when_less_than_2_cards_left() {
        //arrange
        Game game = new Game(2);
        Deck deck = new Deck();
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        SmartPlayer player = new SmartPlayer(playerHand);
        playerHand.add(new Card(Faces.Seven, Colors.Red));
        playerHand.add(new Card(Faces.Seven, Colors.Yellow));
        playerHand.add(new Card(Faces.Four, Colors.Yellow));
        playerHand.add(new Card(Faces.Three, Colors.Blue));


        //act
        //var topCard = game.getTopCard();
        //var playedCard = player.playCard();
        //assert
        //assertTrue(topCard == playedCard);
    }

}



