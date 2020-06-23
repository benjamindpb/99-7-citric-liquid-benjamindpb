package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
    private Set<IPanel> panels;
    private List<Player> players;
    private int chapter;
    private int indexCurrentPlayer;

    public GameController() {
        panels = new HashSet<>();
        players = new ArrayList<>(4);
        indexCurrentPlayer = 0;
    }

    /**
     *
     * @param id coordinate in the board
     * @return the panel
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel bonus = new BonusPanel(id);
        panels.add(bonus);
        return bonus;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public BossPanel createBossPanel(int id) {
        BossPanel boss = new BossPanel(id);
        panels.add(boss);
        return boss;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public DropPanel createDropPanel(int id) {
        DropPanel drop = new DropPanel(id);
        panels.add(drop);
        return drop;

    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel encounter = new EncounterPanel(id);
        panels.add(encounter);
        return encounter;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public HomePanel createHomePanel(int id) {
        HomePanel home = new HomePanel(id);
        panels.add(home);
        return home;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public NeutralPanel createNeutralPanel(int id) {
        NeutralPanel neutral = new NeutralPanel(id);
        panels.add(neutral);
        return neutral;
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
        Player player = new Player(name, hitPoints, attack, defense, evasion);
        panel.addPlayerToPanel(player);
        players.add(player);
        return player;
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

    public void setNextPanel(IPanel panel, IPanel panel1) {
        if(!panel.equals(panel1)){
            panel.addNextPanel(panel1);
        }
    }


    public void movePlayer() {
        Player player = this.getTurnOwner();
        int moves = player.roll();

    }


    public IPanel getPlayerPanel(Player unit) {
        return new HomePanel(1);
    }

    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        getTurnOwner().setNormaGoal(goal);
    }

    public Player getTurnOwner() {
        return players.get(indexCurrentPlayer);
    }

    public void setPlayerHome(Player unit, HomePanel panel) {
    }

    public void endTurn() {
    }

    public Set<IPanel> getPanels() {
        return panels;
    }

    public int getChapter() {
        return chapter;
    }
}
