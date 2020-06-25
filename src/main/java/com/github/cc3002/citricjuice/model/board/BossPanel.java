package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;

/**
 * This class represent a Boss Panel
 *
 * @author Benjamin del Pino Badilla
 *
 */
public class BossPanel extends AbstractPanel{

    private BossUnit bossUnit;
    /**
     * Creates a new Boss Panel
     *  @param id    represent a id of the panel in the board
     *
     */
    public BossPanel(int id) {
        super(id);
        this.bossUnit = null;
    }

    public BossUnit getBossUnit() {
        return bossUnit;
    }

    public void setBossUnit(BossUnit bossUnit) {
        this.bossUnit = bossUnit;
    }

    /**
     * In this panel the player battles with a random Boss unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        try{
            player.attack(bossUnit);
        }catch (Exception e){
            System.out.println("The panel has not a unit asigned yet.");
        }
    }
}
