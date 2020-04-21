package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Neutral Panel
 */
public class NeutralPanel extends AbstractPanel{


    /**
     * Creates a new Neutral Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public NeutralPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
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
