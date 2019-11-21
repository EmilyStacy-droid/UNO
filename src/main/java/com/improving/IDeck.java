package com.improving;

import java.util.LinkedList;

public interface IDeck {

    int getDrawPilesSize();

    LinkedList<Card> getDiscardPile();
}
