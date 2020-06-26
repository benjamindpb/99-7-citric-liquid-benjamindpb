package com.github.cc3002.citricjuice.states;

/**
 * Representa cuando un player sale del juego (KO)
 */
public class OutOfGame extends TurnPhase {
    /**
     * el player vuelve al juego
     */
    public void backToGame(){
        this.changePhase(new InGame());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean isOutOfGame(){
        return true;
    }
}
