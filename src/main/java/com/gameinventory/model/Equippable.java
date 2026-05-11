package com.gameinventory.model;

public interface Equippable {

    void equip(Player player);

    void unequip(Player player);

    boolean isEquipped();
}