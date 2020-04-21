package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Draw Panel
 */
public class DrawPanel extends AbstractPanel {

    /**
     * Creates a new Draw Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public DrawPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
    }

    /**
     * The player draw a card and add the card to his hand
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }
}
