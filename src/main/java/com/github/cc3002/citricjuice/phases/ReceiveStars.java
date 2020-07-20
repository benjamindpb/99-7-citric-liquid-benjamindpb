package com.github.cc3002.citricjuice.phases;

/**
 * Primera fase del turno cuando el player recibe estrellas
 */
public class ReceiveStars extends TurnPhase {

    public void playCard(){
        this.changePhase(new PlayCard());
    }

    public void movePlayer(){
        this.changePhase(new MovePlayer());
    }

    public boolean isReceivingStars(){
        return true;
    }
}
