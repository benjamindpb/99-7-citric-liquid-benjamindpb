package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.Chicken;
import com.github.cc3002.citricjuice.model.units.wild.RoboBall;
import com.github.cc3002.citricjuice.model.units.wild.Seagull;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

import java.util.ArrayList;
import java.util.Objects;
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
    private long seed;

    public WildUnit getSelectedWildUnit() {
        return selectedWildUnit;
    }

    public ArrayList<WildUnit> getWildUnits() {
        return wildUnits;
    }

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
     * ThiS Constructor creates a panel in the (0,0) board coordinate
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
        player.attack(selectedWildUnit);
        if(!selectedWildUnit.isOutOfCombat()){
            selectedWildUnit.attack(player);
        }
    }

    /**
     * This method creates a new instance of a Wild Unit
     */
    public void createWildUnit(){
        int index = random.nextInt(wildUnits.size());
        this.selectedWildUnit = wildUnits.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EncounterPanel that = (EncounterPanel) o;
        return Objects.equals(wildUnits, that.wildUnits);
    }


    /**
     * Set a seed
     *
     * @param seed to be setted
     */
    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }

}
