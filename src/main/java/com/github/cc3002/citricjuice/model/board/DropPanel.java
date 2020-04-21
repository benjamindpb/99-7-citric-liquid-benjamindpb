package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Drop Panel
 */
public class DropPanel extends AbstractPanel{

    /**
     * Creates a new Drop Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public DropPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
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
