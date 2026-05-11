package com.gameinventory.model;

public abstract class Weapon extends Equipment {

    public Weapon(String id,
                  String name,
                  String description,
                  Rarity rarity,
                  int tradeValue,
                  int attackBonus) {

        super(
                id,
                name,
                description,
                rarity,
                ItemType.WEAPON,
                tradeValue,
                attackBonus
        );
    }

    @Override
    protected void applyStatBonus(Player player) {

        player.setAttack(
                player.getAttack() + getStatBonus()
        );
    }

    @Override
    protected void removeStatBonus(Player player) {

        player.setAttack(
                player.getAttack() - getStatBonus()
        );
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Weapon | ATK +%d | Value: %d gold",
                getRarity().getLabel(),
                getStatBonus(),
                getTradeValue()
        );
    }
}