package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Bonus Panel
 */
public class BonusPanel extends AbstractPanel{

    /**
     * Constructor for a default panel without any special behaviour.
     *  @param id    represent a id of the panel in the board
     *
     */
    public BonusPanel(int id) {
        super(id);
    }

    /**
     * Increase the player's star count by the D6 roll multiplied by the minimum between the
     * player's norma level and three.
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.increaseStarsBy(player.roll() *
                Math.min(player.getNormaLevel(), 3));
    }
}
