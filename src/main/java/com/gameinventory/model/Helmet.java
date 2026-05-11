package com.gameinventory.model;

public class Helmet extends Armor {

    private boolean hasVisor;

    public Helmet(String id,
                  String name,
                  String description,
                  Rarity rarity,
                  int tradeValue,
                  int defenseBonus,
                  boolean hasVisor) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                defenseBonus
        );

        this.hasVisor = hasVisor;
    }

    public boolean hasVisor() {
        return hasVisor;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Helmet | DEF +%d | Visor: %s | Value: %d gold",
                getRarity().getLabel(),
                getStatBonus(),
                hasVisor ? "Yes" : "No",
                getTradeValue()
        );
    }
}