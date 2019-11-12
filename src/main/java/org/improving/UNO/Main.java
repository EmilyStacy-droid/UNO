package org.improving.UNO;

public class Main {
    public static void main(String[] args) {
        Deck d = new Deck();
        Player p = new Player(d);
        Game g = new Game(p,d);
        g.play();
    }
}
