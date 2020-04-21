package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Encounter Panel
 */
public class EncounterPanel extends AbstractPanel {

    /**
     * Creates a new Encounter Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public EncounterPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
    }

    /**
     * * In this panel the player battles with a random Wild unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {

    }
}
