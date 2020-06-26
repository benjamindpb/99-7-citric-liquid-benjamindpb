package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

import java.util.List;
import java.util.Set;

/**
 * A representation of a Panel
 *
 * @author Benjamin del Pino Badilla
 *
 */
public interface IPanel {
    /**
     * Returns a copy of this panel's next ones.
     */
    Set<IPanel> getNextPanels();

    /**
     * Returns a copy of the players in this panel.
     * @return
     */
    List<Player> getPlayers();

    /**
     * Adds a new adjacent panel to this one.
     *
     * @param panel
     *     the panel to be added.
     */
    void addNextPanel(final IPanel panel);

    /**
     * Adds a new player to this panel
     *
     * @param player to be added
     */
    void addPlayerToPanel(final Player player);

    /**
     * Executes the appropriate action to the player according to the panel's type.
     */
    void activatePanelEffectBy(final Player player);

}
