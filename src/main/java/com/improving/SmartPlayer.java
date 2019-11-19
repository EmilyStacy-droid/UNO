package com.improving;

import java.util.List;

public class SmartPlayer implements IPlayer {
    List<Card> hand;

    public SmartPlayer() {

    }


    public SmartPlayer(List<Card> hand) {
        this.hand = hand;

    }
    @Override
    public Card draw(IGame game) {
        return null;
    }

    @Override
    public void takeTurn(IGame game) {

    }

    @Override
    public int Handsize() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
