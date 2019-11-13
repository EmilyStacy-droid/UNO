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
        return game.draw();
    }

    @Override
    public int Handsize(){
        return hand.size();
    }

    @Override
    public Card takeTurn(Game game) {
        for(var card: hand) {
            if(game.isLegal(card)) {
                return playCard(game, card);
            }else if(!game.isLegal(card)){
                var drawnCard = game.draw();
                if(game.isLegal(drawnCard)) {
                    return playCard(game, drawnCard);
                }

            }
        }

       return null;
    }

    private Card playCard(Game game, Card card) {
        if (card.getFaces() == Faces.SpinColor) {

            card.setColors(hand.get(0).getColors());
            System.out.println("Player " + this.hashCode() + " set Color to " + card.getColors());
        } else if (card.getFaces() == Faces.DrawTwo) {
            hand.add(draw(game));
            hand.add(draw(game));
        } else if (card.getFaces() == Faces.DrawFour) {
            hand.add(draw(game));
            hand.add(draw(game));
            hand.add(draw(game));
            hand.add(draw(game));
            //bug
        }
        hand.remove(card);
        game.getDeck().getDiscardPile().add(card);
        return card;
    }


    public List<Card> getHand() {
        return hand;
    }



}