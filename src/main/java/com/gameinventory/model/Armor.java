package com.gameinventory.model;

public abstract class Armor extends Equipment {

    public Armor(String id,
                 String name,
                 String description,
                 Rarity rarity,
                 int tradeValue,
                 int defenseBonus) {

        super(
                id,
                name,
                description,
                rarity,
                ItemType.ARMOR,
                tradeValue,
                defenseBonus
        );
    }

    @Override
    protected void applyStatBonus(Player player) {

        player.setDefense(
                player.getDefense() + getStatBonus()
        );
    }

    @Override
    protected void removeStatBonus(Player player) {

        player.setDefense(
                player.getDefense() - getStatBonus()
        );
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Armor | DEF +%d | Value: %d gold",
                getRarity().getLabel(),
                getStatBonus(),
                getTradeValue()
        );
    }
}