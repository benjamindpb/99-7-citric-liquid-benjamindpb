package com.github.cc3002.citricjuice.model.units;

import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

/**
 * A representation of an Unit that can battle others
 *
 * @author Benjamin del Pino Badilla
 *
 */
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
     * @param amount of stars to reduce
     */
    void reduceStarsBy(int amount);

    /**
     * @param amount of stars to increase
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

    /**
     * Attacks another unit
     *
     * @param unit that receives the attack
     */
    void attack(IUnit unit);

    /**
     * Receives damage from a wild unit attack
     *
     * @param wildUnit that attacks
     * @param dmg to receive
     *
     */
    void receiveWildAttack(WildUnit wildUnit, int dmg);

    /**
     * Receives damage from a boss unit attack
     * @param bossUnit that attacks
     * @param dmg to receive
     *
     */
    void receiveBossAttack(BossUnit bossUnit, int dmg);

    /**
     * Receive damage from a player attack
     * @param player that attacks
     * @param dmg to receive
     *
     */
    void receivePlayerAttack(Player player, int dmg);

    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    int roll();

    /**
     * @return the amount of the total damage to do in a battle
     */
    int setDmg();

    /**
     * This method is used when an unit choose to defend an attack
     *
     * @param dmg received
     */
    void defend(int dmg);

    /**
     * This method is used when an unit choose to evade an attack
     *
     * @param dmg received
     */
    void evade(int dmg);

    /**
     * set true to defend field in unit
     */
    void chooseDefend();

    /**
     * set true to evade field in unit
     */
    void chooseEvade();

    /**
     * @return true if an unit choose defend, false in contrary case
     */
    boolean isDefend();

    /**
     * @return true if an unit choose defend, false in contrary case
     */
    boolean isEvade();

    /**
     * @param amount of wins to add
     */
    void increaseWinsBy(int amount);

    /**
     * @return true if an unit dies in a combat :(, false if don't
     */
    boolean isOutOfCombat();
}
