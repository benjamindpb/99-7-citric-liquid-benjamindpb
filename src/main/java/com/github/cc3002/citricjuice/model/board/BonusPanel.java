package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Bonus Panel
 */
public class BonusPanel extends AbstractPanel{

    /**
     * Creates a new Bonus Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public BonusPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
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
