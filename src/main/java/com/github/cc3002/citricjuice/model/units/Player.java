package com.github.cc3002.citricjuice.model.units;

import com.github.cc3002.citricjuice.controller.handlers.IHandler;
import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.units.boss.BossUnit;
import com.github.cc3002.citricjuice.model.units.wild.WildUnit;
import com.github.cc3002.citricjuice.phases.InGamePhase;
import com.github.cc3002.citricjuice.phases.InvalidTransitionException;
import com.github.cc3002.citricjuice.phases.TurnPhase;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:benjamin.dpb@gmail.com"> Benjamín del Pino </a>.

 */
public class Player extends AbstractUnit {
  private int normaLevel;
  private NormaGoal normaGoal;
  private IPanel panel;
  private HomePanel homePanel;
  private TurnPhase turnPhase;
  private final PropertyChangeSupport changeNormaNotification = new PropertyChangeSupport(this);
  private final PropertyChangeSupport normaClearNotification = new PropertyChangeSupport(this);
  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    normaGoal = NormaGoal.STARS;
    this.setTurnPhase(new InGamePhase());
  }

   /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    changeNormaNotification.firePropertyChange("NORMA_CLEAR", normaLevel, normaLevel + 1);
    normaLevel++;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
           getAtk() == player.getAtk() &&
           getDef() == player.getDef() &&
           getEvd() == player.getEvd() &&
           getNormaLevel() == player.getNormaLevel() &&
           getStars() == player.getStars() &&
           getCurrentHP() == player.getCurrentHP() &&
           getName().equals(player.getName());
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHP(), getAtk(), getDef(), getEvd());
  }

  /**
   * The player who activate the panel
   *
   * @param panel the panel who will be activated
   */
  public void activatePanel(@NotNull IPanel panel){
    panel.activatePanelEffectBy(this);
  }

  @Override
  public void attack(IUnit unit) {
    int dmg = this.setDmg();
    unit.receivePlayerAttack(this, dmg);
  }

  @Override
  public void receiveWildAttack(WildUnit wildUnit, int dmg) {
    if(this.defend){
      this.defend(dmg);
    }
    else if (this.evade){
      this.evade(dmg);
    }
    if(this.isOutOfCombat()){
      wildUnit.increaseWinsBy(2);
      int stars = (int) (this.getStars() * 0.5);
      this.reduceStarsBy(stars);
      wildUnit.increaseStarsBy(stars);
    }
  }

  @Override
  public void receiveBossAttack(BossUnit bossUnit, int dmg) {
    if(this.defend){
      this.defend(dmg);
      this.defend = false;
    }
    else if (this.evade){
      this.evade(dmg);
      this.evade = false;
    }
    if(this.isOutOfCombat()){
      bossUnit.increaseWinsBy(2);
      int stars = (int) (this.getStars() * 0.5);
      this.reduceStarsBy(stars);
      bossUnit.increaseStarsBy(stars);
    }
  }

  @Override
  public void receivePlayerAttack(Player player, int dmg) {
    if(this.defend){
      this.defend(dmg);
      this.defend = false;
    }
    else if (this.evade){
      this.evade(dmg);
      this.evade = false;
    }
    if(this.isOutOfCombat()){
      player.increaseWinsBy(2);
      int stars = (int) (this.getStars() * 0.5);
      this.reduceStarsBy(stars);
      player.increaseStarsBy(stars);
    }
  }

  /**
   * @return el tipo de norma que tiene actualmente
   */
  public NormaGoal getNormaGoal() {
      return normaGoal;
  }

  /**
   * @param goal que sera fijada
   */
  public void setNormaGoal(NormaGoal goal) {
    NormaGoal oldNorma = normaGoal;
    this.normaGoal = goal;
    changeNormaNotification.firePropertyChange("CHANGE_NORMA", oldNorma, goal);
  }

  /**
   * @return el panel actual del player
   */
  public IPanel getPanel() {
    return panel;
  }

  /**
   * @param panel que sera seteado al player
   */
  public void setPanel(IPanel panel) {
        this.panel = panel;
    }


  public void changeNormaListener(IHandler changeNormaHadler) {
    changeNormaNotification.addPropertyChangeListener(changeNormaHadler);
  }

  public void normaClearListener(IHandler normaClearHandler){
    normaClearNotification.addPropertyChangeListener(normaClearHandler);
  }

  /**
   * @param homePanel del player
   */
  public void setHomePanel(HomePanel homePanel) {
    this.homePanel = homePanel;
  }

  /**
   * @return el home panel del player
   */
  public HomePanel getHomePanel() {
    return homePanel;
  }

  /**
   * @param turnPhase fase del turno en que se encuentra el player
   */
  public void setTurnPhase(TurnPhase turnPhase) {
    this.turnPhase = turnPhase;
    turnPhase.setPlayer(this);
  }

  /**
   * Los siguientes metodos retornan true si el player se encuentra en
   * un estado en particular o false en caso contrario.
   */
  public boolean isInGame() {
    return turnPhase.isInGame();
  }
  public boolean isOutOfGame() {
    return turnPhase.isOutOfGame();
  }
  public boolean receivingStars() {
    return turnPhase.isReceivingStars();
  }
  public boolean playingCard() {
    return turnPhase.playingCard();
  }
  public boolean isPlayingCard() {
    return turnPhase.playingCard();
  }
  public boolean isMoving() {
    return turnPhase.isMoving();
  }
  public boolean isInBattle() {
    return turnPhase.isInBattle();
  }
  public boolean isActivatingTrapCard() {
    return turnPhase.activatingTrapCard();
  }
  public boolean isActivatingPanel() {
    return turnPhase.activatingPanel();
  }

  /**
   * Los siguientes metodos cambian la fase actual del turno del jugador
   */
  public void receiveStarsPhase() throws InvalidTransitionException {
    turnPhase.receiveStars();
  }
  public void playCardPhase() throws InvalidTransitionException {
    turnPhase.playCard();
  }
  public void movePhase() throws InvalidTransitionException {
    turnPhase.movePlayer();
  }
  public void battlePhase() throws InvalidTransitionException {
    turnPhase.battlePhase();
  }
  public void activateTrapCardPhase() throws InvalidTransitionException {
    turnPhase.activateTrapCard();
  }
  public void activatePanelPhase() throws InvalidTransitionException {
    turnPhase.activatePanelPhase();
  }
  public void outOfGame() throws InvalidTransitionException {
    turnPhase.outOfGame();
  }
  public void backToTheGame() throws InvalidTransitionException {
    turnPhase.backToGame();
  }

}
