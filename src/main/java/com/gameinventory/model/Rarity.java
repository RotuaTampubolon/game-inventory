package com.gameinventory.model;

import com.gameinventory.cli.Color;

public enum Rarity {
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;

    public String getLabel() {
        return switch (this) {
            case COMMON    -> Color.colorize(Color.RARITY_COMMON,    "⚪ Common");
            case UNCOMMON  -> Color.colorize(Color.RARITY_UNCOMMON,  "🟢 Uncommon");
            case RARE      -> Color.colorize(Color.RARITY_RARE,      "🔵 Rare");
            case EPIC      -> Color.colorize(Color.RARITY_EPIC,      "🟣 Epic");
            case LEGENDARY -> Color.colorize(Color.RARITY_LEGENDARY, "🟡 Legendary");
        };
    }
}