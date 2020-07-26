package com.github.cc3002.citricjuice.phases;

/**
 * Primera fase del turno cuando el player recibe estrellas
 */
public class ReceiveStarsPhase extends TurnPhase {

    public void playCard(){
        this.changePhase(new PlayCardPhase());
    }

    public void movePlayer(){
        this.changePhase(new MovePlayerPhase());
    }

    public boolean isReceivingStars(){
        return true;
    }
}
