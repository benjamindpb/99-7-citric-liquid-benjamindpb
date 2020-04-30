package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Neutral Panel
 */
public class NeutralPanel extends AbstractPanel{


    /**
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public NeutralPanel(int row, int column) {
        super(row, column);
    }

    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public NeutralPanel() {
        super(0,0);
    }

    /**
     * This type of panel does not have any type of effect on the player,
     * if a player falls on one of these panels, then his turn ends without changes.
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }


}
