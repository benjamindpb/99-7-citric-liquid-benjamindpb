package com.github.cc3002.citricjuice.model.unit.state;

import com.github.cc3002.citricjuice.model.unit.Player;

public class PlayerState {
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    protected void changeState(PlayerState state){
        player.setPlayerState(state);
    }
    public void error(){
        throw new RuntimeException();
    }

    public void quitGame() {
        error();
    }

    public void enterGame() {
        error();
    }

    public boolean isInGame() {
        return false;
    }

    public boolean isOutOfGame() {
        return false;
    }
}
