package com.gameinventory.model;

public abstract class Equipment extends Item
        implements Equippable, Tradeable {

    private boolean equipped;
    private int statBonus;

    public Equipment(String id,
                     String name,
                     String description,
                     Rarity rarity,
                     ItemType type,
                     int tradeValue,
                     int statBonus) {

        super(
                id,
                name,
                description,
                rarity,
                type,
                tradeValue
        );

        this.equipped = false;
        this.statBonus = statBonus;
    }

    public int getStatBonus() {
        return statBonus;
    }

    @Override
    public boolean isEquipped() {
        return equipped;
    }

    @Override
    public boolean isTradeable() {
        return !equipped;
    }

    @Override
    public void equip(Player player) {

        if (equipped) {
            System.out.println(getName() + " sudah dipakai.");
            return;
        }

        equipped = true;

        applyStatBonus(player);

        System.out.println(getName() + " berhasil diequip.");
    }

    @Override
    public void unequip(Player player) {

        if (!equipped) {
            System.out.println(getName() + " belum dipakai.");
            return;
        }

        equipped = false;

        removeStatBonus(player);

        System.out.println(getName() + " berhasil diunequip.");
    }

    protected abstract void applyStatBonus(Player player);

    protected abstract void removeStatBonus(Player player);
}