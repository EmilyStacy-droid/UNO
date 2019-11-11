package org.improving.UNO;

public class Game {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        hand.start();
    }
}
