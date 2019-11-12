package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Game {
private Player player;
private Deck deck;
private List<Player> players = new ArrayList<Player>();
private Card firstCard;

    public Game(Player player, Deck deck) {
        this.player = player;
        this.deck = deck;
        players.add(player);
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    public void play(){
        player.getHand().start();
        this.firstCard = player.getHand().getHandList().get(0);
        boolean gameInProgress = true;
        while(gameInProgress){
            try{
                for(var player:players){
                    player.takeTurns(this);
                }
                gameInProgress = false;
            }catch(GameExitException ex){
                System.out.println("The game is over!");
                gameInProgress = false;
            }
        }

   }


}
