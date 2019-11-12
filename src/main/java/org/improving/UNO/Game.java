package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Game {
private Deck deck;
private List<Player> players = new ArrayList<Player>();
private List<Card> hand = new ArrayList<>();

    Game(){
        deck = new Deck();
        drawInitialHand();
        players.add(new Player(hand, deck));
    }


    public void playGame(){

        System.out.println("New Game with " + players.size() + " players ");

        for(var player: players) {
            drawInitialHand();
        }

        deck.getDiscardPile().add(deck.draw());

        System.out.println("The Top Card is " + deck.getDiscardPile().getLast());
        //player.getHand().start();
        //this.firstCard = player.getHand().getHandList().get(0);
        boolean gameInProgress = true;
        int turn = 0;
        while(gameInProgress){
            try{
                turn +=1;
                System.out.println("Start Turn " + turn );

                for(var player:players){
                   var play =  player.takeTurn(this);
                    if(play != null) {
                        System.out.println("Player " + player.hashCode() + " played " + play);
                    }

                    if(player.Handsize() == 0) {
                        System.out.println("Player " + player.toString() + " has won the game on turn " + turn);
                        gameInProgress = false;
                    }

                }

            }catch(GameExitException ex){
                System.out.println("The game is over!");
                gameInProgress = false;
            }
        }

   }

    private List drawInitialHand() {

        for(int i =0; i < 7; i++) {
            hand.add(deck.draw());
        }

       return hand;
    }

    //compare player's card to the top card in deck
    public boolean isLegal(Card card){
        return
                card.getColors() == deck.getDiscardPile().getLast().getColors() ||
                card.getFaces()==deck.getDiscardPile().getLast().getFaces() ||
                card.getColors() == Colors.Wild;

   }


}
