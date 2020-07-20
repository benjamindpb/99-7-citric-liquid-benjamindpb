package com.github.cc3002.citricjuice.phases;

/**
 * Esta clase representa la fase de batalla
 */
public class BattlePhase extends TurnPhase {

    /**
     * si se muere, entonces se tiene que ir fuera del juego
     */
    public void outOfGame(){
        this.changePhase(new OutOfGame());
    }

    /**
     * si queda vivo, se sigue con la fase de activacion de carta trampa
     */
    public void activateTrapCard(){
        this.changePhase(new ActivateTrapCard());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean isInBattle(){
        return true;
    }
}
