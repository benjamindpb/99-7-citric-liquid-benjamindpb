package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TurnPhasesTest {
    private Player kai;
    private Player suguri;

    @BeforeEach
    public void setUp(){
        kai = new Player("Kai", 5, 1, 0, 0);
    }

    /**
     * Esta bateria de test no hacen nada mas que comprobar si las fases fueron
     * creadas de buena forma :3
     */
    @Test
    public void noActionPhaseTest1(){
        assertTrue(kai.isInGame());
        assertFalse(kai.isOutOfGame());
        kai.receiveStarsPhase();
        assertTrue(kai.receivingStars());
        assertFalse(kai.isPlayingCard());
        kai.playCardPhase();
        assertTrue(kai.playingCard());
        kai.movePhase();
        assertTrue(kai.isMoving());
        kai.battlePhase();
        assertTrue(kai.isInBattle());
        kai.activateTrapCardPhase();
        assertTrue(kai.isActivatingTrapCard());
        kai.activatePanelPhase();
        assertTrue(kai.isActivatingPanel());
        kai.backToTheGame();
        assertTrue(kai.isInGame());
    }

    @Test
    public void noActionPhaseTest2(){
        assertTrue(kai.isInGame());
        assertFalse(kai.isOutOfGame());
        kai.receiveStarsPhase();
        assertTrue(kai.receivingStars());
        assertFalse(kai.isPlayingCard());
        kai.playCardPhase();
        assertTrue(kai.playingCard());
        assertFalse(kai.isActivatingPanel());
        kai.movePhase();
        assertTrue(kai.isMoving());
        kai.battlePhase();
        assertTrue(kai.isInBattle());
        assertFalse(kai.isActivatingTrapCard());
        kai.outOfGame();
        assertFalse(kai.isInBattle());

    }

    @Test
    public void noActionPhaseTest3(){
        assertTrue(kai.isInGame());
        assertFalse(kai.isOutOfGame());
        kai.receiveStarsPhase();
        assertTrue(kai.receivingStars());
        kai.movePhase();
        assertTrue(kai.isMoving());
        kai.activateTrapCardPhase();
        assertTrue(kai.isActivatingTrapCard());
        kai.outOfGame();
        assertFalse(kai.isInGame());
    }

    @Test
    public void noActionPhaseTest4(){
        assertTrue(kai.isInGame());
        assertFalse(kai.isOutOfGame());
        kai.receiveStarsPhase();
        assertTrue(kai.receivingStars());
        kai.movePhase();
        assertTrue(kai.isMoving());
        kai.activateTrapCardPhase();
        assertTrue(kai.isActivatingTrapCard());
        kai.activatePanelPhase();
        kai.outOfGame();
        assertTrue(kai.isOutOfGame());
    }

    @Test
    public void noActionPhaseTest5(){
        suguri = new Player("Suguri", 4, 1, -1 , 2);
        assertTrue(suguri.isInGame());
        assertFalse(suguri.isOutOfGame());
        suguri.receiveStarsPhase();
        assertTrue(suguri.receivingStars());
        suguri.movePhase();
        assertTrue(suguri.isMoving());
        suguri.activateTrapCardPhase();
        assertTrue(suguri.isActivatingTrapCard());
        assertFalse(suguri.isMoving());
        suguri.outOfGame();
        assertFalse(suguri.isInGame());
        suguri.backToTheGame();
        assertFalse(suguri.isOutOfGame());
        assertTrue(suguri.isInGame());
        assertFalse(kai.receivingStars());
    }

    @Test
    public void errorStateTest(){
        kai.battlePhase();
        kai.movePhase();
        kai.outOfGame();
        kai.movePhase();
        kai.activatePanelPhase();
        kai.activateTrapCardPhase();
        kai.playCardPhase();
        kai.backToTheGame();
        kai.receiveStarsPhase();
    }


}
