package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;

import java.beans.PropertyChangeEvent;

public class PlayerMovedHandler implements IHandler {
    private GameController controller;

    public PlayerMovedHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

    }
}
