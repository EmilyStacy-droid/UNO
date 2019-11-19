import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.improving.Colors.Blue;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluationGame {

    @Test

    public void return_top_card_from_discardPile_reduce_deck(){
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        Deck deck = new Deck();
        var firstCard = game.getTopCard();
        deck.getDiscardPile().add(firstCard);
        var topCard = game.getTopCard();
        assertFalse(deck.getDrawPile().contains(firstCard));
        assertEquals(firstCard, topCard);
    }

    @Test
    public void isLegal_return_true_when_cards_match() {
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        Deck deck = new Deck();
        //draw a card from deck
        game.setDeck(deck);
        var firstCard = game.setFirstCard();
        deck.getDiscardPile().add(firstCard);
        //send it to the discard pile so that topcard can be set
        var topCard = deck.getDiscardPile().getLast();
        game.setTopCard(topCard, topCard.getColors());
        System.out.println("new Card is" + topCard);
        // send a card the same as newCard
         assertTrue(game.isPlayable(topCard));
         assertEquals(firstCard, topCard);
    }

    @Test
    public void islegal_return_true_when_cards_are_wild(){
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        var drawFourCard = new Card (Faces.DrawFour, Colors.Wild);
        var SpinofColorCard = new Card(Faces.SpinColor, Colors.Wild);
        assertTrue(game.isPlayable(drawFourCard));
        assertTrue(game.isPlayable(SpinofColorCard));
    }

    @Test

    public void play_card_make_next_player_draw_four()  {
        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> playerHand2 = new ArrayList<>();
        game.setDeck(deck);
        game.drawInitialHand(playerHand);
        players.add(new Player(playerHand));

        game.drawInitialHand(playerHand2);
        players.add(new Player(playerHand2));

        var player1 = players.get(0);

        var player2 = players.get(1);
        game.setFirstCard();
        deck.getDiscardPile().add(new Card (Faces.DrawFour, Colors.Wild));
        var topCard = deck.getDiscardPile().getLast();
        game.setTopCard(topCard, topCard.getColors());
        game.turnEngine = 0;
        game.turnDirection = 1;

        game.executeCardAction(topCard,game);

        assertEquals(7, player1.Handsize());
        assertEquals(11,player2.Handsize());
    }

    @Test

    public void draw_two_make_next_player_draw_two() {

        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> playerHand2 = new ArrayList<>();
        game.setDeck(deck);
        game.drawInitialHand(playerHand);
        players.add(new Player(playerHand));

        game.drawInitialHand(playerHand2);
        players.add(new Player(playerHand2));

        var player1 = players.get(0);

        var player2 = players.get(1);

        game.setFirstCard();
        deck.getDiscardPile().add(new Card (Faces.DrawTwo, Colors.Wild));
        var topCard = deck.getDiscardPile().getLast();
        game.setTopCard(topCard, topCard.getColors());
        game.turnEngine = 0;
        game.turnDirection = 1;
        game.executeCardAction(topCard,game);

        assertEquals(7, player1.Handsize());
        assertEquals(9,player2.Handsize());


    }

    @Test

    public void skip_works_on_next_player(){

        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        game.setDeck(deck);
         game.turnEngine = 1;
        game.turnDirection = 1;

        game.executeCardAction(new Card(Faces.Skip,Colors.Green), game);
        var result = game.turnEngine;

        assertEquals(2,result);

    }

    @Test

    public void reverse_reverse_the_order(){
        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);

        game.setDeck(deck);

        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> playerHand2 = new ArrayList<>();
        players.add(new Player(playerHand));
        players.add(new Player(playerHand2));

        game.turnEngine = 1;
        game.turnDirection = 1;

        game.executeCardAction(new Card(Faces.Reverse,Colors.Green), game);

        var result = game.turnDirection;

        assertEquals(-1,result);
    }

    //the rule for firstCard as skip or reverse or drawFour or drawTwo?


    @Test
    public void draw_four_reset_color(){
        //arrange
        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> playerHand2 = new ArrayList<>();
        players.add(new Player(playerHand));
        players.add(new Player(playerHand2));
        var player1 = players.get(0);

        //act
        var newCard = new Card(Faces.DrawFour,Colors.Wild);
         player1.playCard(newCard, game);
        //assert

        assertTrue(player1.declareColor(newCard, game) != null);

    }
    @Test
    public void playCard_in_Game_pass_color_declaration() {

        //arrange
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        Deck deck = new Deck();
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Faces.Five, Colors.Yellow));
        Player player = new Player(playerHand);
        players.add(player);
        game.setFirstCard();
        var playerCard = playerHand.get(0);
        deck.getDiscardPile().add(playerCard);
        game.setTopCard(playerCard, playerCard.getColors());
        Optional<Colors> color = Optional.ofNullable(player.declareColor(playerCard,game));
        game.playCard(playerCard, color);
        //Act
        game.setTopCard(playerCard, color.orElseThrow());
        assertEquals(color, color);
    }

    @Test
    public void playCard_in_Game_pass_color_declaration_with_wild_Card() {

        //arrange
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        Deck deck = new Deck();
        game.setDeck(deck);
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Faces.Five, Colors.Wild));
        Player player = new Player(playerHand);
        players.add(player);
        game.setFirstCard();
        var playerCard = playerHand.get(0);
        //deck.getDiscardPile().add(playerCard);
        //game.setTopCard(playerCard, playerCard.getColors());
        player.playCard(playerCard, game);
        Optional<Colors> color = Optional.ofNullable(player.declareColor(playerCard,game));
        game.playCard(playerCard, color);

        //Act
        game.setTopCard(playerCard, color.orElseThrow());
        assertEquals(color, color);
        assertTrue(color.isPresent());
    }

    @Test

    public void color_declaration_reset_wildCard_correctly(){
        //arrange
        List<Player> players = new ArrayList<Player>();
        Game game = new Game(players);
        Deck deck = new Deck();
        game.setDeck(deck);
        game.setFirstCard();
        game.setTopCard(new Card(Faces.SpinColor, Colors.Wild), Colors.Red);
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Faces.Five, Colors.Red));
        Player player1 = new Player(playerHand);
        players.add(player1);
        var player1Card = playerHand.get(0);
        player1.playCard(player1Card, game);

        //act
        assertTrue(game.isPlayable(player1Card));
        //assert
    }



}








