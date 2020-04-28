package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

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
        player.setCurrentHP(player.getCurrentHP() + 1);
        normaCheck(player);
    }

    /**
     * Make a normal check for a player
     *
     * @param player who will have a normal check
     *
     */
    private void normaCheck(Player player) {
        int norma = player.getNormaLevel();
        //...to do...
    }
}
