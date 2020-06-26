package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.controller.handlers.ChangeNormaHandler;
import com.github.cc3002.citricjuice.controller.handlers.IHandler;
import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.*;

/**
 * Esta clase representa al controlador. Este sirve como intermediario entre los objetos
 * del modelo y la interfaz gráfica de la aplicación.
 * El controlador debe encargarse de mantener todos los parámetros necesarios para
 * implementar las reglas y el flujo del juego.
 */
public class GameController {
    private HashMap<Integer, IPanel> panels;
    private List<Player> players;
    private int chapter;
    private int indexCurrentPlayer;

    private final IHandler changeNormaHandler = new ChangeNormaHandler(this);

    /**
     * metodo constructor
     */
    public GameController() {
        panels = new HashMap<>();
        players = new ArrayList<>(4);
        indexCurrentPlayer = 0;
        chapter = 1;
    }

    /**
     *
     * @param id coordinate in the board
     * @return the panel
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel bonus = new BonusPanel(id);
        panels.put(id, bonus);
        return bonus;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public DropPanel createDropPanel(int id) {
        DropPanel drop = new DropPanel(id);
        panels.put(id, drop);
        return drop;

    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public BossPanel createBossPanel(int id) {
        BossPanel boss = new BossPanel(id);
        panels.put(id, boss);
        return boss;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel encounter = new EncounterPanel(id);
        //encounter.setWildUnit(wildUnits.get(random.nextInt(3)));
        panels.put(id, encounter);
        return encounter;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public HomePanel createHomePanel(int id) {
        HomePanel home = new HomePanel(id);
        panels.put(id, home);
        return home;
    }

    /**
     * @param id coordinate in the board
     * @return the panel
     */
    public NeutralPanel createNeutralPanel(int id) {
        NeutralPanel neutral = new NeutralPanel(id);
        panels.put(id, neutral);
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
        return new WildUnit(name, hitPoints, attack, defense, evasion);
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
        return new BossUnit(name, hitPoints, attack, defense, evasion);
    }

    /**
     * @param panel al que se le va a setear un panel siguiente
     * @param panel1 agregado
     */
    public void setNextPanel(IPanel panel, IPanel panel1) {
        if(!panel.equals(panel1)){
            panel.addNextPanel(panel1);
        }
    }

    /**
     * Este metodo se encarga del movimiento del jugador por los paneles.
     * Se detiene cuando hay un jugador mas en el panel o cuando llega a
     * su home panel.
     */
    public void movePlayer() {
        int moves = getTurnOwner().roll();
        Player turnOwner = getTurnOwner();
        boolean earlyStop = false; // para detectar si se el jugador se detiene
        do {
            if(turnOwner.getPanel().equals(turnOwner.getHomePanel())){
                earlyStop = true;
                break;
            }
            if(turnOwner.getPanel().getPlayers().size() > 1){
                earlyStop = true;
                break;
            }
            if(turnOwner.getPanel().getNextPanels().size() > 1){
                // TODO: el panel del player actual tiene mas de un panel siguiente
            }
            else{ // el panel del player actual tiene solo un panel siguiente o 0 (?)
                IPanel nextPanel = turnOwner.getPanel().getNextPanels().iterator().next();
                turnOwner.getPanel().getPlayers().remove(turnOwner);
                nextPanel.getPlayers().add(turnOwner);
                turnOwner.setPanel(nextPanel);
            }
        }while(moves-- > 0);
        if(!earlyStop){
            turnOwner.getPanel().activatePanelEffectBy(turnOwner);
        }
    }

    /**
     * @param player que se estudia
     * @return  el panel en que esta el player
     */
    public IPanel getPlayerPanel(Player player) {
        return player.getPanel();
    }

    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        getTurnOwner().changeNormaListener(changeNormaHandler);
        getTurnOwner().setNormaGoal(goal);
    }

    /**
     * @return el propietario del turno
     */
    public Player getTurnOwner() {
        return players.get(indexCurrentPlayer%4);
    }

    /**
     * @param unit a la que se le seteara el home panel
     * @param panel home panel unico del player
     */
    public void setPlayerHome(Player unit, HomePanel panel) {
        unit.setHomePanel(panel);
    }

    /**
     * metodo que se encarga de finalizar el turno y de paso aumentar el chapter
     * si ya se cumplio una ronda
     */
    public void endTurn() {
        if(indexCurrentPlayer == 3){
            indexCurrentPlayer = 0;
            chapter++;
        }
        else{
            indexCurrentPlayer++;
        }
    }

    /**
     * @return el tablero, es una coleccion de todos los paneles
     */
    public Collection<IPanel> getPanels() {
        return panels.values();
    }

    /**
     * @return el capitulo actual del juego
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * @param bossUnit que sera agregada al panel
     * @param bossPanel al que se le agrega la unidad
     */
    public void setBossUnitToBossPanel(BossUnit bossUnit, BossPanel bossPanel) {
        bossPanel.setBossUnit(bossUnit);
    }
    /**
     * @param wildUnit que sera agregada al panel
     * @param encounterPanel al que se le agrega la unidad
     */
    public void setWildUnitToBossPanel(WildUnit wildUnit, EncounterPanel encounterPanel) {
        encounterPanel.setWildUnit(wildUnit);
    }

    //TODO:  agregar NormaCheck y NormaClear, observer (?)
}
