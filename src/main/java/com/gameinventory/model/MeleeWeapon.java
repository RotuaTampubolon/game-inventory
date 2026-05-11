package com.gameinventory.model;

public class MeleeWeapon extends Weapon {

    private String weaponClass;

    public MeleeWeapon(String id,
                       String name,
                       String description,
                       Rarity rarity,
                       int tradeValue,
                       int attackBonus,
                       String weaponClass) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                attackBonus
        );

        this.weaponClass = weaponClass;
    }

    public String getWeaponClass() {
        return weaponClass;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | %s | ATK +%d | Value: %d gold",
                getRarity().getLabel(),
                weaponClass,
                getStatBonus(),
                getTradeValue()
        );
    }
}