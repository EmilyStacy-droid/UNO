package com.improving;

public interface IPlayer extends IPlayerInfo {

    //int Handsize();

    Card draw(IGame game);

    void takeTurn(IGame game);
}
