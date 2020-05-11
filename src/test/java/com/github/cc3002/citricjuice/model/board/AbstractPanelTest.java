package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.boss.FlyingCastle;
import com.github.cc3002.citricjuice.model.units.boss.ShifuRobot;
import com.github.cc3002.citricjuice.model.units.boss.StoreManager;
import com.github.cc3002.citricjuice.model.units.wild.Chicken;
import com.github.cc3002.citricjuice.model.units.wild.RoboBall;
import com.github.cc3002.citricjuice.model.units.wild.Seagull;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

  private HomePanel testHomePanel;
  private NeutralPanel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private DrawPanel testDrawPanel;

  private ArrayList<BossUnit> bossUnits = new ArrayList<BossUnit>();
  private ArrayList<WildUnit> wildUnits = new ArrayList<>();


  private int testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDrawPanel = new DrawPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel();
    testNeutralPanel = new NeutralPanel();

    testSeed = new Random().nextInt();

    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    kai = new Player("Kai", 5, 1, 0, 0);
    chicken = new Player("Chicken", 3, -1, -1, 1);

    bossUnits.add(new FlyingCastle());
    bossUnits.add(new ShifuRobot());
    bossUnits.add(new StoreManager());

    wildUnits.add(new Chicken());
    wildUnits.add(new RoboBall());
    wildUnits.add(new Seagull());
  }

  @Test
  public void constructorTest() {
    assertEquals(new BonusPanel(), testBonusPanel);
    assertEquals(new BossPanel(), testBossPanel);
    assertEquals(new DrawPanel(), testDrawPanel);
    assertEquals(new DropPanel(), testDropPanel);
    assertEquals(new EncounterPanel(), testEncounterPanel);
    assertEquals(new HomePanel(), testHomePanel);
    assertEquals(new NeutralPanel(), testNeutralPanel);
  }

  @Test
  public void constructorWhitParameterTest(){
    final var bonus = new BonusPanel(1,2);
    final var boss = new BossPanel(4,6);
    final var draw = new DrawPanel(3,2);
    final var drop = new DropPanel(0,1);
    final var encounter = new EncounterPanel(5,2);
    final var home = new HomePanel(1,9);
    assertEquals(1, bonus.getRow());
    assertEquals(2, bonus.getColumn());
    assertEquals(4, boss.getRow());
    assertEquals(6, boss.getColumn());
    assertEquals(3, draw.getRow());
    assertEquals(2, draw.getColumn());
    assertEquals(0, drop.getRow());
    assertEquals(1, drop.getColumn());
    assertEquals(5, encounter.getRow());
    assertEquals(2, encounter.getColumn());
    assertEquals(1, home.getRow());
    assertEquals(9, home.getColumn());

  }

  @Test
  public void toStringTest(){
    final var bonus = new BonusPanel(1,2);
    String coordinate = bonus.toString();
    assertEquals("(1, 2)", coordinate);
    assertEquals("(0, 0)", testNeutralPanel.toString());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel(1, 2);
    final var expectedPanel2 = new NeutralPanel(2,3);

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
    assertTrue(testNeutralPanel.getPlayersInPanel().isEmpty());
    testNeutralPanel.addPlayerToPanel(kai);
    assertEquals(1, testNeutralPanel.getPlayersInPanel().size());
    testNeutralPanel.addPlayerToPanel(chicken);
    assertEquals(2, testNeutralPanel.getPlayersInPanel().size());
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
    final var boss = new BossPanel(1,2);
    assertEquals(new BossPanel(1, 2), boss);
    assertEquals(null, boss.getSelectedBossUnit());
    assertFalse(boss.getBossUnits() == null);
    assertEquals(new FlyingCastle(), boss.getBossUnits().get(0));

    long seed1 = new Random().nextLong();
    Random r1 = new Random();
    r1.setSeed(seed1);
    boss.setSeed(seed1);

    BossUnit bossExpected = bossUnits.get(r1.nextInt(bossUnits.size()));
    boss.activatePanelEffectBy(suguri);
    assertEquals(bossExpected, boss.getSelectedBossUnit());
    assertFalse(boss.getSelectedBossUnit() == null);
  }

  @Test
  public void encounterPanelTest(){
    final var wild = new EncounterPanel(1,2);
    assertEquals(new EncounterPanel(1, 2), wild);
    assertEquals(null, wild.getSelectedWildUnit());
    assertFalse(wild.getWildUnits() == null);
    assertEquals(new Chicken(), wild.getWildUnits().get(0));

    long seed1 = new Random().nextLong();
    Random r1 = new Random();
    r1.setSeed(seed1);
    wild.setSeed(seed1);

    WildUnit wildExpected = wildUnits.get(r1.nextInt(bossUnits.size()));
    wild.activatePanelEffectBy(suguri);
    assertEquals(wildExpected, wild.getSelectedWildUnit());
    assertFalse(wild.getSelectedWildUnit() == null);
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
  // endregion
}