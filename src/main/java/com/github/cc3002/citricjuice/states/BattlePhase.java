package com.github.cc3002.citricjuice.states;

public class BattlePhase extends State {

    public void outOfGame(){
        this.changeState(new OutOfGame());
    }

    public void activateTrapCard(){
        this.changeState(new ActivatePanel());
    }

    public boolean isInBattle(){
        return true;
    }
}
