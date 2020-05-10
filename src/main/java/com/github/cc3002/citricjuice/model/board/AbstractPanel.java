package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

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

  private Set<IPanel> nextPanels = new HashSet<>();
  private Set<Player> playersInPanel = new HashSet<>();

  private int row;
  private int column;
  private String id;

  /**
   * Constructor for a default panel without any special behaviour.
   *
   * @param row represent a row of the panel in the board
   * @param column  represent a column of the panel in the board
   */
  public AbstractPanel(int row, int column) {
    this.row = row;
    this.column = column;
    id = "(" + row + ", " + column + ")";
  }

  @Override
  public Set<IPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  @Override
  public Set<Player> getPlayersInPanel() {
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
  public String toString() {
    return id;
  }

  /**
   * @return the row of the current panel
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column of the current panel
   */
  public int getColumn() {
    return column;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractPanel that = (AbstractPanel) o;
    return row == that.row &&
            column == that.column &&
            Objects.equals(nextPanels, that.nextPanels) &&
            Objects.equals(playersInPanel, that.playersInPanel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nextPanels, playersInPanel, row, column);
  }
}
