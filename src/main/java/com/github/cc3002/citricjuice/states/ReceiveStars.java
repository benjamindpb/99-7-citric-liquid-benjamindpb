package com.github.cc3002.citricjuice.states;

public class ReceiveStars extends State {

    public void PlayCard(){
        this.changeState(new PlayCard());
    }

    public void activateTrapCard(){
        this.changeState(new ActivateTrapCard());
    }

    public boolean isReceivingStars(){
        return true;
    }
}
