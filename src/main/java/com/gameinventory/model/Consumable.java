package com.gameinventory.model;

public abstract class Consumable extends Item
        implements Usable, Tradeable {

    private int effectValue;

    public Consumable(String id,
                      String name,
                      String description,
                      Rarity rarity,
                      int tradeValue,
                      int effectValue) {

        super(
                id,
                name,
                description,
                rarity,
                ItemType.CONSUMABLE,
                tradeValue
        );

        this.effectValue = effectValue;
    }

    public int getEffectValue() {
        return effectValue;
    }

    @Override
    public boolean isConsumedOnUse() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Effect +%d | Value: %d gold",
                getRarity().getLabel(),
                effectValue,
                getTradeValue()
        );
    }
}