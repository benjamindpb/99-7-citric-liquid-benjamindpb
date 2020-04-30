package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Draw Panel
 */
public class DrawPanel extends AbstractPanel {


    /**
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public DrawPanel(int row, int column) {
        super(row, column);
    }
    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public DrawPanel() {
        super(0,0);
    }

    /**
     * The player draw a card and add the card to his hand
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }
}
