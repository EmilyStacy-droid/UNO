package org.improving.UNO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private List<Player> players = new ArrayList<Player>();
    int currentPlayer;
    int turnEngine;
    int turnDirection;
    int playerNum;

    public Game() {
        this.deck = new Deck();

        for (int i = 0; i < 3; i++) {
            // List<Card> hand = new ArrayList<>();
            deck.shuffleCards(this.getDeck().getDrawPile());
            var hand = drawInitialHand(new ArrayList<>());
            players.add(new Player(hand, this));

        }
        playerNum=players.size();
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
        turnEngine=0;
        turnDirection=1;
        while (gameInProgress) {
            try {
                turn += 1;

                System.out.println("Start Turn " + turn);
                if(turnEngine < 0){
                    turnEngine = (turnEngine + playerNum) % playerNum;
                }

                    currentPlayer = turnEngine % playerNum;

                    getPlayers().get(currentPlayer).takeTurn(this);


                    if (getPlayers().get(currentPlayer).Handsize() == 0) {
                        System.out.println("Player " + getPlayers().get(currentPlayer).hashCode() + " has won the game on turn " + turn);
                        gameInProgress = false;
                    }

                    turnEngine += turnDirection;

            } catch (GameExitException ex) {
                System.out.println("The game is over!");
                gameInProgress = false;
            }
        }

    }

    private void getFirstCard() {
        deck.getDiscardPile().add(deck.draw());

        System.out.println("The Top Card is " + deck.getDiscardPile().getLast());
    }

    private List drawInitialHand(List<Card> hand) {

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

    private Deck getDeck() {
        return deck;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }



//    public boolean examineAction(Faces face) {
//        return  face == Faces.Reverse ||
//                face == Faces.DrawFour||
//                face == Faces.Skip ||
//                face == Faces.DrawTwo;
//
//    }
    public Card playCard(Card card) {
       // getPlayers().get(currentPlayer).getHand().remove(card);

        this.getDeck().getDiscardPile().add(card);

        if (card != null) {
            System.out.println(" Player " +getPlayers().get(currentPlayer).hashCode() + " played " + card);
            if(card.getFaces() == Faces.Reverse){
                System.out.println(" The player " + getPlayers().get(currentPlayer).hashCode() + " reverse the order.");
                turnDirection = turnDirection * (-1);
            }

            if(card.getFaces() == Faces.DrawFour) {
               if(turnDirection == 1) {
                   if (currentPlayer == 2) {
                       int nextPlayer = 0;
                       System.out.println("The player" + getPlayers().get(nextPlayer).hashCode() + " drew four cards.");
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       System.out.println("next player is " + nextPlayer);
                       turnEngine += turnDirection;
                       return null;
                   } else {
                       int nextPlayer = currentPlayer + 1;
                       System.out.println("The player" + getPlayers().get(nextPlayer).hashCode() + " drew four cards.");
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       getPlayers().get(nextPlayer).draw(this);
                       System.out.println("next player is " + nextPlayer);
                       turnEngine += turnDirection;
                       return null;

                   }
               }

                   if(turnDirection == -1) {
                       if(currentPlayer == 0){
                           int nextPlayer = 2;
                           System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew four cards.");
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           turnEngine+=turnDirection;
                           return null;
                       }else {
                           int nextPlayer = currentPlayer - 1;
                           System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew four cards.");
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           getPlayers().get(nextPlayer).draw(this);
                           turnEngine+=turnDirection;
                           return null;

                       }
                   }

               }

            if (card.getFaces() == Faces.DrawTwo) {
                if(turnDirection ==1) {
                    if(currentPlayer ==2) {

                    int nextPlayer= 0;
                    System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew two cards.");
                    getPlayers().get(nextPlayer).draw(this);
                    getPlayers().get(nextPlayer).draw(this);
                     turnEngine+=turnDirection;
                    return null;
                    } else {
                        int nextPlayer = currentPlayer +1;
                        System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew two cards.");
                        getPlayers().get(nextPlayer).draw(this);
                        getPlayers().get(nextPlayer).draw(this);
                        turnEngine+=turnDirection;
                        return null;
                    }
                }
                if(turnDirection == -1) {
                    if(currentPlayer == 0){
                        int nextPlayer = 2;
                        System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew two cards.");
                        getPlayers().get(nextPlayer).draw(this);
                        getPlayers().get(nextPlayer).draw(this);
                        System.out.println("next player is " + nextPlayer);
                        turnEngine+=turnDirection;
                        return null;
                    }else {
                        int nextPlayer = currentPlayer - 1;
                        System.out.println(" The player " + getPlayers().get(nextPlayer).hashCode() + " drew two cards.");
                        getPlayers().get(nextPlayer).draw(this);
                        getPlayers().get(nextPlayer).draw(this);
                        turnEngine+=turnDirection;
                        return null;
                    }
                }

            }

            if (card.getFaces() == Faces.Skip) {
                turnEngine+=turnDirection;
                return null;
            }

        }

        return card;

    }
}
