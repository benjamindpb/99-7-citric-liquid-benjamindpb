package com.github.cc3002.citricjuice.phases;

/**
 * Fase de movimiento del player
 */
public class MovePlayer extends TurnPhase {

    /**
     * se cambia a la fase de batalla
     */
    public void battlePhase(){
        this.changePhase(new BattlePhase());
    }

    /**
     * el player activa la trampa carta del panel
     */
    public void activateTrapCard(){
        this.changePhase(new ActivateTrapCard());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean isMoving(){
        return true;
    }
}
