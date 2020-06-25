package com.github.cc3002.citricjuice.states;

public class ActivateTrapCard extends State {
    public void activatePanel(){
        this.changeState(new ActivatePanel());
    }

    public void outOfGame() { this.changeState(new OutOfGame());}

    public boolean activatingTrapCard(){
        return true;
    }
}
