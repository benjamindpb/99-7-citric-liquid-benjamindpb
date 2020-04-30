package com.github.cc3002.citricjuice.model.units;

/**
 *
 * Abstract Class that represents the Units of the game
 *
 */
public abstract class AbstractUnit implements IUnit{

    protected final String name;
    protected final int maxHP;
    protected final int atk;
    protected final int def;
    protected final int evd;
    protected int stars;
    protected int currentHP;
    protected int wins;

    public AbstractUnit(final String name, final int hp, final int atk, final int def,
                        final int evd) {
        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        wins = 0;
        stars = 0;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public int getMaxHP() {
      return maxHP;
    }

    @Override
    public int getAtk() {
      return atk;
    }

    @Override
    public int getDef() {
      return def;
    }

    @Override
    public int getEvd() {
      return evd;
    }

    @Override
    public int getCurrentHP() {
      return currentHP;
    }

    @Override
    public void setCurrentHP(final int newHP) {
      this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    @Override
    public void reduceStarsBy(final int amount) {
      stars = Math.max(0, stars - amount);
    }

    @Override
    public void increaseStarsBy(final int amount) {
      stars += amount;
    }

    @Override
    public int getStars() {
      return stars;
    }

    @Override
    public int getWins() {
      return wins;
    }

}
