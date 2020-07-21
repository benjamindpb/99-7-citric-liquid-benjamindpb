package com.github.cc3002.citricjuice.phases;

/**
 * Representa cuando un player sale del juego (KO)
 */
public class OutOfGamePhase extends TurnPhase {
    /**
     * el player vuelve al juego
     */
    public void backToGame(){
        this.changePhase(new InGamePhase());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean isOutOfGame(){
        return true;
    }
}
