package org.improving.UNO;

public interface PlayerInterface {

    Card draw(Game game);

    int Handsize();

    void takeTurn(Game game);
}
