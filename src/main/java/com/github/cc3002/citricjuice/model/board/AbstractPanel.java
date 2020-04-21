package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class that represents a generic panel in the board of the game.
 * This class contains the necessary methods to get the properties of each panel.
 *
 * @author <a href="mailto:benjamin.dpb@gmail.com"> Benjamín del Pino </a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel{
  private Set<IPanel> nextPanels = new HashSet<>();
  private Set<Player> playersInPanel = new HashSet<>();

  /**
   * Creates a new Panel
   *
   * @param nextPanels Panel's next panels
   * @param playersInPanel Panel's players
   */
  public AbstractPanel(Set<IPanel> nextPanels, Set<Player> playersInPanel) {
    this.nextPanels = nextPanels;
    this.playersInPanel = playersInPanel;
  }

  @Override
  public Set<IPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  @Override
  public Set<Player> getPlayers() {
    return Set.copyOf(playersInPanel);
  }


  @Override
  public void addNextPanel(final IPanel panel) {
    nextPanels.add(panel);
  }


}
