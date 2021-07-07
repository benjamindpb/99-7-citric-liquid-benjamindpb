package com.github.cc3002.citricjuice.phases;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * Esta clase es la encargada de representar las fases del turno de un player.
 * Representa el patron de diseño STATE
 */
public class TurnPhase {
    private Player player;

    /**
     * @param player referenciado
     */
    public void setPlayer(Player player){
        this.player = player;
    }

    /**
     * @param turnPhase fase a la que se esta cambiando
     */
    public void changePhase(TurnPhase turnPhase){
        player.setTurnPhase(turnPhase);
    }

    /**
     * Todos los metodos hacia abajo retornan false. Se invocan solo si
     * estamos preguntando si estamos en una fase erronea.
     */

    public boolean isInGame(){
        return false;
    }
    public boolean isReceivingStars(){
        return false;
    }
    public boolean playingCard(){
        return false;
    }
    public boolean isMoving(){
        return false;
    }
    public boolean isInBattle(){
        return false;
    }
    public boolean activatingTrapCard(){
        return false;
    }
    public boolean activatingPanel(){
        return false;
    }
    public boolean isOutOfGame(){
        return false;
    }

    /**
     * Todos estos metodos hacia abajo retornan un error porque son llamados
     * cuando estamos en una fase que no permite el paso a una fase, aun cuando
     * nosotros lo queramos asi. Por ej:
     *
     * Si estamos en la fase ingame no podemos ir a la fase battle en un paso, tenemos
     * que pasar por otras fases antes de llegar a battle. Por esto es que salta un ERROR
     */
    public void receiveStars() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to receive stars phase.");
    }
    public void playCard() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to play card phase.");
    }
    public void movePlayer() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to move player phase.");
    }
    public void battlePhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to battle phase.");
    }
    public void activateTrapCard() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to activate trap card phase.");
    }
    public void activatePanelPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to activate panel phase.");
    }
    public void outOfGame() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to out of game phase.");
    }
    public void backToGame() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to back to game phase.");
    }
}
