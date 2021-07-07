package com.github.cc3002.citricjuice.model.units;

import java.util.Objects;
import java.util.Random;

/**
 *
 * Abstract Class that represents the Units of the game
 *
 */
public abstract class AbstractUnit implements IUnit{

    private final String name;
    private final int maxHP;
    private final int atk;
    private final int def;
    private final int evd;
    protected boolean defend;
    protected boolean evade;
    private Random random;
    private int stars;
    private int currentHP;
    private int wins;

    private long seed;

    public AbstractUnit(final String name, final int hp, final int atk, final int def,
                        final int evd) {
        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;

        defend = false;
        evade = false;
        wins = 0;
        stars = 0;
        random = new Random();
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

    @Override
    public int roll() {
      return random.nextInt(6) + 1;
    }

    @Override
    public int setDmg(){
        return this.roll() + this.getAtk();
    }

    @Override
    public void defend(int dmg){
        int def = this.roll() + this.getDef();
        this.setCurrentHP(this.getCurrentHP() - Math.max(1, dmg - def));
        this.defend = true;
    }
    @Override
    public void evade(int dmg){
        int evd = this.roll() + this.getEvd();
        if (evd <= dmg) {
            this.setCurrentHP(this.getCurrentHP() - dmg);
        }
        this.evade = false;
    }

    @Override
    public void chooseDefend(){
        defend = true;
        evade = false;
    }

    @Override
    public void chooseEvade(){
        defend = false;
        evade = true;
    }
    @Override
    public boolean isDefend() {
        return defend;
    }
    @Override
    public boolean isEvade() {
        return evade;
    }
    @Override
    public void increaseWinsBy(final int amount){
        wins += amount;
    }
    @Override
    public boolean isOutOfCombat(){
        return currentHP == 0;
    }

    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnit that = (AbstractUnit) o;
        return maxHP == that.maxHP &&
                atk == that.atk &&
                def == that.def &&
                evd == that.evd &&
                stars == that.stars &&
                currentHP == that.currentHP &&
                wins == that.wins &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxHP, atk, def, evd, stars, currentHP, wins);
    }
}
