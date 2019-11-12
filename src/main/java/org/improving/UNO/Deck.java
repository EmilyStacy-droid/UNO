package org.improving.UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> drawPile = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();

    Random random = new Random();

    public Deck() {
        for (int i = 0; i < 2; i++) {
            for (var face : Faces.values()) {
                for (var color : Colors.values()) {

                    if (face.getintValue() == 1 && color.getintValue() == 1) {
                        drawPile.add(new Card(face, color));
                    }

                    if (face.getintValue() == 0 && color.getintValue() == 1) {
                        drawPile.add(new Card(face, color));

                    }
                }
            }
        }
        for (int j = 0; j < 4; j++) {
            for (var face : Faces.values()) {
                for (var color : Colors.values()) {
                    if (face.getintValue() == (-1) && color.getintValue() == 0) {
                        drawPile.add(new Card(face, color));
                    }
                }
            }
        }

    }

    public List<Card> getDrawPile() {
        return drawPile;
    }


// draw method

    public Card draw() {
        var randomIndex = random.nextInt(drawPile.size());
        var card = drawPile.get(randomIndex);
        drawPile.remove(randomIndex);

        if (drawPile.size() == 0) {
            switchDeck();
        }
        return card;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }
    //shufflemethod, if drawPile.size = 0; add discardPile to drawPile; clean discardPile

    public List<Card> shuffleCards(List<Card> cardList) {

        Collections.shuffle(cardList);
        return cardList;
    }

    public void switchDeck() {
        shuffleCards(discardPile);
        drawPile.addAll(discardPile);
        discardPile.removeAll(discardPile);

    }

    public void addDiscard(Card card) {
        discardPile.add(card);
    }

}
