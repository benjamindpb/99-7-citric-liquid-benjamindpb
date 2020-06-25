package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class AbstractPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;

  private Player kai;
  private Player chicken;
  private Player suguri;

  private BossUnit shifuRobot;
  private WildUnit seagull;

  private HomePanel testHomePanel;
  private NeutralPanel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private DrawPanel testDrawPanel;

  private int testSeed;

  @BeforeEach
  public void setUp() {
    int i = 0;
    testBonusPanel = new BonusPanel(i++);
    testBossPanel = new BossPanel(i++);
    testDrawPanel = new DrawPanel(i++);
    testDropPanel = new DropPanel(i++);
    testEncounterPanel = new EncounterPanel(i++);
    testHomePanel = new HomePanel(i++);
    testNeutralPanel = new NeutralPanel(i);

    testSeed = new Random().nextInt();

    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    kai = new Player("Kai", 5, 1, 0, 0);
    chicken = new Player("Chicken", 3, -1, -1, 1);

    seagull = new WildUnit("Seagull", 3, 1, -1, -1);
    shifuRobot = new BossUnit("Shifu Robot", 7, 2, 3, -2);

  }

  @Test
  public void constructorWhitParameterTest(){
    var expectedId = 0;
    assertEquals(expectedId++, testBonusPanel.getId());
    assertEquals(expectedId++, testBossPanel.getId());
    assertEquals(expectedId++, testDrawPanel.getId());
    assertEquals(expectedId++, testDropPanel.getId());
    assertEquals(expectedId++, testEncounterPanel.getId());
    assertEquals(expectedId++, testHomePanel.getId());
    assertEquals(expectedId, testNeutralPanel.getId());

  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel(1);
    final var expectedPanel2 = new NeutralPanel(2);

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
                 testNeutralPanel.getNextPanels());
  }

  @Test
  public void addPlayerTest(){
    assertTrue(testNeutralPanel.getPlayers().isEmpty());
    testNeutralPanel.addPlayerToPanel(kai);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    testNeutralPanel.addPlayerToPanel(chicken);
    assertEquals(2, testNeutralPanel.getPlayers().size());
  }


  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.increaseStarsBy(10);

    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    assertEquals(2, suguri.getNormaLevel());

    suguri.setCurrentHP(1);
    suguri.increaseWinsBy(1);

    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(2, suguri.getCurrentHP());
    assertEquals(3, suguri.getNormaLevel());

    suguri.increaseStarsBy(61);

    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(4, suguri.getNormaLevel());

    suguri.increaseWinsBy(12);

    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(5, suguri.getNormaLevel());

    suguri.increaseStarsBy(300);

    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(6, suguri.getNormaLevel());
  }
  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatePanelEffectBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void bonusPanelTest(){
    int stars = suguri.getStars();
    assertEquals(1, suguri.getNormaLevel());
    testBonusPanel.activatePanelEffectBy(suguri);
    assertNotEquals(stars, suguri.getStars());
  }

  @Test
  public void bossPanelTest(){
    final var boss = new BossPanel(1);
    assertEquals(new BossPanel(1), boss);
    assertEquals(null, boss.getBossUnit());
    boss.setBossUnit(shifuRobot);
    assertEquals(new BossUnit("Shifu Robot", 7,2, 3, -2), boss.getBossUnit());
    assertFalse(boss.getBossUnit() == null);
  }

  @Test
  public void encounterPanelTest(){
    final var wild = new EncounterPanel(1);
    assertEquals(new EncounterPanel(1), wild);
    assertEquals(null, wild.getWildUnit());
    wild.setWildUnit(seagull);
    assertEquals(new WildUnit("Seagull", 3, 1, -1, -1), wild.getWildUnit());
    assertFalse(wild.getWildUnit() == null);
  }


  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatePanelEffectBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatePanelEffectBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
}