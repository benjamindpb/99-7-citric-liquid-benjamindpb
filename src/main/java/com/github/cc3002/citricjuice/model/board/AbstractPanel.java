package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

import java.util.*;

/**
 * Abstract class that represents a generic panel in the board of the game.
 * This class contains the necessary methods to get the properties of each panel.
 *
 * @author <a href="mailto:benjamin.dpb@gmail.com"> Benjamín del Pino </a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel {

  private Set<IPanel> nextPanels = new HashSet<>();
  private List<Player> playersInPanel = new ArrayList<>();

  private final int id;

  /**
   * Constructor for a default panel without any special behaviour.
   *  @param id represent a row of the panel in the board
   *
   */
  public AbstractPanel(int id) {
    this.id = id;
  }

  @Override
  public Set<IPanel> getNextPanels() {
    return nextPanels;
  }

  @Override
  public List<Player> getPlayers() {
    return playersInPanel;
  }


  @Override
  public void addNextPanel(final IPanel panel) {
    nextPanels.add(panel);
  }

  public void addPlayerToPanel(Player player){
    playersInPanel.add(player);
  }

  /**
   * @return the id of the current panel
   */
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPanel)) return false;
    AbstractPanel that = (AbstractPanel) o;
    return id == that.id &&
            Objects.equals(nextPanels, that.nextPanels) &&
            Objects.equals(playersInPanel, that.playersInPanel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNextPanels(), playersInPanel, getId());
  }
}
