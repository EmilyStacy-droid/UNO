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
    //failed every two times
    public void take_turn_play_wild_cards_when_next_player_has_less_than_2_cards_left() {
        //arrange
        Game game = new Game(2);
        Deck deck = new Deck();
        game.setDeck(deck);
        var firstCard = game.setFirstCard();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> playerHand2 = new ArrayList<>();
        SmartPlayer player = new SmartPlayer(playerHand);
        SmartPlayer player2 = new SmartPlayer(playerHand2);
        playerHand2.add(new Card(Faces.Four, Colors.Yellow));
        playerHand2.add(new Card(Faces.Three, Colors.Blue));
        playerHand2.add(new Card(Faces.Eight, Colors.Red));
        playerHand.add(new Card(Faces.Seven, Colors.Red));
        playerHand.add(new Card(Faces.Seven, Colors.Yellow));
        playerHand.add(new Card(Faces.Four, Colors.Yellow));
        playerHand.add(new Card(Faces.Three, Colors.Blue));
        playerHand.add(new Card(Faces.DrawFour, Colors.Wild));
        game.setTopCard(firstCard, firstCard.getColors());
        var nextPlayer =game.getnextPlayer();
        var handSize = nextPlayer.Handsize();
        player2.takeTurn(game);
        player.takeTurn(game);


        //act
        var topCardFaces = game.getTopCard().getFaces();
        //assert
        assertEquals(Faces.DrawFour, topCardFaces);
    }

}



