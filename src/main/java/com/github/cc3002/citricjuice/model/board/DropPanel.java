package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Drop Panel
 */
public class DropPanel extends AbstractPanel{

    /**
     * @param id    represent a id of the panel in the board
     *
     */
    public DropPanel(int id) {
        super(id);
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
