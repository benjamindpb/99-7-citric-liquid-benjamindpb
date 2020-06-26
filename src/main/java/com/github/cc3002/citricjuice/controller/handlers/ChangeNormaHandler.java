package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.NormaGoal;

import java.beans.PropertyChangeEvent;

/**
 * Esta clase representa un observer que esta pendiente del cambio
 * de la norma de un player
 */
public class ChangeNormaHandler implements IHandler{
    private GameController controller;

    /**
     * @param controller referenciado que comunica del cambio
     */
    public ChangeNormaHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        controller.setCurrPlayerNormaGoal((NormaGoal) event.getNewValue());
    }
}
