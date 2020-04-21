package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

/**
 * This class represent a Home Panel
 */
public class HomePanel extends AbstractPanel{

    /**
     * Creates a new Home Panel
     *
     * @param nextPanels     Panel's next panels
     * @param playersInPanel Panel's players
     */
    public HomePanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
        super(nextPanels, playersInPanel);
    }

    /**
     * The player recovers one point of HP and realize a Norma check
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
        normaCheck(player);
    }

    /**
     * Make a normal check for a player
     *
     * @param player who will have a normal check
     *
     */
    private void normaCheck(Player player) {
        int norma = player.getNormaLevel();
        //...to do...
    }
}