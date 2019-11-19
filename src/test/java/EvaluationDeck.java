import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluationDeck {


        @Test

      public void Evaluate_drawCardPile_Return_with_right_amount(){
            Deck deck = new Deck();
        var result =deck.getDrawPile().size();
        assertEquals(112,result);
    }

    @Test

    public void disCardPile_starts_with_zero(){

            //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
           //act
        var disCardPilesize = deck.getDiscardPile().size();

        //assert
        assertEquals(0, disCardPilesize);
    }

    @Test
    public void draw_first_card_when_play() {
        //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
        //act
        var result = game.setFirstCard();

        //assert
        assertFalse(result==null);
    }


    @Test

    public void firstCard_reduce_deck_size(){
        //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
        var result = game.setFirstCard();
        //act
        var drawCardSize = deck.getDrawPile().size();

        //assert
        assertEquals(111,drawCardSize);
    }


    @Test
    public void firstCard_is_passed_as_topCard(){
        //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
        var result = game.setFirstCard();

        //act
        game.setTopCard(result, result.getColors());
        var topCard = game.getTopCard();

        //assert
        assertEquals(result, topCard);
    }

    @Test
    public void topCard_replaced_with_turns() {
//         //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
        var firstCard = game.setFirstCard();
        var topCard = new Card(Faces.Five, Colors.Yellow);
        deck.getDiscardPile().add(topCard);

       //action
         game.setTopCard(topCard, topCard.getColors());

     //assert
        var result = game.getTopCard();
        assertEquals(result, topCard);

    }

    @Test
    public void first_Card_not_return_actionCard(){
        //arrange
        Deck deck = new Deck();
        List<EMPlayer> players = new ArrayList<EMPlayer>();
        Game game = new Game(1);
        game.setDeck(deck);
        var firstCard = game.setFirstCard();
        //action
       var result = game.hasAction(firstCard);

        //assert
        assertFalse(result);
    }

//    @Test
//
//    public void deck_switch_when_drawCard_empty(){
//            //arrange
//        Deck deck = new Deck();
//        List<Player> players = new ArrayList<Player>();
//        Game game = new Game(players);
//        game.setDeck(deck);
//        //act
//        for(var i=0; i<112;i++) {
//            game.draw();
//        }
//        //assert
//    }
}


