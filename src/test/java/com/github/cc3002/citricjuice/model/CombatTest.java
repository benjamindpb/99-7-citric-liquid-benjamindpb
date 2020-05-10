package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.boss.ShifuRobot;
import com.github.cc3002.citricjuice.model.units.boss.StoreManager;
import com.github.cc3002.citricjuice.model.units.wild.Chicken;
import com.github.cc3002.citricjuice.model.units.wild.Seagull;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombatTest {

    private Player suguri;
    private Seagull seagull;
    private ShifuRobot shifu;

    private long seed1;
    private long seed2;
    private Random r1;
    private Random r2;


    @BeforeEach
    public void setUp(){
        suguri = new Player("Suguri", 4,1, -1, 2);
        seagull = new Seagull(); //("Seagull", 3, 1, -1, -1)
        shifu = new ShifuRobot(); //("Shifu Robot", 7, 2, 3, -2)
        seed1 = new Random().nextLong();
        seed2 = new Random().nextLong();
        r1 = new Random();
        r2 = new Random();
    }

    private BossUnit getStoreManager(){
        return new StoreManager();
    }
    private WildUnit getChicken(){
        return new Chicken(); //("Chicken", 3, -1, -1, 1);
    }

    @Test
    public void verifyDefenseEvadeValuesTest(){
        //Defense Case
        assertEquals(false, suguri.isDefend());
        suguri.chooseDefend();
        assertEquals(true, suguri.isDefend());
        //Evade Case
        assertEquals(false, suguri.isEvade());
        suguri.chooseEvade();
        assertEquals(true, suguri.isEvade());

    }

    @RepeatedTest(10)
    public void simpleBattleWithDefUnitChooseTest(){
        WildUnit opponent = getChicken();
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        opponent.setSeed(seed2);

        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def = (r2.nextInt(6) +1) + opponent.getDef();

        int expectedHP = Math.max(0,opponent.getCurrentHP() - Math.max(1, dmg - def));
        int d = suguri.setDmg();
        opponent.defend(d);
        assertEquals(expectedHP, opponent.getCurrentHP());
    }
    @RepeatedTest(10)
    public void simpleBattleWithEvadeUnitChooseTest(){
        BossUnit opponent = new StoreManager();
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        opponent.setSeed(seed2);

        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int evd = (r2.nextInt(6) +1) + opponent.getEvd();
        int d = suguri.setDmg();

        int expectedHP = Math.max(0, opponent.getCurrentHP() - (evd <= dmg ? dmg : 0));
        opponent.evade(d);
        assertEquals(expectedHP, opponent.getCurrentHP());
    }

    @RepeatedTest(10)
    public void playerVersusSeagullTest(){

        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        seagull.setSeed(seed2);

        int dmg_ply = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def_opp = (r2.nextInt(6) + 1) + seagull.getDef();
        int expectedSeagullHP = Math.max(0,seagull.getCurrentHP() - Math.max(1, dmg_ply - def_opp));
        int expectedSuguriWins = suguri.getWins();

        int dmg = suguri.setDmg();
        seagull.chooseDefend();
        seagull.receivePlayerAttack(suguri, dmg);
        assertEquals(expectedSeagullHP, seagull.getCurrentHP());

        if(seagull.isOutOfCombat()){
            assertEquals(0, seagull.getCurrentHP());
            assertEquals(expectedSuguriWins + 1, suguri.getWins());
        }
        else{ // contrattack
            int dmg_opp = (r2.nextInt(6) + 1) + seagull.getAtk();
            int evd_ply = (r1.nextInt(6) + 1) + suguri.getEvd();
            int expectedSuguriHP = Math.max(0, suguri.getCurrentHP() - (evd_ply <= dmg_opp ? dmg_opp : 0));
            int expectedSeagullWins = seagull.getWins();

            int dmg2 = seagull.setDmg();
            suguri.chooseEvade();
            suguri.receiveWildAttack(seagull, dmg2);
            assertEquals(expectedSuguriHP, suguri.getCurrentHP());
            if(suguri.isOutOfCombat()){
                assertEquals(0, suguri.getCurrentHP());
                assertEquals(expectedSeagullWins + 1, seagull.getWins());
            }
        }



    }

}
