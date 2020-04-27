package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Drop Panel
 */
public class DropPanel extends AbstractPanel{

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
