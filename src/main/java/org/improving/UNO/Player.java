package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {
    List<Card> hand;
    Deck deck;

    public Player(List<Card> hand, Deck deck) {
        this.hand = hand;
        this.deck = deck;
    }

    @Override
    public Card draw(Game game) {
        return deck.draw();
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
            }
        }

        var drawnCard = deck.draw();
        if(game.isLegal(drawnCard)) {
            return playCard(game, drawnCard);
        }
       return null;
    }

    private Card playCard(Game game, Card card) {
        if(card.getColors() == Colors.Wild) {
            card.setColors(hand.get(0).getColors()) ;
            System.out.println("Player " + this.hashCode() + " set Color to " + card.getColors());
        }
        hand.remove(card);
        deck.getDiscardPile().add(card);
        return card;
    }

//    public void playCard(Game game, Player player, Card card) {
//        if(card.getFaces() == Faces.DrawTwo) {
//            //why
//            player.draw(game);
//            player.draw(game);
//        }
//
//        if(card.getFaces() == Faces.DrawFour) {
//            player.draw(game);
//            player.draw(game);
//            player.draw(game);
//            player.draw(game);
//        }
//
//
//        deck.addDiscard(card);
//    }

    public List<Card> getHand() {
        return hand;
    }

//    private void playCard(Game game, Card card) {
//        List<Colors> namedSuit = null;
//        hand.remove(card);
//        if(card.getColors() == Colors.Wild) {
//            namedSuit = hand.get(0).getColors();
//        }
//
//        game.playCard(this,card,namedSuit);
//
//    }

}