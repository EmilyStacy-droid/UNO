import org.improving.UNO.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.improving.UNO.Colors.Blue;
import static org.improving.UNO.Faces.Eight;
import static org.improving.UNO.Faces.Nine;
import static org.junit.jupiter.api.Assertions.*;


public class EvaluationCards {




    @Test

    public void Evaluate_should_return_right_match() {
        Game game = new Game();
        List <Card> cards = new ArrayList<>();

        var newCard = game.getDeck().getDrawPile().add(new Card(Faces.DrawFour, Colors.Yellow));
        var result = game.getDeck().getDrawPile();
        assertFalse(result.contains(newCard));
    }

    @Test
    public void Evaluate_drawCardPile_Return_with_right_amount(){
        Game game = new Game();

        var result = game.getDeck().getDrawPile().size();

        assertEquals(91,result);
    }



@Test

    public void evaluate_if__shuffle_cards_work(){
    Game game = new Game();
    var beforeShuffle = game.getDeck().getDrawPile().size();
    for(int i=0; i<game.getDeck().getDrawPile().size();i++){
        game.draw();
    }

    var afterShuttle = game.getDeck().getDrawPile().size();

    assertFalse(beforeShuffle == afterShuttle);

}

@Test
    public void evaluate_if_players_class_get_new_Cards(){
    Game game = new Game();
    game.getPlayers().add(new Player(game.getPlayers().get(0).getHand(), game));
    var cardFromGame = game.getDeck().getDrawPile();
    var cardFromPlayer =  game.getPlayers().get(0).getHand();

    assertFalse(cardFromGame.contains(cardFromPlayer));

}


@Test
public void evalute_if_switch_card_happen(){
    Game game = new Game();
    for(int i=0; i< 91; i++) {
        game.draw();
    }
    var result = game.getDeck().getDiscardPile().size();

    assertEquals(0,result);
}


@Test

 public void evaluate_if_firstCard_generated(){
    Game game = new Game();
    game.getFirstCard();
    var size = game.getDeck().getDiscardPile().size();
   assertTrue(1 == size);

}

@Test
    public void isLegal_return_true_when_faces_match() {
        Game game = new Game();
        game.getFirstCard();
        var topCardFace = game.getDeck().getDiscardPile().getLast().getFaces();
        var newCard = new Card(topCardFace, Blue);
        assertTrue(game.isLegal(newCard));
    }

@Test

public void take_turns_get_one_card_returned(){
//    Game game = new Game();
//    //var playerOneTurn = game.getPlayers().get(0).takeTurn(game);
//    var hand = game.getPlayers().get(0).getHand();
//    System.out.println("return is " +  playerOneTurn);
//    System.out.println("hand  is " + hand);
//
//    assertFalse(hand.contains(playerOneTurn));
}
    @Test

    public void AllPlayerGetaHand() {
        Game game = new Game();
        var playerOneHandSize = game.getPlayers().get(0).Handsize();
        var playerTwoHandSize = game.getPlayers().get(1).Handsize();
        var playerThreeHandSize = game.getPlayers().get(2).Handsize();
        assertEquals(playerOneHandSize, playerTwoHandSize, playerThreeHandSize);
//        System.out.println("Player 1 got " + game.getPlayers().get(0).getHand());
//        System.out.println("Player 2 got " + game.getPlayers().get(1).getHand());
//        System.out.println("Player 3 got " + game.getPlayers().get(2).getHand());

    }

@Test

 public void play_card_work_on_draw_four()  {
    Game game = new Game();
    var wildCard = new Card(Faces.DrawFour, Colors.Wild);
    game.getPlayers().get(game.getCurrentPlayer()).getHand().add(wildCard);
    game.playCard(wildCard);
    var player2Hand = game.getPlayers().get(game.getCurrentPlayer()).getHand().size();
    assertEquals(11,player2Hand);
}

    @Test

    public void play_card_work_on_draw_two()  {
        Game game = new Game();
        var wildCard = new Card(Faces.DrawTwo, Colors.Yellow);
        game.getPlayers().get(game.getCurrentPlayer()).getHand().add(wildCard);
        game.playCard(wildCard);
        var player2Hand = game.getPlayers().get(game.getCurrentPlayer()).getHand().size();
        assertEquals(9,player2Hand);
    }


    @Test

    public void play_card_work_on_reverse(){
        Game game = new Game();
        var presentPlayer = game.getCurrentPlayer();
        var reverseCard = new Card(Faces.Reverse, Colors.Green);
        game.getPlayers().get(game.getCurrentPlayer()).getHand().add(reverseCard);
        game.playCard(reverseCard);
        var newPlayer = game.getCurrentPlayer();
        assertFalse(presentPlayer == newPlayer);

    }



}
