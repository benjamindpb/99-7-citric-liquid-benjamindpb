package com.github.cc3002.citricjuice.states;

/**
 * Esta clase representa la fase de cuando un player esta en juego
 */
public class InGame extends TurnPhase {

    /**
     * se reciben estrellas por estar en el juego y haber comenzado el turno
     */
    public void receiveStars(){
        this.changePhase(new ReceiveStars());
    }

    /**
     * @return true, porque efectivamente estamos en su fase xD
     */
    public boolean isInGame(){
        return true;
    }
}
