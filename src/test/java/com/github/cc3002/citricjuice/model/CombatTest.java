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
    private Player getKai(){
        return new Player("Kai", 5, 1, 0, 0);
    }

    @RepeatedTest(50)
    public void simpleBattleWithDefUnitChooseTest(){
        WildUnit opponent = getChicken();
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        opponent.setSeed(seed2);

        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int def = (r2.nextInt(6) +1) + opponent.getDef();

        int expectedHP = Math.max(0,opponent.getCurrentHP() - Math.max(1, dmg - def));
        opponent.chooseDefend();
        suguri.beginBattle(opponent);
        assertEquals(expectedHP, opponent.getCurrentHP());
    }
    @RepeatedTest(50)
    public void simpleBattleWithEvadeUnitChooseTest(){
        BossUnit opponent = new StoreManager();
        r1.setSeed(seed1);
        r2.setSeed(seed2);
        suguri.setSeed(seed1);
        opponent.setSeed(seed2);

        int dmg = (r1.nextInt(6) + 1) + suguri.getAtk();
        int evd = (r2.nextInt(6) +1) + opponent.getEvd();

        int expectedHP = Math.max(0, opponent.getCurrentHP() - (evd <= dmg ? dmg : 0));
        opponent.chooseEvade();
        suguri.beginBattle(opponent);
        assertEquals(expectedHP, opponent.getCurrentHP());
    }
}
