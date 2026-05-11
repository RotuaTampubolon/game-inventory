package com.gameinventory.model;

public class BodyArmor extends Armor {

    private String material;

    public BodyArmor(String id,
                     String name,
                     String description,
                     Rarity rarity,
                     int tradeValue,
                     int defenseBonus,
                     String material) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                defenseBonus
        );

        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Body Armor (%s) | DEF +%d | Value: %d gold",
                getRarity().getLabel(),
                material,
                getStatBonus(),
                getTradeValue()
        );
    }
}