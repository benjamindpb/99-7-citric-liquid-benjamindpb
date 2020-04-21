package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  private AbstractPanel testHomePanel;
  private AbstractPanel testNeutralPanel;
  private AbstractPanel testBonusPanel;
  private AbstractPanel testDropPanel;
  private AbstractPanel testEncounterPanel;
  private AbstractPanel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new AbstractPanel(PanelType.BONUS);
    testBossPanel = new AbstractPanel(PanelType.BOSS);
    testDropPanel = new AbstractPanel(PanelType.DROP);
    testEncounterPanel = new AbstractPanel(PanelType.ENCOUNTER);
    testHomePanel = new AbstractPanel(PanelType.HOME);
    testNeutralPanel = new AbstractPanel(PanelType.NEUTRAL);
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    assertEquals(PanelType.BONUS, testBonusPanel.getType());
    assertEquals(PanelType.BOSS, testBossPanel.getType());
    assertEquals(PanelType.DROP, testDropPanel.getType());
    assertEquals(PanelType.ENCOUNTER, testEncounterPanel.getType());
    assertEquals(PanelType.HOME, testHomePanel.getType());
    assertEquals(PanelType.NEUTRAL, testNeutralPanel.getType());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new AbstractPanel(PanelType.NEUTRAL);
    final var expectedPanel2 = new AbstractPanel(PanelType.NEUTRAL);

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