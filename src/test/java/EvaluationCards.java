import org.improving.UNO.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class EvaluationCards {


    @Test

    public void Evaluate_should_return_right_match() {
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        List <Card> cards = new ArrayList<>();

        var newCard = cards.add(new Card(Faces.DrawFour, Colors.Yellow));
        var result = deck.getDrawPile();
       // System.out.println(result.size());
        assertFalse(result.contains(newCard));
    }

    @Test
    public void Evaluate_drawCardPile_Return_with_right_amount(){
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        List <Card> cards = new ArrayList<>();

        hand.start();

        //var result= deck.getDrawPile().size();
        var result = deck.getDrawPile().size();

        assertEquals(105,result);

    }


    //shuffle the cards
@Test

    public void evaluate_if__Cards_are_shuffled(){
    Deck deck = new Deck();
    Hand hand = new Hand(deck);
    List <Card> cards = new ArrayList<>();
    var beforeShuffle = deck.getDrawPile().get(0);
    hand.start();
    var result = deck.getDrawPile().get(0);

    assertFalse(beforeShuffle == result);

}

@Test
    public void evaluate_if_hands_get_new_Card(){
    Deck deck = new Deck();
    Hand hand = new Hand(deck);
    List <Card> cards = new ArrayList<>();

    hand.start();

    var result = hand.getHandList().size();

    assertEquals(7,result);

}

@Test
    public void evalute_if_discard_get_a_card(){
    Deck deck = new Deck();
    Hand hand = new Hand(deck);
    List <Card> cards = new ArrayList<>();
    var card = deck.draw();
    hand.playCard(card);
    var result = deck.getDiscardPile().size();
    assertEquals(1,result);
}

//do draw for 112 times; test the length of drawpile and discardfile
@Test
public void evalute_if_switch_card_happen(){
    Deck deck = new Deck();
    Hand hand = new Hand(deck);
    List <Card> cards = new ArrayList<>();
    for(int i=0; i<112; i++) {
        deck.draw();
    }
    deck.switchDeck();
    var result = deck.getDiscardPile().size();
    assertEquals(0,result);
}

@Test

    public void evaluate_if_playerGetCard(){
    Deck d = new Deck();
    Hand h = new Hand(d);
    Player p = new Player(d);
    Game g = new Game(p,d);
    var handList = p.getHand().getHandList();
    var result = h.getHandList();
    assertEquals(handList,result);


}

@Test

 public void evaluate_if_firstCard_generated(){
    Deck d = new Deck();
    Player p = new Player(d);
    Game g = new Game(p,d);
    g.play();
    var firstCard = p.getHand().getHandList().get(0);
    System.out.println("fc is " + firstCard);
    System.out.println("hl is" + p.getHand().getHandList());

   assertTrue(p.getHand().getHandList().contains(firstCard));

}

@Test

public void evaluate_if_firstCard_removed(){
    Deck d = new Deck();
    Player p = new Player(d);
    Game g = new Game(p,d);
    g.play();
   var result = p.getHand().getHandList().size();
    System.out.println("dc is " + d.getDiscardPile());
    System.out.println("hc is" + p.getHand().getHandList());
   assertEquals(6, result);
}

@Test

public void evaluate_if_take_turns_happen(){
    Deck d = new Deck();
    Player p = new Player(d);
    Game g = new Game(p,d);
    g.play();
    var result = p.getHand().getHandList().size();
    assertEquals(5, result);
}

@Test

//looks the same as the previous test :(
 public void take_turns_run_many_times(){
    Deck d = new Deck();
    Player p = new Player(d);
    Game g = new Game(p,d);
    g.play();
    var firstCard = g.getFirstCard();
    var result = p.getHand().getHandList().get(0);
    assertFalse(firstCard == result);

}

}
