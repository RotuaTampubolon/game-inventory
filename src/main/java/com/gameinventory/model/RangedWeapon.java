package com.gameinventory.model;

public class RangedWeapon extends Weapon {

    private int range;

    public RangedWeapon(String id,
                        String name,
                        String description,
                        Rarity rarity,
                        int tradeValue,
                        int attackBonus,
                        int range) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                attackBonus
        );

        this.range = range;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Ranged | ATK +%d | Range: %d | Value: %d gold",
                getRarity().getLabel(),
                getStatBonus(),
                range,
                getTradeValue()
        );
    }
}