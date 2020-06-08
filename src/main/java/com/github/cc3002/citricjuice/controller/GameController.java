package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.boss.BossUnit;
import com.github.cc3002.citricjuice.model.unit.wild.WildUnit;


/**
 *This class
 */
public class GameController {
    /**
     *
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public BonusPanel createBonusPanel(int x, int y) {
        return new BonusPanel(x, y);
    }

    /**
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public BossPanel createBossPanel(int x, int y) {
        return new BossPanel(x, y);
    }

    /**
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public DropPanel createDropPanel(int x, int y) {
        return new DropPanel(x, y);
    }

    /**
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public EncounterPanel createEncounterPanel(int x, int y) {
        return new EncounterPanel(x, y);
    }

    /**
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public HomePanel createHomePanel(int x, int y) {
        return new HomePanel(x, y);
    }

    /**
     * @param x coordinate in the board
     * @param y coordinate in the board
     * @return the panel
     */
    public NeutralPanel createNeutralPanel(int x, int y) {
        return new NeutralPanel(x, y);
    }

    /**
     * @param name of the player
     * @param hitPoints of the player
     * @param attack of the player
     * @param defense of the player
     * @param evasion of the panel
     * @param panel that the player is located
     * @return
     */
    public Player createPlayer(String name, int hitPoints, int attack, int defense,
                               int evasion, IPanel panel) {
        //todo: hacer que los player se ubiquen en un panel
        return new Player(name, hitPoints, attack, defense, evasion);
    }

    /**
     * @param name of the unit
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return
     */
    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        return new WildUnit(name, hitPoints, attack, defense, evasion);
    }

    /**
     * @param name of the unit
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return
     */
    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        return new BossUnit(name, hitPoints, attack, defense, evasion);
    }

}
