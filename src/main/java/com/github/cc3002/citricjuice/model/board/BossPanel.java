package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Boss Panel
 */
public class BossPanel extends AbstractPanel{

    /**
     * Creates a new Boss Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public BossPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
    }

    /**
     * In this panel the player battles with a random Boss unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }
}
