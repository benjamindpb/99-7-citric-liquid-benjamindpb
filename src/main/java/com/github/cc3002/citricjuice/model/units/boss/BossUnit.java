package com.github.cc3002.citricjuice.model.units.boss;

import com.github.cc3002.citricjuice.model.units.AbstractUnit;
import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;

public class BossUnit extends AbstractUnit {

    public BossUnit(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveBossAttack(this);
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
