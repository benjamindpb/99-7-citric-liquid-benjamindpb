package com.github.cc3002.citricjuice.states;

public class MovePlayer extends State {

    public void battlePhase(){
        this.changeState(new BattlePhase());
    }

    public void activateCard(){
        this.changeState(new ActivateTrapCard());
    }

    public void activatePanel() { this.changeState(new ActivatePanel());}

    public boolean isMoving(){
        return true;
    }
}
