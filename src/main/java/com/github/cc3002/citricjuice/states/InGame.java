package com.github.cc3002.citricjuice.states;

public class InGame extends State {

    public void receiveStars(){
        this.changeState(new ReceiveStars());
    }
    public void endTurn(){ //se activa cuando un player vuelve al juego despues del KO
        this.changeState(new EndTurn());
    }
    public boolean isInGame(){
        return true;
    }
}
