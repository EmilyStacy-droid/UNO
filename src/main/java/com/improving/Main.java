package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);


        System.out.println("UNO game is going to start, how many players do you want? >>");

        int playerNum = scanner.nextInt();



        Game g = new Game(playerNum);

        System.out.println("New Game with " + playerNum + " players ");

        g.play();
    }
}
