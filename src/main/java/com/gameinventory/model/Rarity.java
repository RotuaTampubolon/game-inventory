package com.gameinventory.model;

public enum Rarity {
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;

    public String getLabel() {
        return switch (this) {
            case COMMON    -> "⚪ Common";
            case UNCOMMON  -> "🟢 Uncommon";
            case RARE      -> "🔵 Rare";
            case EPIC      -> "🟣 Epic";
            case LEGENDARY -> "🟡 Legendary";
        };
    }
}