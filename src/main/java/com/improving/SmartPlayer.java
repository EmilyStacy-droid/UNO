package com.improving;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SmartPlayer implements IPlayer {
    List<Card> hand;



    public SmartPlayer(List<Card> hand) {
        this.hand = hand;

    }

    @Override
    public void newHand(List<Card> cards) {
        this.hand.clear();
        this.hand.addAll(cards);
    }

    @Override
    public Card draw(IGame game) {
        hand.add(game.draw());
        return game.draw();
    }

    @Override
    public void takeTurn(IGame game) {
        //take this out
        //System.out.println("player " + this.getName()+ " " +  this.hashCode() + " has " + this.hand);
        for(var card: hand) {
            filterCard(hand);
            int handSize = game.getnextPlayer().Handsize();
            if( handSize <= 2 && checkWildCard(hand)) {
                var wildCard = hand.stream().filter(c->c.getColors() == Colors.Wild).findFirst().get();
               playCard(wildCard,game);
                return;
            }

            if(game.isPlayable(card)) {
                if(handSize <=2 && checkSkipCard(hand)){
                    var skipCard = hand.stream().filter(c->c.getFaces() == Faces.Skip).findFirst().get();
                    playCard(skipCard, game);
                    return;
                }

                if(handSize <=2 && checkReverseCard(hand)){
                    var reverseCard = hand.stream().filter(c->c.getFaces() == Faces.Reverse).findFirst().get();
                    playCard(reverseCard, game);
                    return;
                }

                if(handSize <=2 && checkDrawTwoCard(hand)){
                    var drawTwoCard = hand.stream().filter(c->c.getFaces() == Faces.DrawTwo).findFirst().get();
                    playCard(drawTwoCard, game);
                    return;
                }

                playCard(card,game);
                return;
            }
        }

        var drawnCard = draw(game);
        if(game.isPlayable(drawnCard)) {
            //game.topCard = card;
            playCard(drawnCard, game);
        }


    }

    @Override
    public int Handsize() {
        return hand.size();
    }

    @Override
    public String getName() {
        return "Emily's smart player";
    }





    public void playCard(Card card, IGame game) {
        Colors declaredColor = declareColor(card,game);
        hand.remove(card);

        //declared color can be null
        game.playCard(card, java.util.Optional.ofNullable(declaredColor));
    }


    public Colors declareColor(Card card, IGame game) {
        var declaredColor = card.getColors();
        int numWildColorCardsinHand = 0;
        ArrayList<Colors> randomColors = new ArrayList<>();
        randomColors.add(Colors.Red);
        randomColors.add(Colors.Blue);
        randomColors.add(Colors.Green);
        randomColors.add(Colors.Yellow);

        if(randomColors.contains(declaredColor)){
            declaredColor = card.getColors();
        }else if (declaredColor.equals(Colors.Wild)) {
            numWildColorCardsinHand++;
            Collections.shuffle(randomColors);
            for(Card c: hand) {
                if (c.getColors() == randomColors.get(0)) {
                    declaredColor = c.getColors();
                    break;
                }
            }

            if(numWildColorCardsinHand == hand.size()){
                Collections.shuffle(randomColors);
                declaredColor = randomColors.get(0);
            }

        }

        return declaredColor;
    }

    private List filterCard (List<Card> cards) {
       cards = cards.stream()
                .sorted(Comparator.comparingInt(card -> card.getFaces().getPointValue()))
                .sorted(Comparator.comparing(card -> card.getColors().getPointValue()))
                .collect(Collectors.toList());
        return cards;

    }

    private boolean checkWildCard(List<Card> cards) {
        return cards.contains(Colors.Wild);
    }

    private boolean checkSkipCard(List<Card> cards) {
        return cards.contains(Faces.Skip);
    }

    private boolean checkReverseCard(List<Card> cards) {
        return cards.contains(Faces.Reverse);
    }

    private boolean checkDrawTwoCard(List<Card> cards) {
        return cards.contains(Faces.DrawTwo);
    }

}



