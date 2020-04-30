package com.github.cc3002.citricjuice.model.units;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:benjamin.dpb@gmail.com"> Benjamín del Pino </a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends AbstractUnit {
  private final Random random;
  private int normaLevel;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    random = new Random();
  }

  /**
   * Set's the seed for this player's random number generator.
   * The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
   * Returns a uniformly distributed random value in [1, 6]
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
           getAtk() == player.getAtk() &&
           getDef() == player.getDef() &&
           getEvd() == player.getEvd() &&
           getNormaLevel() == player.getNormaLevel() &&
           getStars() == player.getStars() &&
           getCurrentHP() == player.getCurrentHP() &&
           getName().equals(player.getName());
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHP(), getAtk(), getDef(), getEvd());
  }

  /**
   * The player who activate the panel
   *
   * @param panel the panel who will be activated
   */
  public void activatePanel(@NotNull IPanel panel){
    panel.activatePanelEffectBy(this);
  }

  @Override
  public void attack(IUnit unit) {
    unit.receivePlayerAttack(this);
  }

  @Override
  public void receiveWildAttack(WildUnit wildUnit) {

  }

  @Override
  public void receiveBossAttack(BossUnit bossUnit) {

  }

  @Override
  public void receivePlayerAttack(Player player) {

  }
}