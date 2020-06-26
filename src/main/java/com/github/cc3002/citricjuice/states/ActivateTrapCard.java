package com.github.cc3002.citricjuice.states;

/**
 * Esta clase representa la fase de activar la carta trampa del panel
 */
public class ActivateTrapCard extends TurnPhase {
    /**
     * se cambia a la fase de activacion del panel
     */
    public void activatePanelPhase(){
        this.changePhase(new ActivatePanel());
    }

    /**
     * si por algun efecto el player llega a morir entonces, sale del juego
     */
    public void outOfGame() { this.changePhase(new OutOfGame());}

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean activatingTrapCard(){
        return true;
    }
}
