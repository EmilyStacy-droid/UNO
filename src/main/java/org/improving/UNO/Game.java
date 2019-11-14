package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private List<Player> players = new ArrayList<Player>();
    int currentPlayer =0;


    public Game() {
        this.deck = new Deck();

        for (int i = 0; i < 3; i++) {
            // List<Card> hand = new ArrayList<>();
            deck.shuffleCards(this.getDeck().getDrawPile());
            var hand = drawInitialHand(new ArrayList<>());
            players.add(new Player(hand, this));
        }
    }

    public Card draw() {
        return deck.draw();
    }

    public void playGame() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the player number");
//        int x = scanner.nextInt();
//        System.out.println("There are " + x + "players.");

        System.out.println("New Game with " + players.size() + " players ");

        getFirstCard();

        boolean gameInProgress = true;
        int turn = 0;

        while (gameInProgress) {
            try {
                turn += 1;

                System.out.println("Start Turn " + turn);

                for (int i=0; i< 3; i++) {
                    currentPlayer = i;
                    var play = getPlayers().get(currentPlayer).takeTurn(this);


                    if (play != null) {
                        System.out.println("Player " +getPlayers().get(currentPlayer).hashCode() + " played " + play);
                    }

                    if (getPlayers().get(currentPlayer).Handsize() == 0) {
                        System.out.println("Player " + getPlayers().get(currentPlayer).toString() + " has won the game on turn " + turn);
                        gameInProgress = false;
                    }

                }

            } catch (GameExitException ex) {
                System.out.println("The game is over!");
                gameInProgress = false;
            }
        }

    }

    public void getFirstCard() {
        deck.getDiscardPile().add(deck.draw());

        System.out.println("The Top Card is " + deck.getDiscardPile().getLast());
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

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Card playCard(Card card) {
        getPlayers().get(currentPlayer).getHand().remove(card);
        this.getDeck().getDiscardPile().add(card);

       if(card.getFaces() == Faces.DrawFour) {
           if(currentPlayer <2) {
               currentPlayer ++;
               System.out.println("The player" + getPlayers().get(currentPlayer).hashCode() + "drew four cards.");
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               System.out.println("current player is " + currentPlayer);
               return null;
           }
           if(currentPlayer > 2) {
               currentPlayer =0;
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               getPlayers().get(currentPlayer).getHand().add(draw());
               System.out.println("current player is " + currentPlayer);
               return null;
           }

       }

        if (card.getFaces() == Faces.DrawTwo) {
            if(currentPlayer < 2) {
                currentPlayer ++;
                System.out.println("The player" + getPlayers().get(currentPlayer).hashCode() + "drew two cards.");
                getPlayers().get(currentPlayer).getHand().add(draw());
                getPlayers().get(currentPlayer).getHand().add(draw());
                return null;
            }

            if(currentPlayer > 2) {
                currentPlayer = 0;
                System.out.println("The player" + getPlayers().get(currentPlayer).hashCode() + "drew two cards.");
                getPlayers().get(currentPlayer).getHand().add(draw());
                getPlayers().get(currentPlayer).getHand().add(draw());
                return null;
            }
//
        }

        return card;

    }
}
