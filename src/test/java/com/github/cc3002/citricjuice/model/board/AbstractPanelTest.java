package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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


  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDrawPanel = new DrawPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel();
    testNeutralPanel = new NeutralPanel();

    testSeed = new Random().nextLong();

    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    kai = new Player("Kai", 5, 1, 0, 0);
    chicken = new Player("Chicken", 3, -1, -1, 1);
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
    assertTrue(testNeutralPanel.getPlayers().isEmpty());
    testNeutralPanel.addPlayerToPanel(kai);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    testNeutralPanel.addPlayerToPanel(chicken);
    assertEquals(2, testNeutralPanel.getPlayers().size());
  }


  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());

    suguri.setCurrentHP(1);
    testHomePanel.activatePanelEffectBy(suguri);
    assertEquals(2, suguri.getCurrentHP());
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