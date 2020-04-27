package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract class that represents a generic panel in the board of the game.
 * This class contains the necessary methods to get the properties of each panel.
 *
 * @author <a href="mailto:benjamin.dpb@gmail.com"> Benjamín del Pino </a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel {
  protected Set<IPanel> nextPanels = new HashSet<>();
  protected Set<Player> playersInPanel = new HashSet<>();

  /**
   * This is the constructor of this abstract class.
   * Note that this will never uses because an abstract
   * class cannot be instantiated.
   */


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

  public void addPlayerToPanel(final Player player){
    playersInPanel.add(player);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractPanel that = (AbstractPanel) o;
    return Objects.equals(nextPanels, that.nextPanels) &&
            Objects.equals(playersInPanel, that.playersInPanel);
  }

}
