package com.github.cc3002.citricjuice.states;

public class OutOfGame extends State {
    public void backToGame(){
        this.changeState(new InGame());
    }
    public void endTurn(){
        this.changeState(new EndTurn());
    }

    public boolean isOutOfGame(){
        return true;
    }
}
