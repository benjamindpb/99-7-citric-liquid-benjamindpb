package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Boss Panel
 */
public class BossPanel extends AbstractPanel{


    /**
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public BossPanel(int row, int column) {
        super(row, column);
    }
    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public BossPanel() {
        super(0,0);
    }

    /**
     * In this panel the player battles with a random Boss unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }
}
