package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Drop Panel
 */
public class DropPanel extends AbstractPanel{

    /**
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public DropPanel(int row, int column) {
        super(row, column);
    }

    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public DropPanel() {
        super(0,0);
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
