package com.github.cc3002.citricjuice.model.cards.battle;

public class BigMagnumCard extends battleCard{
    public BigMagnumCard() {
        super("Big Magnum",
                "Pays 1 HP to play this card." +
                        "During this battle wins +2 ATK." +
                        "This card cant be used by a player with 1 HP",
                3,
                20);
    }
}
