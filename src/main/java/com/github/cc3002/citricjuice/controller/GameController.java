package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.boss.BossUnit;
import com.github.cc3002.citricjuice.model.unit.wild.WildUnit;

public class GameController {
    public BonusPanel createBonusPanel(int x, int y) {
        return new BonusPanel(x, y);
    }

    public BossPanel createBossPanel(int x, int y) {
        return new BossPanel(x, y);
    }

    public DropPanel createDropPanel(int x, int y) {
        return new DropPanel(x, y);
    }

    public EncounterPanel createEncounterPanel(int x, int y) {
        return new EncounterPanel(x, y);
    }

    public HomePanel createHomePanel(int x, int y) {
        return new HomePanel(x, y);
    }

    public NeutralPanel createNeutralPanel(int x, int y) {
        return new NeutralPanel(x, y);
    }

    public Player createPlayer(String name, int hitPoints, int attack, int defense,
                               int evasion, IPanel panel) {
        //todo: hacer que los player se ubiquen en un panel
        return new Player(name, hitPoints, attack, defense, evasion);
    }

    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        return new WildUnit(name, hitPoints, attack, defense, evasion);
    }

    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense,
                                   int evasion) {
        return new BossUnit(name, hitPoints, attack, defense, evasion);
    }

}
