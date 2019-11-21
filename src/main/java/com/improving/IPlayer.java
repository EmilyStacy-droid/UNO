package com.improving;

import java.util.List;

public interface IPlayer extends IPlayerInfo {

    void newHand(List<Card> cards);

    Card draw(IGame game);

    void takeTurn(IGame game);
}
