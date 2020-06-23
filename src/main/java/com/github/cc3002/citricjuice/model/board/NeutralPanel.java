package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

/**
 * This class represent a Neutral Panel
 */
public class NeutralPanel extends AbstractPanel{


    /**
     * @param id    represent a id of the panel in the board
     *
     */
    public NeutralPanel(int id) {
        super(id);
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
