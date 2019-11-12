package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    Deck deck;

    private final List<Card> handList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public void start() {
        deck.shuffleCards(deck.getDrawPile());

        for (int i = 0; i < 7; i++) {

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
