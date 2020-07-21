package com.github.cc3002.citricjuice.phases;

/**
 * Esta clase representa la fase de activar el panel en que cae
 * un player
 */
public class ActivatePanelPhase extends TurnPhase {

    /**
     * cambia a la fase cuando el jugador sale del juego
     */
    public void outOfGame(){
        changePhase(new OutOfGamePhase());
    }

    /**
     * el player vuelve a la fase inicial
     */
    public void backToGame(){
        changePhase(new InGamePhase());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean activatingPanel(){
        return true;
    }
}
