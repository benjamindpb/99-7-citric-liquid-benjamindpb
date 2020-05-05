package com.github.cc3002.citricjuice.model.units.wild;

import com.github.cc3002.citricjuice.model.units.AbstractUnit;
import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;

/**
 * Representation of a Wild Unit
 */
public class WildUnit extends AbstractUnit {
    /**
     * Creates a new Wild Unit.
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
    public WildUnit(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveWildAttack(this, false);
    }

    @Override
    public void receiveWildAttack(WildUnit wildUnit, boolean counterAttack) {
        wildUnit.beginBattle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            wildUnit.receiveWildAttack(this, true);
        }
        else if (this.isOutOfCombat()){ // hp == 0
            wildUnit.increaseWinsBy(1);
            int stars = (int) Math.floor(this.getStars() * 0.5);
            wildUnit.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }

    @Override
    public void receiveBossAttack(BossUnit bossUnit, boolean counterAttack) {
        bossUnit.beginBattle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            bossUnit.receiveWildAttack(this, true);
        }
        else if (this.isOutOfCombat()){ // hp == 0
            bossUnit.increaseWinsBy(1);
            int stars = (int) Math.floor(this.getStars() * 0.5);
            bossUnit.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }

    @Override
    public void receivePlayerAttack(Player player, boolean counterAttack) {
        player.beginBattle(this);
        if(this.getCurrentHP() > 0 && !counterAttack){
            player.receiveWildAttack(this, true);
        }
        else if (this.isOutOfCombat()){ // hp == 0
            int stars = this.getStars();
            player.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
        }
    }

}
