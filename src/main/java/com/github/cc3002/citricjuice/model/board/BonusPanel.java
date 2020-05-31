package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * This class represent a Bonus Panel
 */
public class BonusPanel extends AbstractPanel{

    /**
     * Constructor for a default panel without any special behaviour.
     *
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public BonusPanel(int row, int column) {
        super(row, column);
    }

    /**
     * This Constructor creates a panel in the (0,0) board coordinate
     */
    public BonusPanel() {
        super(0,0);
    }

    /**
     * Increase the player's star count by the D6 roll multiplied by the minimum between the
     * player's norma level and three.
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }
}
