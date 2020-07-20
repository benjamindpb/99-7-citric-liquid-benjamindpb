package com.github.cc3002.citricjuice.phases;

/**
 * Esta clase representa la fase de activar el panel en que cae
 * un player
 */
public class ActivatePanel extends TurnPhase {

    /**
     * cambia a la fase cuando el jugador sale del juego
     */
    public void outOfGame(){
        this.changePhase(new OutOfGame());
    }

    /**
     * el player vuelve a la fase inicial
     */
    public void backToGame(){
        this.changePhase(new InGame());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean activatingPanel(){
        return true;
    }
}
