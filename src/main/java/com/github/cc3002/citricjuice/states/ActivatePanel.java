package com.github.cc3002.citricjuice.states;

public class ActivatePanel extends State {
    public void endTurn(){
        this.changeState(new EndTurn());
    }

    public boolean activatingPanel(){
        return true;
    }
}
