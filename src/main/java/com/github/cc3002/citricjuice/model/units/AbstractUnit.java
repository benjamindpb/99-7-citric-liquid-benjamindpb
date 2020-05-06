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
    private Random random;
    private int stars;
    private int currentHP;
    private int wins;

    private boolean defend, evade;
    private long seed;

    public AbstractUnit(final String name, final int hp, final int atk, final int def,
                        final int evd) {
        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;

        wins = 0;
        stars = 0;
        random = new Random();
        defend = false;
        evade = false;
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
    public boolean getDefChoose(){
        return defend;
    }

    @Override
    public boolean getEvadeChoose(){
        return evade;
    }

    public void beginBattle(IUnit unit) {
        int dmg =  this.roll() + this.getAtk();
        if(unit.getDefChoose()){
            defend = false;
            int def = unit.roll() + unit.getDef();
            unit.setCurrentHP(unit.getCurrentHP() - Math.max(1, dmg - def));
        }
        else if (unit.getEvadeChoose()){
            evade = false;
            int evd = unit.roll() + unit.getEvd();
            if (evd <= dmg) {
                unit.setCurrentHP(unit.getCurrentHP() - dmg);
            }
        }

    }

    public void increaseWinsBy(final int amount){
        wins += amount;
    }

    public boolean isOutOfCombat(){
        return currentHP == 0;
    }


    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }

    public long getSeed(){
        return seed;
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
                defend == that.defend &&
                evade == that.evade &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxHP, atk, def, evd, stars, currentHP, wins, defend, evade);
    }
}
