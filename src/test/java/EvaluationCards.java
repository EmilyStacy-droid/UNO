import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class EvaluationCards {
    List<Player> players = new ArrayList<Player>();
    ArrayList<Card> playerHand = new ArrayList<>();
    Game game = new Game(players);
    Deck deck = new Deck();
    @Test

    public void Evaluate_should_return_right_match() {
        this.deck = deck;
        var newCard = deck.getDrawPile().add(new Card(Faces.DrawFour, Colors.Yellow));
        var result = deck.getDrawPile();
        assertFalse(result.contains(newCard));
    }




@Test

    public void evaluate_if__shuffle_cards_work(){
    Deck deck = new Deck();
    var beforeShuffle = deck.getDrawPile().size();
    for(int i=0; i< deck.getDrawPile().size();i++){
        deck.draw();
    }

    var afterShuttle = deck.getDrawPile().size();

    assertFalse(beforeShuffle == afterShuttle);

}

@Test
    public void evaluate_if_players_class_get_new_Cards(){
        this. game = game;
        this.deck = deck;
        this.playerHand = playerHand;

        var cardFromGame = game.draw();
        var drawPile = deck.getDrawPile();
        playerHand.add(cardFromGame);
        players.add(new Player(playerHand));
        var cardFromPlayer =  playerHand.get(0);
        assertFalse(drawPile.contains(cardFromPlayer));

}


//@Test
//public void evalute_if_switch_pile_happen(){
//    List<Player> players = new ArrayList<Player>();
//    Game game = new Game(players);
//    Deck deck = new Deck();
//    game.setDeck(deck);
//    for(int i=0; i< 112; i++) {
//        game.draw();
//    }
//
//    var disCardresult = deck.getDiscardPile().size();
//    var drawPileresult2 = deck.getDrawPile().size();
//
//    assertEquals(0,disCardresult);
//    assertEquals(112,drawPileresult2);
//}


@Test

    public void AllPlayerGetaHand() {
        this. game = game;
        this.deck = deck;
        this.players = players;
       ArrayList<Card> playerHand1 = new ArrayList<>();
       ArrayList<Card> playerHand2 = new ArrayList<>();
        players.add(new Player(playerHand1));
        players.add(new Player(playerHand2));
        game.drawInitialHand(playerHand1);
        game.drawInitialHand(playerHand2);

      var playerOneHandSize = game.getPlayerInfo().get(0).Handsize();
        var playerTwoHandSize = game.getPlayerInfo().get(1).Handsize();

        assertEquals(playerOneHandSize, playerTwoHandSize);

    }




}
