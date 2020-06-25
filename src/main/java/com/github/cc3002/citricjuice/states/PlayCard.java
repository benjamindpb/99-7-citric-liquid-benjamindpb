package com.github.cc3002.citricjuice.states;

public class PlayCard extends State {
    public void move(){
        this.changeState(new MovePlayer());
    }

    public boolean playingCard(){
        return true;
    }
}
