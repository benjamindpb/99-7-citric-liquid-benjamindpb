package com.github.cc3002.citricjuice.model.cards;

import java.util.Objects;

public abstract class AbstractCard implements ICard{
    private final String name;
    private final String effect;
    private final int norma;
    private final int price;

    public AbstractCard(String name, String effect, int norma, int price) {
        this.name = name;
        this.effect = effect;
        this.norma = norma;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public int getNorma() {
        return norma;
    }

    public int getPrice() {
        return price;
    }


}
