package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.Chicken;
import com.github.cc3002.citricjuice.model.units.wild.RoboBall;
import com.github.cc3002.citricjuice.model.units.wild.Seagull;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represent a Encounter Panel
 *
 * @author Benjamin del Pino Badilla
 *
 */
public class EncounterPanel extends AbstractPanel {

    private WildUnit selectedWildUnit;
    private final ArrayList<WildUnit> wildUnits = new ArrayList<WildUnit>();
    private Random random;

    /**
     * Creates a new Encounter Panel
     *
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public EncounterPanel(int row, int column) {
        super(row, column);
        this.selectedWildUnit = null;

        this.wildUnits.add(new Chicken());
        this.wildUnits.add(new RoboBall());
        this.wildUnits.add(new Seagull());
    }

    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public EncounterPanel() {
        super(0,0);
    }

    /**
     * * In this panel the player battles with a random Wild unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        if(this.selectedWildUnit == null || this.selectedWildUnit.getCurrentHP() == 0){
            createWildUnit();
        }
        player.attack(this.selectedWildUnit);
    }

    /**
     * This method creates a new instance of a Wild Unit
     */
    public void createWildUnit(){
        int index = random.nextInt(wildUnits.size());
        this.selectedWildUnit = wildUnits.get(index);
    }

}
