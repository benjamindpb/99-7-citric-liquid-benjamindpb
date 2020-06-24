package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.NormaGoal;

import java.beans.PropertyChangeEvent;

public class ChangeNormaHandler implements IHandler{
    private GameController controller;

    public ChangeNormaHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        controller.setCurrPlayerNormaGoal((NormaGoal) event.getNewValue());
    }
}
