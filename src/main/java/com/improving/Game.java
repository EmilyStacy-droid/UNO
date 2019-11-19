package com.improving;

import java.util.*;

public class Game implements IGame {
    private Deck deck = new Deck();
    private List<Player> players;
    int currentPlayer;
    public int turnEngine;
    public int turnDirection;
    public Colors declaredColor;
    int playerNum;
    Card topCard;
    Card firstCard;


    public Game(List<Player> players) {
        this.players = players;

        playerNum = players.size();

        for (Player player : players) {

            deck.shuffleCards(this.getDeck().getDrawPile());

            var hand = drawInitialHand(new ArrayList<>());

            player.hand = hand;

        }


    }

    public Card draw() {
        return deck.draw();
    }


@Override
    public void play(List<Player> players) {
        this.players = players;
        //first action: set firstCard when there is no topCard
        setFirstCard();

        setTopCard(firstCard, firstCard.getColors());

        boolean gameInProgress = true;
        int turn = 0;
        turnEngine = 0;
        turnDirection = 1;
        while (gameInProgress) {

                turn += 1;

                System.out.println("Start Turn " + turn);
                if (turnEngine < 0) {
                    turnEngine = (turnEngine + playerNum) % playerNum;
                }

                currentPlayer = turnEngine % playerNum;

                players.get(currentPlayer).takeTurn(this);

            if (players.get(currentPlayer).Handsize() == 1) {
                System.out.println("Player " +  this.hashCode() + " shouts out UNO" );
            }


                if (players.get(currentPlayer).Handsize() == 0) {
                    System.out.println("Player " +  players.get(currentPlayer).hashCode() + " has won the game on turn " + turn);
                    gameInProgress = false;
                }

                turnEngine += turnDirection;

        }

    }


    //constructed in game
    public List drawInitialHand(List<Card> hand) {

        for (int i = 0; i < 7; i++) {
            hand.add(draw());
        }
        return hand;
    }

    //compare player's card to the top card in deck
    @Override
    public boolean isPlayable(Card card) { // TODO: NoSuchElementException

        //if the actual the color of the card matches the topcard color or matches the declared color, then the card is playable
        //if the card face is the same as the top card face, then the card is playable
        if (card.getColors() == Colors.Wild || declaredColor ==card.getColors() ||card.getFaces() == topCard.getFaces() )  {
            return true;
        } else{   //otherwise, it's not playable
            return false;
        }

    }

    private Deck getDeck() {
        return deck;
    }


    public List<IPlayerInfo> getPlayerInfo() {
        ArrayList<IPlayerInfo> playerInfo = new ArrayList<>();
        for(IPlayer player:players){
            playerInfo.add(player);

        }        return playerInfo;
    }

    @Override
    public IPlayerInfo getnextPlayer() {
        if(turnDirection == 1){
        if (currentPlayer == (playerNum - 1)){
            return players.get(0);
        }else {
            return players.get(currentPlayer + 1);
        }
        }
        if(turnDirection == (-1)){
            if(currentPlayer == 0) {
                return players.get(playerNum -1);

            }else {
               return players.get(currentPlayer -1);
            }
        }
        return null;
    }

    @Override
    public IPlayerInfo getnextnextPlayer() {
        return null;
    }

    @Override
    public IPlayerInfo getpreviousPlayer() {
        if(turnDirection == 1){
            if (currentPlayer == (0)){
                return players.get(playerNum - 1);
            }else {
                return players.get(currentPlayer - 1);
            }
        }
        if(turnDirection == (-1)){
            if(currentPlayer == playerNum -1) {
                return players.get(0);

            }else {
                return players.get(currentPlayer -1);
            }
        }
        return null;
    }


    @Override
    public IDeck getDeckInfo() {
        return null;
    }


    public int getCurrentPlayer() {
        return currentPlayer;
    }

    //topCard settings
    public Card getTopCard() {
        return topCard;

    }

    public Card setFirstCard() {
        if(getDeck().getDrawPile().size() > 0){
        var drawCard = draw();

        if (!hasAction(drawCard)) {
            firstCard = drawCard;
            System.out.println("The First Card is " + firstCard);
            getDeck().getDiscardPile().add(firstCard);
            return firstCard;

        }else {
            return setFirstCard();
        }
        }

        firstCard = getDeck().getDiscardPile().get(0);
        return firstCard;
    }

    public void setTopCard(Card topCard, Colors declaredColor) {

        if (topCard == null) {
            topCard = setFirstCard();
            declaredColor = topCard.getColors();

        } else {
            topCard = getDeck().getDiscardPile().getLast();
            declaredColor = topCard.getColors();
        }
            if (topCard.getColors().getintValue() == 0) {
                Random random = new Random();
                int num = random.nextInt(4);
                if (num == 0) {
                    declaredColor = Colors.Blue;
                }

                if (num == 1) {
                    declaredColor = Colors.Red;
                }

                if (num == 2) {
                    declaredColor = Colors.Yellow;
                }

                if (num == 3) {
                    declaredColor = Colors.Green;
                }
            }

            //topCard.setColor(declaredColor);

            System.out.println("The top card is " + topCard);
            deck.getDiscardPile().add(topCard);
            this.topCard = topCard;
            this.declaredColor = declaredColor;
        }



    //only happen when there is not a declared color from the user


    //forcely sign a declared color
    public Colors forcedPickedDeclaredColor() {
        ArrayList<Colors> randomColors = new ArrayList<>();
        randomColors.add(Colors.Red);
        randomColors.add(Colors.Blue);
        randomColors.add(Colors.Green);
        randomColors.add(Colors.Yellow);
        return randomColors.get(0);
    }

    //check if declared Colors are valid

    public Boolean isValidDeclaredColor(Optional<Colors> declaredColor) {
        boolean isValid = false;
        Colors[] validColor = {Colors.Red, Colors.Green, Colors.Yellow, Colors.Blue};
        for (Colors color : validColor) {
            if (declaredColor.get().ordinal() == color.ordinal()) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public void playCard(Card card, Optional<Colors> color) {

        Optional<Colors> declaredColor  = color;

        this.getDeck().getDiscardPile().add(card);

        System.out.println(" Player " + players.get(currentPlayer).hashCode() + " played " + card);
        //check player-declaredCard to see if a declared color is claimed


        if (declaredColor.isEmpty()) {
            if (card.getColors().getintValue() != 0) {
                setTopCard(card, card.getColors());
            } else {
                setTopCard(card, forcedPickedDeclaredColor());
            }

        } else if (declaredColor.isPresent()) {
            if (!isValidDeclaredColor(declaredColor)) {
                declaredColor = Optional.ofNullable(forcedPickedDeclaredColor());
            }
            setTopCard(card, declaredColor.orElseThrow());
            System.out.println("The color of TopCard is now " + declaredColor);
        }


        if (players.get(currentPlayer).Handsize() != 0) {
            if (hasAction(topCard)) {
                executeCardAction(topCard, this);
            }
        }
    }

    public void executeCardAction(Card card, Game game) {
        if (card.getFaces().equals(Faces.DrawTwo)) {
            if (turnDirection == 1) {
                if (currentPlayer == (playerNum - 1)) {

                    int nextPlayer = 0;
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew two cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    turnEngine += turnDirection;

                } else {
                    int nextPlayer = currentPlayer + 1;
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew two cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    turnEngine += turnDirection;

                }
            } else if (turnDirection == (-1)) {
                if (currentPlayer == (playerNum - 1)) {
                    int nextPlayer = 2;
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew two cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    turnEngine += turnDirection;
                } else {
                    int nextPlayer = currentPlayer + 1;
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew two cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    turnEngine += turnDirection;
                }


            }
        }

        if (card.getFaces().equals(Faces.DrawFour)) {
            if (turnDirection == 1) {
                if (currentPlayer == (playerNum - 1)) {
                    int nextPlayer = 0;
                    System.out.println("The player" + players.get(nextPlayer).hashCode() + " drew four cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    System.out.println("next player is " + nextPlayer);
                    turnEngine += turnDirection;

                } else {
                    int nextPlayer = currentPlayer + 1;
                    System.out.println("The player" + players.get(nextPlayer).hashCode() + " drew four cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    System.out.println("next player is " + nextPlayer);
                    turnEngine += turnDirection;

                }
            } else if (turnDirection == -1) {
                if (currentPlayer == 0) {
                    int nextPlayer = (playerNum - 1);
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew four cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    System.out.println("next player is " + nextPlayer);
                    turnEngine += turnDirection;
                } else {
                    int nextPlayer = currentPlayer - 1;
                    System.out.println(" The player " + players.get(nextPlayer).hashCode() + " drew four cards.");
                    players.get(nextPlayer).draw(this);
                    players.get(nextPlayer).draw(this);
                    turnEngine += turnDirection;
                }
            }

        }
        if (card.getFaces().equals(Faces.Reverse)) {
            System.out.println(" The player " + players.get(currentPlayer).hashCode() + " reverse the order.");
            turnDirection = turnDirection * (-1);
        }

        if (card.getFaces().equals(Faces.Skip)) {
            turnEngine += turnDirection;

        }
    }

    public boolean hasAction(Card card) {
        if (card.getFaces().equals(Faces.DrawFour)) {
            return true;
        } else if (card.getFaces().equals(Faces.DrawTwo)) {
            return true;
        } else if (card.getFaces().equals(Faces.Skip)) {
            return true;
        } else if (card.getFaces().equals(Faces.Reverse)) {
            return true;
        }else if(card.getColors().equals(Colors.Wild)){
            return true;
        }
        return false;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


}
