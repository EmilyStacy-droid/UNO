package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame{
    void playCard(Card card, Optional<Colors> declaredColor);

    boolean isPlayable(Card card);

    Card draw();

    public void play(List<Player> players);

    public List<IPlayerInfo> getPlayerInfo();

    public IPlayerInfo getnextPlayer();

    IPlayerInfo getnextnextPlayer();

    public IPlayerInfo getpreviousPlayer();

    public IDeck getDeckInfo();


}

