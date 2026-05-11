package com.gameinventory.model;

public class Potion extends Consumable {

    public Potion(String id,
                  String name,
                  String description,
                  Rarity rarity,
                  int tradeValue,
                  int healAmount) {

        super(
                id,
                name,
                description,
                rarity,
                tradeValue,
                healAmount
        );
    }

    @Override
    public void use(Player player) {

        int healed = Math.min(
                getEffectValue(),
                100 - player.getHp()
        );

        player.setHp(
                player.getHp() + healed
        );

        System.out.printf(
                "%s digunakan! HP +%d (HP sekarang: %d)%n",
                getName(),
                healed,
                player.getHp()
        );
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Potion | Heal +%d HP | Value: %d gold",
                getRarity().getLabel(),
                getEffectValue(),
                getTradeValue()
        );
    }
}