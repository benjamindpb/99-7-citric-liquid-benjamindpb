package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel{
  private Set<IPanel> nextPanels = new HashSet<>();
  private Set<Player> playersInPanel = new HashSet<>();

/*  *//**
   * Restores a player's HP in 1.
   *//*
  private static void applyHealTo(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
  }

  *//**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   *//*
  private static void applyDropTo(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }

  *//**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   *//*
  private static void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }*/

  public Set<IPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  public Set<Player> getPlayers() {
    return Set.copyOf(playersInPanel);
  }


  public void addNextPanel(final IPanel panel) {
    nextPanels.add(panel);
  }


  //{
/*    switch (type) {
      case BONUS:
        applyBonusTo(player);
        break;
      case DROP:
        applyDropTo(player);
        break;
      case HOME:
        applyHealTo(player);
        break;
      case NEUTRAL:
        break;
    }*/
  //}
}
