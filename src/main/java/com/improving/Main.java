package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        List<Player> players = new ArrayList<Player>();

        System.out.println("UNO game is going to start, how many players do you want? >>");
        System.out.println();

        int playerNum = scanner.nextInt();

        for(int i=0; i<playerNum;i++){
        players.add(new Player());
        }

        Game g = new Game(players);

        System.out.println("New Game with " + players.size() + " players ");

        g.play(players);
    }
}
