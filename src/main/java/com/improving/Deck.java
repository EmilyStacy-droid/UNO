package com.improving;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Deck implements IDeck {
    private LinkedList<Card> drawPile = new LinkedList<>();
    private LinkedList<Card> discardPile = new LinkedList<>();
    //private LinkedList<Card> allCardsList = new LinkedList<>();

    Random random = new Random();

    public Deck() {
       createDeck();
    }

    private void createDeck(){
        for (int i = 0; i < 2; i++) {
            for (var face : Faces.values()) {
                for (var color : Colors.values()) {

                    if (face.getintValue() == 1 && color.getintValue() == 1) {
                        drawPile.add(new Card(face, color));
                        //allCardsList.add(new Card(face, color));
                    }

                    if (face.getintValue() == 0 && color.getintValue() == 1) {
                        drawPile.add(new Card(face, color));
                        //allCardsList.add(new Card(face, color));

                    }
                }
            }
        }
        for (int j = 0; j < 4; j++) {
            for (var face : Faces.values()) {
                for (var color : Colors.values()) {
                    if (face.getintValue() == (-1) && color.getintValue() == 0) {
                        drawPile.add(new Card(face, color));
                        //allCardsList.add(new Card(face, color));
                    }
                }
            }
        }

    }
    //set it private after testing

    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }

    @Override
    public int getDrawPilesSize(){
        return drawPile.size();

    }

// draw method

    public Card draw() {

        if (drawPile.size() <= 0) {
            switchDeck();
        }
        var randomIndex = random.nextInt(drawPile.size());


        var card = drawPile.get(randomIndex);

        drawPile.remove(card);



        return card;
    }

    @Override
    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }




    public LinkedList<Card> shuffleCards(LinkedList<Card> cardLinkedList) {

        Collections.shuffle(cardLinkedList);
        return cardLinkedList;
    }

    private void switchDeck() {
        shuffleCards(discardPile);
        System.out.println("discardPile has " + discardPile.size() );
        drawPile.addAll (discardPile);
        System.out.println("drawPile has " + drawPile.size() );
        discardPile.clear();
        Collections.shuffle(drawPile);

    }


}
