package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.board.BossPanel;
import com.github.cc3002.citricjuice.model.board.EncounterPanel;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyControllerTest {
    private GameController controller;
    private BossPanel bossPanel;
    private EncounterPanel encounterPanel;
    private BossUnit bossUnit;
    private WildUnit wildUnit;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
        bossPanel = controller.createBossPanel(1);
        encounterPanel = controller.createEncounterPanel(2);
        bossUnit = controller.createBossUnit("Store Manager", 10, 3, 4, -2);
        wildUnit = controller.createWildUnit("Chicken", 3, 1, -1, 1);
    }

    @Test
    public void setBossUnitToBossPanelTest(){
        assertNull(bossPanel.getBossUnit());
        controller.setBossUnitToBossPanel(bossUnit, bossPanel);
        BossUnit expectedBossUnit = new BossUnit("Store Manager", 10, 3, 4, -2);
        assertEquals(expectedBossUnit, bossPanel.getBossUnit());
    }

    @Test
    public void setWildUnitToBossPanelTest(){
        assertNull(encounterPanel.getWildUnit());
        controller.setWildUnitToBossPanel(wildUnit, encounterPanel);
        WildUnit expectedWildUnit = new WildUnit("Chicken", 3, 1, -1, 1);
        assertEquals(expectedWildUnit, encounterPanel.getWildUnit());
    }
}
