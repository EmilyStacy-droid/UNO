package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    Deck deck = new Deck();

    private final List<Card> handList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public void start() {

        for (int i = 0; i < 7; i++) {

            deck.shuffleCards(deck.getDrawPile());

            Card card = deck.draw();

            handList.add(card);

            System.out.println(card.toString());
        }

    }

    public void playCard(Card card) {

        deck.addDiscard(card);

        handList.remove(card);
    }

    public List<Card> getHandList() {
        return handList;
    }
}
