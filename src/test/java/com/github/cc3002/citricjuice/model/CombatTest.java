package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CombatTest {
    private Player suguri;
    private Player kai;
    private WildUnit seagull;
    private WildUnit chicken;
    private BossUnit shifu;
    private BossUnit storeManager;
    private long seed1;
    private long seed2;
    private Random r1;
    private Random r2;

    @BeforeEach
    public void setUp(){
        suguri = new Player("Suguri", 4,1, -1, 2);
        kai = new Player("Kai", 5, 1 ,0, 0);
        seagull = new WildUnit("Seagull", 3, 1, -1, -1); //("Seagull", 3, 1, -1, -1)
        chicken = new WildUnit("Chicken", 3, -1, -1, 1);
        shifu = new BossUnit("Shifu Robot", 7, 2, 3, -2); //("Shifu Robot", 7, 2, 3, -2)
        storeManager = new BossUnit("Store Manager", 8, 3, 2, -1);

        seed1 = new Random().nextLong();
        seed2 = new Random().nextLong();
        r1 = new Random();
        r2 = new Random();
    }

    @Test
    public void verifyDefenseEvadeValuesTest(){
        assertFalse(suguri.isDefend());
        suguri.chooseDefend();
        assertTrue(suguri.isDefend());
        assertFalse(suguri.isEvade());
        suguri.chooseEvade();
        assertTrue(suguri.isEvade());
    }

    @Test
    public void wildUnitAttackTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        chicken.setSeed(seed1);
        storeManager.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + chicken.getAtk();
        int def = (r2.nextInt(6) + 1) + storeManager.getDef();
        int expectedHP = Math.max(0, storeManager.getCurrentHP() - Math.max(1, dmg - def));
        storeManager.chooseDefend();
        chicken.attack(storeManager);
        assertEquals(expectedHP, storeManager.getCurrentHP());
    }

    @Test
    public void wildUnitAttackTest2(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        chicken.setSeed(seed1);
        suguri.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + chicken.getAtk();
        int def = (r2.nextInt(6) + 1) + suguri.getDef();
        int expectedHP = Math.max(0, suguri.getCurrentHP() - Math.max(1, dmg - def));
        suguri.chooseDefend();
        chicken.attack(suguri);
        assertEquals(expectedHP, suguri.getCurrentHP());
    }

    @Test
    public void bossUnitAttackTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        storeManager.setSeed(seed1);
        suguri.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + storeManager.getAtk();
        int def = (r2.nextInt(6) + 1) + suguri.getDef();
        int expectedHP = Math.max(0, suguri.getCurrentHP() - Math.max(1, dmg - def));
        suguri.chooseDefend();
        storeManager.attack(suguri);
        assertEquals(expectedHP, suguri.getCurrentHP());
    }

    @Test
    public void playerUnitAttackTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        storeManager.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def = (r2.nextInt(6) + 1) + storeManager.getDef();
        int expectedHP = Math.max(0, storeManager.getCurrentHP() - Math.max(1, dmg - def));
        storeManager.chooseDefend();
        suguri.attack(storeManager);
        assertEquals(expectedHP, storeManager.getCurrentHP());
    }

    @RepeatedTest(10)
    public void simpleBattleWithDefUnitChooseTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        chicken.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def = (r2.nextInt(6) +1) + chicken.getDef();
        int expectedHP = Math.max(0,chicken.getCurrentHP() - Math.max(1, dmg - def));
        int d = suguri.setDmg();
        chicken.defend(d);
        assertEquals(expectedHP, chicken.getCurrentHP());
    }
    @RepeatedTest(10)
    public void simpleBattleWithEvadeUnitChooseTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        storeManager.setSeed(seed2);
        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int evd = (r2.nextInt(6) +1) + storeManager.getEvd();
        int d = suguri.setDmg();
        int expectedHP = Math.max(0, storeManager.getCurrentHP() - (evd <= dmg ? dmg : 0));
        storeManager.evade(d);
        assertEquals(expectedHP, storeManager.getCurrentHP());
    }

    @RepeatedTest(10)
    public void suguriVersusKaiTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        kai.setSeed(seed2);
        int dmg_sug = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def_kai = (r2.nextInt(6) + 1) + kai.getDef();
        int expectedKaiHP = Math.max(0,kai.getCurrentHP() - Math.max(1, dmg_sug - def_kai));
        int dmg = suguri.setDmg();
        kai.chooseDefend();
        kai.receivePlayerAttack(suguri, dmg);
        assertEquals(expectedKaiHP, kai.getCurrentHP());
        if(kai.isOutOfCombat()){
            assertEquals(0, kai.getCurrentHP());
        }
        else{ // contrattack
            int dmg_kai = (r2.nextInt(6) + 1) + kai.getAtk();
            int evd_sug = (r1.nextInt(6) + 1) + suguri.getEvd();
            int expectedSuguriHP = Math.max(0, suguri.getCurrentHP() - (evd_sug <= dmg_kai ? dmg_kai : 0));
            int dmg2 = kai.setDmg();
            suguri.chooseEvade();
            suguri.receivePlayerAttack(kai, dmg2);
            assertEquals(expectedSuguriHP, suguri.getCurrentHP());
            if(suguri.isOutOfCombat()) {
                assertEquals(0, suguri.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void suguriVersusSeagullTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        seagull.setSeed(seed2);
        int dmg_ply = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def_opp = (r2.nextInt(6) + 1) + seagull.getDef();
        int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - Math.max(1, dmg_ply - def_opp));
        int dmg = suguri.setDmg();
        seagull.chooseDefend();
        seagull.receivePlayerAttack(suguri, dmg);
        assertEquals(expectedSeagullHP, seagull.getCurrentHP());
        if(seagull.isOutOfCombat()){
            assertEquals(0, seagull.getCurrentHP());
        }
        else{ // contrattack
            int dmg_opp = (r2.nextInt(6) + 1) + seagull.getAtk();
            int evd_ply = (r1.nextInt(6) + 1) + suguri.getEvd();
            int expectedSuguriHP = Math.max(0, suguri.getCurrentHP() - (evd_ply <= dmg_opp ? dmg_opp : 0));
            int dmg2 = seagull.setDmg();
            suguri.chooseEvade();
            suguri.receiveWildAttack(seagull, dmg2);
            assertEquals(expectedSuguriHP, suguri.getCurrentHP());
            if(suguri.isOutOfCombat()){
                assertEquals(0, suguri.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void suguriVersusSeagullTest2(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        seagull.setSeed(seed2);
        int dmg_ply = (r1.nextInt(6) + 1) + suguri.getAtk();
        int evd_sea = (r2.nextInt(6) + 1) + seagull.getEvd();
        int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - (evd_sea <= dmg_ply ? dmg_ply : 0));
        int dmg = suguri.setDmg();
        seagull.chooseEvade();
        seagull.receivePlayerAttack(suguri, dmg);
        assertEquals(expectedSeagullHP, seagull.getCurrentHP());
        if(seagull.isOutOfCombat()){
            assertEquals(0, seagull.getCurrentHP());
        }
        else{ // contrattack
            int dmg_opp = (r2.nextInt(6) + 1) + seagull.getAtk();
            int evd_ply = (r1.nextInt(6) + 1) + suguri.getEvd();
            int expectedSuguriHP = Math.max(0, suguri.getCurrentHP() - (evd_ply <= dmg_opp ? dmg_opp : 0));
            int dmg2 = seagull.setDmg();
            suguri.chooseEvade();
            suguri.receiveWildAttack(seagull, dmg2);
            assertEquals(expectedSuguriHP, suguri.getCurrentHP());
            if(suguri.isOutOfCombat()){
                assertEquals(0, suguri.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void suguriVersusShifuTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        shifu.setSeed(seed2);
        int dmg_ply = (r1.nextInt(6) + 1) + suguri.getAtk();
        int evd_opp = (r2.nextInt(6) + 1) + shifu.getEvd();
        int expectedShifuHP = Math.max(0, shifu.getCurrentHP() - (evd_opp <= dmg_ply ? dmg_ply : 0));
        int dmg = suguri.setDmg();
        shifu.chooseEvade();
        shifu.receivePlayerAttack(suguri, dmg);
        assertEquals(expectedShifuHP, shifu.getCurrentHP());
        if(shifu.isOutOfCombat()){
            assertEquals(0, shifu.getCurrentHP());
        }
        else{ // contrattack
            int dmg_opp = (r2.nextInt(6) + 1) + shifu.getAtk();
            int evd_ply = (r1.nextInt(6) + 1) + suguri.getEvd();
            int expectedSuguriHP = Math.max(0, suguri.getCurrentHP() - (evd_ply <= dmg_opp ? dmg_opp : 0));
            int dmg2 = shifu.setDmg();
            suguri.chooseEvade();
            suguri.receiveBossAttack(shifu, dmg2);
            assertEquals(expectedSuguriHP, suguri.getCurrentHP());
            if(suguri.isOutOfCombat()){
                assertEquals(0, suguri.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void seagullVersusShifuTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        seagull.setSeed(seed1);
        shifu.setSeed(seed2);
        int dmg_sea = (r1.nextInt(6) + 1) + seagull.getAtk();
        int evd_shi = (r2.nextInt(6) + 1) + shifu.getEvd();
        int expectedShifuHP = Math.max(0, shifu.getCurrentHP() - (evd_shi <= dmg_sea ? dmg_sea : 0));
        int dmg = seagull.setDmg();
        shifu.chooseEvade();
        shifu.receiveWildAttack(seagull, dmg);
        assertEquals(expectedShifuHP, shifu.getCurrentHP());
        if(shifu.isOutOfCombat()){
            assertEquals(0, shifu.getCurrentHP());
        }
        else{ // contrattack
            int dmg_shi = (r2.nextInt(6) + 1) + shifu.getAtk();
            int evd_sea = (r1.nextInt(6) + 1) + seagull.getEvd();
            int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - (evd_sea <= dmg_shi ? dmg_shi :
                    0));
            int dmg2 = shifu.setDmg();
            seagull.chooseEvade();
            seagull.receiveBossAttack(shifu, dmg2);
            assertEquals(expectedSeagullHP, seagull.getCurrentHP());
            if(seagull.isOutOfCombat()){
                assertEquals(0, seagull.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void seagullVersusShifuTest2(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        seagull.setSeed(seed1);
        shifu.setSeed(seed2);
        int dmg_sea = (r1.nextInt(6) + 1) + seagull.getAtk();
        int evd_shi = (r2.nextInt(6) + 1) + shifu.getEvd();
        int expectedShifuHP = Math.max(0, shifu.getCurrentHP() - (evd_shi <= dmg_sea ? dmg_sea : 0));
        int dmg = seagull.setDmg();
        shifu.chooseEvade();
        shifu.receiveWildAttack(seagull, dmg);
        assertEquals(expectedShifuHP, shifu.getCurrentHP());
        if(shifu.isOutOfCombat()){
            assertEquals(0, shifu.getCurrentHP());
        }
        else{ // contrattack
            int dmg_shi = (r2.nextInt(6) + 1) + shifu.getAtk();
            int def_sea = (r1.nextInt(6) + 1) + seagull.getEvd();
            int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - Math.max(1, dmg_shi - def_sea));
            int dmg2 = shifu.setDmg();
            seagull.chooseDefend();
            seagull.receiveBossAttack(shifu, dmg2);
            assertEquals(expectedSeagullHP, seagull.getCurrentHP());
            if(seagull.isOutOfCombat()){
                assertEquals(0, seagull.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void seagullVersusChickenTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        seagull.setSeed(seed1);
        chicken.setSeed(seed2);
        int dmg_sea = (r1.nextInt(6) + 1) + seagull.getAtk();
        int evd_shi = (r2.nextInt(6) + 1) + chicken.getEvd();
        int expectedChickenHP = Math.max(0, chicken.getCurrentHP() - (evd_shi <= dmg_sea ? dmg_sea : 0));
        int dmg = seagull.setDmg();
        chicken.chooseEvade();
        chicken.receiveWildAttack(seagull, dmg);
        assertEquals(expectedChickenHP, chicken.getCurrentHP());
        if(chicken.isOutOfCombat()){
            assertEquals(0, chicken.getCurrentHP());
        }
        else{ // contrattack
            int dmg_shi = (r2.nextInt(6) + 1) + chicken.getAtk();
            int def_sea = (r1.nextInt(6) + 1) + seagull.getEvd();
            int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - Math.max(1, dmg_shi - def_sea));
            int dmg2 = chicken.setDmg();
            seagull.chooseDefend();
            seagull.receiveWildAttack(chicken, dmg2);
            assertEquals(expectedSeagullHP, seagull.getCurrentHP());
            if(seagull.isOutOfCombat()){
                assertEquals(0, seagull.getCurrentHP());
            }
        }
    }

    @RepeatedTest(10)
    public void shifuVersusStoreManagerTest(){
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        shifu.setSeed(seed1);
        storeManager.setSeed(seed2);

        int dmg_shi = (r1.nextInt(6) + 1) + shifu.getAtk();
        int evd_sto = (r2.nextInt(6) + 1) + storeManager.getEvd();
        int expectedStoreMHP = Math.max(0, storeManager.getCurrentHP() - (evd_sto <= dmg_shi ? dmg_shi : 0));

        int dmg = shifu.setDmg();
        storeManager.chooseEvade();
        storeManager.receiveBossAttack(shifu, dmg);
        assertEquals(expectedStoreMHP, storeManager.getCurrentHP());

        if(storeManager.isOutOfCombat()){
            assertEquals(0, storeManager.getCurrentHP());
        }
        else{ // contrattack
            int dmg_sto = (r2.nextInt(6) + 1) + storeManager.getAtk();
            int def_shi = (r1.nextInt(6) + 1) + shifu.getDef();
            int expectedShifuHP = Math.max(0,shifu.getCurrentHP() - Math.max(1, dmg_sto - def_shi));

            int dmg2 = storeManager.setDmg();
            shifu.chooseDefend();
            shifu.receiveBossAttack(storeManager, dmg2);
            assertEquals(expectedShifuHP, shifu.getCurrentHP());
            if(shifu.isOutOfCombat()){
                assertEquals(0, shifu.getCurrentHP());

            }
        }
    }

}
