package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private List<Player> players = new ArrayList<Player>();


    public Game() {
        this.deck = new Deck();

        for (int i = 0; i < 3; i++) {
            // List<Card> hand = new ArrayList<>();
            var hand = drawInitialHand(new ArrayList<>());
            players.add(new Player(hand, this));
        }
    }

    public Card draw() {
        return deck.draw();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player number");
        int x = scanner.nextInt();
        System.out.println("There are " + x + "players.");

        System.out.println("New Game with " + players.size() + " players ");

//        for(var player: players) {
//            drawInitialHand();
//        }

        deck.getDiscardPile().add(deck.draw());

        System.out.println("The Top Card is " + deck.getDiscardPile().getLast());


        boolean gameInProgress = true;
        int turn = 0;
        while (gameInProgress) {
            try {
                turn += 1;
                System.out.println("Start Turn " + turn);

                for (var player : players) {
                    var play = player.takeTurn(this);
                    if (play != null) {
                        System.out.println("Player " + player.hashCode() + " played " + play);
                    }

                    if (player.Handsize() == 0) {
                        System.out.println("Player " + player.toString() + " has won the game on turn " + turn);
                        gameInProgress = false;
                    }

                }

            } catch (GameExitException ex) {
                System.out.println("The game is over!");
                gameInProgress = false;
            }
        }

    }

    public List drawInitialHand(List<Card> hand) {

        for (int i = 0; i < 7; i++) {
            hand.add(draw());
        }
        return hand;
    }

    //compare player's card to the top card in deck
    public boolean isLegal(Card card) { // TODO: NoSuchElementException

        if (deck.getDiscardPile().size() != 0) {
            return
                    card.getColors() == deck.getDiscardPile().getLast().getColors() ||
                            card.getFaces() == deck.getDiscardPile().getLast().getFaces();
        }
        return
                card.getFaces() == Faces.SpinColor ||
                        card.getFaces() == Faces.DrawFour;

    }

    public Deck getDeck() {
        return deck;
    }


    public List<Player> getPlayers() {
        return players;
    }
}
