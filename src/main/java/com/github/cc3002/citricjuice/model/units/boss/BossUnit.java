package com.github.cc3002.citricjuice.model.units.boss;

import com.github.cc3002.citricjuice.model.units.AbstractUnit;
import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

/**
 * Representation of a Boss Unit
 */
public class BossUnit extends AbstractUnit {

    /**
     * Creates a new Boss Unit.
     *
     * @param name
     *     the unit's name.
     * @param hp
     *     the initial (and max) hit points of the unit.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public BossUnit(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveBossAttack(this, false);
    }

    @Override
    public void receiveWildAttack(WildUnit wildUnit, boolean counterAttack) {
        wildUnit.battle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            wildUnit.receiveBossAttack(this, true);
        }
        else if (this.getCurrentHP() == 0){ // hp == 0
            wildUnit.increaseWinsBy(3);
            int stars = (int) Math.floor(this.getStars() * 0.5);
            wildUnit.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }

    @Override
    public void receiveBossAttack(BossUnit bossUnit, boolean counterAttack) {
        bossUnit.battle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            bossUnit.receiveBossAttack(this, true);
        }
        else if (this.getCurrentHP() == 0){ // hp == 0
            bossUnit.increaseWinsBy(3);
            int stars = (int) Math.floor(this.getStars());
            bossUnit.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }

    @Override
    public void receivePlayerAttack(Player player, boolean counterAttack) {
        player.battle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            player.receiveBossAttack(this, true);
        }
        else if (this.getCurrentHP() == 0){ // hp == 0
            player.increaseWinsBy(3);
            int stars = this.getStars();
            player.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }
}
