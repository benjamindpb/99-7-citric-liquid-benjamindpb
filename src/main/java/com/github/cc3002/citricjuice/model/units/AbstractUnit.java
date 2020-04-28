package com.github.cc3002.citricjuice.model.units;

/**
 *
 * Abstract Class that represents the Units of the game
 *
 */
public class AbstractUnit implements IUnit{
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int evade;
    private int stars;
    private int wins;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getEvade() {
        return evade;
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
