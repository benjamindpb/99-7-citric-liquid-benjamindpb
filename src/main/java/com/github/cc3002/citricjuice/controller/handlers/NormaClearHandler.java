package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.units.Player;

import java.beans.PropertyChangeEvent;

public class NormaClearHandler implements IHandler {
    private GameController controller;

    public NormaClearHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Player player = controller.getTurnOwner();
        propertyChangeEvent.getNewValue();
        controller.normaCheck(player);
    }
}
