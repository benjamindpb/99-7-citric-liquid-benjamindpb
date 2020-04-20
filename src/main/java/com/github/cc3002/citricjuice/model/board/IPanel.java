package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

public interface IPanel {
    /**
     * Returns a copy of this panel's next ones.
     */
    Set<IPanel> getNextPanels();

    /**
     * Returns a copy of the players in this panel.
     */
    Set<Player> getPlayers();

    /**
     * Adds a new adjacent panel to this one.
     *
     * @param panel
     *     the panel to be added.
     */
    void addNextPanel(final IPanel panel);
    /**
     * Executes the appropriate action to the player according to this panel's type.
     */
    public abstract void activatedBy(final Player player);

}
