package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * This class represent a Home Panel
 */
public class HomePanel extends AbstractPanel{

    /**
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public HomePanel(int row, int column) {
        super(row, column);
    }

    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public HomePanel() {
        super(0,0);
    }

    /**
     * The player recovers one point of HP and realize a Norma check
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        normaCheck(player);
        player.setCurrentHP(player.getCurrentHP() + 1);
    }

    /**
     * Make a normal check for a player
     *
     * @param player who will have a normal check
     *
     */
    private void normaCheck(Player player) {
        int norma = player.getNormaLevel();
        int stars = player.getStars();
        int wins = player.getWins();

        if(norma == 1){
            if(stars >= 10){
                player.normaClear();
            }
        }
        else if(norma == 2){
            if(stars >= 30 || wins >= 1) {
                player.normaClear();
            }
        }
        else if(norma == 3){
            if(stars >= 70 || wins >= 5) {
                player.normaClear();
            }
        }
        else if(norma == 4){
            if(stars >= 120 || wins >= 9) {
                player.normaClear();
            }
        }
        else if(norma == 5){
            if(stars >= 200 || wins >= 14) {
                player.normaClear(); // player wins
            }
        }
    }
}
