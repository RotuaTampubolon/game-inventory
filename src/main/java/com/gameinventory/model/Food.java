package com.gameinventory.model;

public class Food extends Consumable {

    private int attackBuff;

    public Food(String id,
                String name,
                String description,
                Rarity rarity,
                int tradeValue,
                int healAmount,
                int attackBuff) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                healAmount
        );

        this.attackBuff = attackBuff;
    }

    @Override
    public void use(Player player) {

        player.setHp(
                player.getHp() + getEffectValue()
        );

        player.setAttack(
                player.getAttack() + attackBuff
        );

        System.out.printf(
                "%s dimakan! HP +%d, ATK +%d%n",
                getName(),
                getEffectValue(),
                attackBuff
        );
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Food | HP +%d | ATK +%d | Value: %d gold",
                getRarity().getLabel(),
                getEffectValue(),
                attackBuff,
                getTradeValue()
        );
    }
}