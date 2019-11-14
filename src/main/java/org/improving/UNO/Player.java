package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {
    List<Card> hand;
    Game game;

    public Player(List<Card> hand, Game game) {
        this.hand = hand;
        this.game = game;
    }

    @Override
    public Card draw(Game game) {
        hand.add(game.draw());
        return game.draw();
    }

    @Override
    public int Handsize(){
        return hand.size();
    }

    @Override
    public void takeTurn(Game game) {
        for(var card: hand) {
            if(game.isLegal(card) && card.getFaces() == Faces.SpinColor) {
                card.setColors(hand.get(0).getColors());
                System.out.println("Player " + this.hashCode() + " set Color to " + card.getColors());
                game.playCard(card);
                hand.remove(card);
                return;

            }else if (game.isLegal(card) ){
                game.playCard(card);
                hand.remove(card);
                return;
            }
             if(!game.isLegal(card)){
                var drawnCard = game.draw();
                if(game.isLegal(drawnCard)) {
                    game.playCard(card);
                    hand.remove(card);
                    return;
                }
            }
        }
    }

//    public List<Card> getHand() {
//        return hand;
//    }



}