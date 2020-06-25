package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.controller.handlers.ChangeNormaHandler;
import com.github.cc3002.citricjuice.controller.handlers.IHandler;
import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.*;

public class GameController {
    private Set<IPanel> panels;
    private List<Player> players;
    private List<WildUnit> wildUnits;
    private List<BossUnit> bossUnits;
    private int chapter;
    private int indexCurrentPlayer;
    private Random random;

    private IHandler changeNormaHandler = new ChangeNormaHandler(this);

    public GameController() {
        panels = new HashSet<>();
        players = new ArrayList<>(4);
        indexCurrentPlayer = 0;
        chapter = 1;
        wildUnits = new ArrayList<>();
        bossUnits = new ArrayList<>();
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
    public DropPanel createDropPanel(int id) {
        DropPanel drop = new DropPanel(id);
        panels.add(drop);
        return drop;

    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public BossPanel createBossPanel(int id) {
        BossPanel boss = new BossPanel(id);
        //boss.setBossUnit(bossUnits.get(random.nextInt(3)));
        panels.add(boss);
        return boss;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel encounter = new EncounterPanel(id);
        //encounter.setWildUnit(wildUnits.get(random.nextInt(3)));
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
     * @return a new Player
     */
    public Player createPlayer(String name, int hitPoints, int attack, int defense,
                               int evasion, IPanel panel) {
        Player player = new Player(name, hitPoints, attack, defense, evasion);
        panel.addPlayerToPanel(player);
        player.setPanel(panel);
        players.add(player);
        return player;
    }

    /**
     * @param name of the unit
     * @param hitPoints of the unit
     * @param attack of the unit
     * @param defense of the unit
     * @param evasion of the unit
     * @return a new Wild Unit
     */
    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        WildUnit unit = new WildUnit(name, hitPoints, attack, defense, evasion);
        //wildUnits.add(unit);
        return unit;
    }

    /**
     * @param name of the unit
     * @param hitPoints of the unit
     * @param attack of the unit
     * @param defense of the unit
     * @param evasion of the unit
     * @return a new Boss Unit
     */
    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        BossUnit unit = new BossUnit(name, hitPoints, attack, defense, evasion);
        //bossUnits.add(unit);
        return unit;
    }

    public void setNextPanel(IPanel panel, IPanel panel1) {
        if(!panel.equals(panel1)){
            panel.addNextPanel(panel1);
        }
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void movePlayer() {
        int moves = getTurnOwner().roll();
        Player turnOwner = getTurnOwner();
        do {
            if(turnOwner.getPanel().equals(turnOwner.getHomePanel())){
                break;
            }
            if(turnOwner.getPanel().getPlayers().size() > 1){
                break;
            }
            if(turnOwner.getPanel().getNextPanels().size() > 1){
                // el panel del player actual tiene mas de un panel siguiente
            }
            else{ // el panel del player actual tiene solo un panel siguiente o 0 (?)
                IPanel nextPanel = turnOwner.getPanel().getNextPanels().iterator().next();
                turnOwner.getPanel().getPlayers().remove(turnOwner);
                nextPanel.getPlayers().add(turnOwner);
                turnOwner.setPanel(nextPanel);
            }
        }while(moves-- > 0);
        turnOwner.getPanel().activatePanelEffectBy(turnOwner);

    }


    public IPanel getPlayerPanel(Player player) {
        return player.getPanel();
    }

    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        getTurnOwner().changeNormaListener(changeNormaHandler);
        getTurnOwner().setNormaGoal(goal);
    }

    public Player getTurnOwner() {
        return players.get(indexCurrentPlayer%4);
    }

    public void setPlayerHome(Player unit, HomePanel panel) {
        unit.setHomePanel(panel);
    }

    public void endTurn() {
        if(indexCurrentPlayer == 3){
            indexCurrentPlayer = 0;
            chapter++;
        }
        else{
            indexCurrentPlayer++;
        }
    }

    public Set<IPanel> getPanels() {
        return panels;
    }

    public int getChapter() {
        return chapter;
    }
}
