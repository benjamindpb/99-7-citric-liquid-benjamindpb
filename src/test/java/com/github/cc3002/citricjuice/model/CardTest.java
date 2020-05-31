package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.card.battle.BattleCard;
import com.github.cc3002.citricjuice.model.card.battle.BigMagnumCard;
import com.github.cc3002.citricjuice.model.card.battle.IAmOnFireCard;
import com.github.cc3002.citricjuice.model.card.battle.SinkOrSwimCard;
import com.github.cc3002.citricjuice.model.card.boost.*;
import com.github.cc3002.citricjuice.model.card.trap.InvasionCard;
import com.github.cc3002.citricjuice.model.card.trap.PiyoProcessCard;
import com.github.cc3002.citricjuice.model.card.trap.SkyRestaurantCard;
import com.github.cc3002.citricjuice.model.card.trap.TrapCard;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    BattleCard bigMagnum;
    BattleCard iAmOnFire;
    BattleCard sinkSwim;

    BoostCard dash;
    BoostCard nicePresent;
    BoostCard  pudding;
    BoostCard sakiscookie;

    TrapCard invasion;
    TrapCard piyoProcess;
    TrapCard skyRestaurant;


    @BeforeEach
    public void setUp(){
        bigMagnum = new BigMagnumCard();
        iAmOnFire = new IAmOnFireCard();
        sinkSwim = new SinkOrSwimCard();

        dash = new DashCard();
        nicePresent = new NicePresentCard();
        pudding = new PuddingCard();
        sakiscookie = new SakisCookieCard();

        invasion = new InvasionCard();
        piyoProcess = new PiyoProcessCard();
        skyRestaurant = new SkyRestaurantCard();

        Player kai = new Player("Kai", 5, 1, 0, 0);

    }

    @Test
    public void gettersTest(){
        assertEquals("Big Magnum", bigMagnum.getName());
        assertEquals("Pudding", pudding.getName());
        assertEquals("Invasion", invasion.getName());

        assertEquals(3, bigMagnum.getNorma());
        assertEquals(4, pudding.getNorma());

        assertEquals(0, piyoProcess.getPrice());
        assertEquals(10, nicePresent.getPrice());

    }
}
