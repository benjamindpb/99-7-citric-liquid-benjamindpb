package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.boss.FlyingCastle;
import com.github.cc3002.citricjuice.model.units.boss.ShifuRobot;
import com.github.cc3002.citricjuice.model.units.boss.StoreManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * This class represent a Boss Panel
 *
 * @author Benjamin del Pino Badilla
 *
 */
public class BossPanel extends AbstractPanel{

    private BossUnit selectedBossUnit;
    private final ArrayList<BossUnit> bossUnits = new ArrayList<BossUnit>();
    private Random random;
    private long seed;

    /**
     * Creates a new Boss Panel
     *
     * @param row    represent a row of the panel in the board
     * @param column represent a column of the panel in the board
     */
    public BossPanel(int row, int column) {
        super(row, column);
        this.selectedBossUnit = null;
        random = new Random();

        this.bossUnits.add(new FlyingCastle());
        this.bossUnits.add(new ShifuRobot());
        this.bossUnits.add(new StoreManager());
    }
    /**
     * Thic Constructor creates a panel in the (0,0) board coordinate
     */
    public BossPanel() {
        super(0,0);
    }

    public BossUnit getSelectedBossUnit() {
        return selectedBossUnit;
    }

    /**
     * In this panel the player battles with a random Boss unit
     *
     * @param player who activate the panel
     */
    public void activatePanelEffectBy(Player player) {
        if(this.selectedBossUnit == null || selectedBossUnit.getCurrentHP() == 0){
            createBossUnit();
        }
        player.attack(selectedBossUnit);
        if(!selectedBossUnit.isOutOfCombat()){ //CA
            selectedBossUnit.attack(player);
        }
    }

    /**
     * This method creates a new instance of a Boss Unit
     */
    public void createBossUnit(){
        int index = random.nextInt(bossUnits.size());
        this.selectedBossUnit = bossUnits.get(index);
    }

    public ArrayList<BossUnit> getBossUnits() {
        return bossUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BossPanel bossPanel = (BossPanel) o;
        return Objects.equals(bossUnits, bossPanel.bossUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bossUnits);
    }
    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }
}
