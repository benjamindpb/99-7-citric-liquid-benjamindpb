package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.Random;

/**
 * This class represent a Encounter Panel
 *
 * @author Benjamin del Pino Badilla
 *
 */
public class EncounterPanel extends AbstractPanel {

    private WildUnit wildUnit;
    private Random random;

    /**
     * Creates a new Encounter Panel
     *  @param id    represent a id of the panel in the board
     *
     */
    public EncounterPanel(int id) {
        super(id);
        wildUnit = null;
        random = new Random();
    }

    public WildUnit getWildUnit() {
        return wildUnit;
    }

    public void setWildUnit(WildUnit wildUnit) {
        this.wildUnit = wildUnit;
    }

    /**
     * * In this panel the player battles with a random Wild unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        try{
            player.attack(wildUnit);
        }catch (Exception e){
            System.out.println("The panel has not a unit asigned yet.");
        }
    }


}
