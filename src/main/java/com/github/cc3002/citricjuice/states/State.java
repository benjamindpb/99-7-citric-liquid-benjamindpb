package com.github.cc3002.citricjuice.states;

import com.github.cc3002.citricjuice.model.units.Player;

public class State {
    private Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public void changeState(State state){
        player.setState(state);
    }

    public void error(){
        throw new RuntimeException();
    }

    public boolean isInGame(){
        return false;
    }
    public boolean isReceivingStars(){
        return true;
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
    public boolean endedTurn(){
        return false;
    }

}
