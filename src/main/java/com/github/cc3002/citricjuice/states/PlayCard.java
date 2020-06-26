package com.github.cc3002.citricjuice.states;

/**
 * Fase de jugar una carta
 */
public class PlayCard extends TurnPhase {
    /**
     * cambiamos a la fase de movimiento del player
     */
    public void movePlayer(){
        this.changePhase(new MovePlayer());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean playingCard(){
        return true;
    }
}
