package com.gameinventory.model;

public interface Usable {

    void use(Player player);

    boolean isConsumedOnUse();
}