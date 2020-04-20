package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

public class DropPanel extends AbstractPanel{
    /**
     * Executes the appropriate action to the player according to this panel's type.
     *
     * @param player
     */
    @Override
    public void activatedBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
