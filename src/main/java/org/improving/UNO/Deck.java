package org.improving.UNO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {
    private final LinkedList<Card> drawPile = new LinkedList<>();
    private final LinkedList<Card> discardPile = new LinkedList<>();
    private Player player;


    Random random = new Random();

    public Deck() {
       createDeck();
    }

    public void createDeck(){
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

    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }


// draw method

    public Card draw() {
        if (drawPile.size() == 0) {
            switchDeck();
        }

        var randomIndex = random.nextInt(drawPile.size());

        var card = drawPile.get(randomIndex);

        // is it deck or drawPile?
        drawPile.remove(randomIndex);

        return card;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }




    public LinkedList<Card> shuffleCards(LinkedList<Card> cardLinkedList) {

        Collections.shuffle(cardLinkedList);
        return cardLinkedList;
    }

    public void switchDeck() {
        shuffleCards(discardPile);
        drawPile.addAll(discardPile);
        discardPile.removeAll(discardPile);

    }


}
