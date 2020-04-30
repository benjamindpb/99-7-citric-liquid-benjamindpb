package com.github.cc3002.citricjuice.model.units;

import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

public interface IUnit{

    /**
     * Returns the character's name.
     */
    String getName();

    /**
     * Returns the character's max hit points.
     */
    int getMaxHP();

    /**
     * Returns the current character's attack points.
     */
    int getAtk();

    /**
     * Returns the current character's defense points.
     */
    int getDef();

    /**
     * Returns the current character's evasion points.
     */
    int getEvd();

    /**
     * Returns the current hit points of the character.
     */
    int getCurrentHP();

    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    void setCurrentHP(int newHP);

    /**
     * Reduces this player's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    void reduceStarsBy(int amount);

    /**
     * Increases this player's star count by an amount.
     */
    void increaseStarsBy(int amount);

    /**
     * Returns this player's star count.
     */
    int getStars();

    /**
     * Return this player's wins count
     *
     * @return the wins of the player
     */
    int getWins();

    void attack(IUnit unit);

    void receiveWildAttack(WildUnit wildUnit);

    void receiveBossAttack(BossUnit bossUnit);

    void receivePlayerAttack(Player player);
}
