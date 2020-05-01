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
        unit.receiveWildAttack(this);
    }

    @Override
    public void receiveWildAttack(WildUnit wildUnit) {

    }

    @Override
    public void receiveBossAttack(BossUnit bossUnit) {

    }

    @Override
    public void receivePlayerAttack(Player player) {

    }
}
