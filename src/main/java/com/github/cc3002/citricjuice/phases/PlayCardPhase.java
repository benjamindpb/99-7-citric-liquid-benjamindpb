package com.github.cc3002.citricjuice.phases;

/**
 * Fase de jugar una carta
 */
public class PlayCardPhase extends TurnPhase {
    /**
     * cambiamos a la fase de movimiento del player
     */
    public void movePlayer(){
        this.changePhase(new MovePlayerPhase());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean playingCard(){
        return true;
    }
}
