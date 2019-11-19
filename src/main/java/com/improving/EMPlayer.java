package com.improving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EMPlayer implements IPlayer {
    List<Card> hand;

    public EMPlayer() {

    }


    public EMPlayer(List<Card> hand) {
        this.hand = hand;

    }


    @Override
    public Card draw(IGame game) {
        hand.add(game.draw());
        return game.draw();
    }

    @Override
    public int Handsize(){

        return hand.size();
    }

    @Override
    public void takeTurn(IGame game) {
        System.out.println("player " + this.hashCode() + " has " + this.hand);
        for(var card: hand) {
            if(game.isPlayable(card)) {
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
            declaredColor = null;
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
//        boolean declaredColorinHand = false;
//
//        //check the wild card is removed
//
//        int numWildColorCardsinHand = 0;
//
//        if(card.getColors().equals(Colors.Wild) ) {
//            while(!declaredColorinHand) {
//                Collections.shuffle(randomColors);
//
//                for(Card c:hand) {
//                    if(c.getColors() == randomColors.get(0)){
//                        declaredColorinHand =true;
//                        declaredColor = c.getColors();
//                        break;
//                    }
//
//                    if(card.getColors().equals(Colors.Wild)){
//                        numWildColorCardsinHand++;
//                    }
//                }
//
//                if(numWildColorCardsinHand == hand.size()){
//                    Collections.shuffle(randomColors);
//                    declaredColorinHand =true;
//                    declaredColor = randomColors.get(0);
//                }
//
//            }
//
//
//        }


        return declaredColor;
    }




}

//else if(topCard.getColors() == Colors.Wild){
//        declaredColor= Optional.of(topCard.getColors()) ;