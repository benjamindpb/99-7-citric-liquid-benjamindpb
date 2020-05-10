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
        int dmg = this.setDmg();
        unit.receiveWildAttack(this, dmg);
    }

    @Override
    public void receiveWildAttack(WildUnit wildUnit, int dmg) {
        if(this.defend){
            this.defend(dmg);
            this.defend = false;
        }
        else if (this.evade){
            this.evade(dmg);
            this.evade = false;
        }
        if(this.isOutOfCombat()){
            wildUnit.increaseWinsBy(1);
            int stars = (int) (this.getStars() * 0.5);
            this.reduceStarsBy(stars);
            wildUnit.increaseStarsBy(stars);
        }
    }

    @Override
    public void receiveBossAttack(BossUnit bossUnit, int dmg) {
        if(this.defend){
            this.defend(dmg);
            this.defend = false;
        }
        else if (this.evade){
            this.evade(dmg);
            this.evade = false;
        }
        if(this.isOutOfCombat()){
            bossUnit.increaseWinsBy(1);
            int stars = (int) (this.getStars() * 0.5);
            this.reduceStarsBy(stars);
            bossUnit.increaseStarsBy(stars);
        }
    }

    @Override
    public void receivePlayerAttack(Player player, int dmg) {
        if(this.isDefend()){
            this.defend(dmg);
        }
        else if (this.isEvade()){
            this.evade(dmg);

        }
        if(this.isOutOfCombat()){
            player.increaseWinsBy(1);
            int stars = this.getStars();
            this.reduceStarsBy(stars);
            player.increaseStarsBy(stars);
        }
    }

}
