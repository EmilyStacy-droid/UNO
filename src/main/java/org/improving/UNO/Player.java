package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Game game;
    private Deck deck = new Deck();
    private Hand hand = new Hand(deck);
    private Card firstCard;

    public Player(Deck deck) {
        this.deck = deck;


    }


    public Hand getHand() {
        return hand;
    }

    public void takeTurns(Game game) {
        List<Card> handCards = getHand().getHandList();
        firstCard = game.getFirstCard();
        deck.addDiscard(firstCard);
        handCards.remove(firstCard);

        for(var card: handCards) {
            if(card.getFaces() == firstCard.getFaces() || card.getColors() == firstCard.getColors()) {
                handCards.remove(card);
                deck.addDiscard(card);
                game.setFirstCard(card);
                return;
            }else {
                var newCard = deck.draw();
                handCards.add(newCard);
                if(card.getFaces() == firstCard.getFaces() || card.getColors() == firstCard.getColors()) {
                    handCards.remove(card);
                    deck.addDiscard(card);
                }else {
                    return;
                }

            }

            if(card.getFaces() == Faces.DrawTwo) {
                for(int i =0; i < 2; i++) {
                    handCards.add(deck.draw());
                }
            }

            if(card.getFaces() == Faces.DrawFour) {
                for(int i =0; i < 4; i++) {
                    handCards.add(deck.draw());
                }
            }
        }
            if (handCards.size() == 1) {
                System.out.println("The player is shouting UNO!");
            }

            if (handCards.size() == 0) {
                System.out.println("The player wins!");
                throw new GameExitException();
            }
        }

}