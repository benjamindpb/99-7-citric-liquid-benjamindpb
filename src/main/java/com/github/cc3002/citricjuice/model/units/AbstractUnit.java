package com.github.cc3002.citricjuice.model.units;

import java.util.Random;

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
    protected final Random random;
    protected int stars;
    protected int currentHP;
    protected int wins;

    protected boolean defend, evade;

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
    public boolean chooseDefend(){
        return true;
    }
    @Override
    public boolean chooseEvade(){
        return true;
    }

    public void battle(IUnit unit){
        int roll_atk = this.roll();
        int dmg = roll_atk + this.getAtk();
        if(unit.chooseDefend()){
            int roll_def = unit.roll();
            int def = roll_def + unit.getDef();
            unit.setCurrentHP(Math.max(1, dmg - def));
        }
        else if (unit.chooseEvade()){
            int roll_evd = unit.roll();
            int evd = roll_evd + unit.getEvd();
            if (evd <= dmg){
                unit.setCurrentHP(unit.getCurrentHP() - dmg);
            }
        }

    }

    public void increaseWinsBy(final int amount){
        wins += amount;
    }
}
