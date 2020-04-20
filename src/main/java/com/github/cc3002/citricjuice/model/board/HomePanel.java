package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

/**
 * This class represent a Home Panel
 */
public class HomePanel extends AbstractPanel{

    /**
     * The player recovers one point of HP and realize a Norma check
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
        normaCheck(player);
    }

    private void normaCheck(Player player) {
        int norma = player.getNormaLevel();
        int stars = player.getStars();
        int wins = player.
        if(norma == 1){
            if(player.getStars() == 10)
                player.normaClear();
        }
        if(norma == 2){


        }
        if(norma == 3){

        }
        if(norma == 4){

        }
        if(norma == 5){

        }
    }
}
