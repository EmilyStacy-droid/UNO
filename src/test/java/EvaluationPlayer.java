import com.improving.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EvaluationPlayer {
    List<Player> players = new ArrayList<Player>();
    Game game = new Game(players);
   Deck deck = new Deck();
    ArrayList<Card> playerHand = new ArrayList<>();
    Player player = new Player(playerHand);

  @Test

    public void drawCard_returns_one_card() {
      this.game = game;
      this.player = player;

      player.draw(game);

      int handSize = player.Handsize();

      assertEquals(1, handSize);

  }

  @Test

public void take_turns_reduce_hand_size(){

    //arrange
      List<Player> players = new ArrayList<Player>();
      Game game = new Game(players);
      Deck deck = new Deck();
      ArrayList<Card> playerHand = new ArrayList<>();
      playerHand.add(new Card(Faces.Five, Colors.Yellow));
      playerHand.add(new Card(Faces.Eight, Colors.Green));
      Player player = new Player(playerHand);
      players.add(player);
      game.setFirstCard();
      game.setTopCard(new Card(Faces.Five, Colors.Yellow), Colors.Yellow);
      //game.getTopCard();
      player.playCard(playerHand.get(0), game);
    //act
      var size = player.Handsize();

// assert
    assertEquals(1,size);
}

//@Test
//
// public void playCard_in_Player_pass_color_declaration(){
//
//      //arrange
//    List<Player> players = new ArrayList<Player>();
//    Game game = new Game(players);
//    Deck deck = new Deck();
//    ArrayList<Card> playerHand = new ArrayList<>();
//    playerHand.add(new Card(Faces.Five, Colors.Yellow));
//    Player player = new Player(playerHand);
//    players.add(player);
//    game.setFirstCard();
//    var playerCard = playerHand.get(0);
//    deck.getDiscardPile().add(playerCard);
//    game.setTopCard(playerCard, playerCard.getColors());
//
//
//    //act
//    Optional<Colors> color = Optional.ofNullable(player.declareColor(playerCard,game));
//    var result = game.isValidDeclaredColor(color);
//
//    //assert
//    assertTrue(result);
//}

@Test

 public void take_turn_returns_the_most_suitable_card() {
  //arrange: check player's hand in playable
    List<Player> players = new ArrayList<Player>();
    Game game = new Game(players);
    Deck deck = new Deck();
    game.setDeck(deck);
    ArrayList<Card> playerHand = new ArrayList<>();
    //set a topCard with a color
    game.setFirstCard();
    game.setTopCard(new Card(Faces.Five, Colors.Red), Colors.Red);
    playerHand.add(new Card(Faces.Eight, Colors.Yellow));
    playerHand.add(new Card(Faces.Eight, Colors.Red));
    playerHand.add(new Card(Faces.Five, Colors.Red));


 //act


 //assert

}


}
