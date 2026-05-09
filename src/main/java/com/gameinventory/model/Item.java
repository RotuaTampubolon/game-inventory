package com.gameinventory.model;

public abstract class Item {

    private String id;
    private String name;
    private String description;
    private Rarity rarity;
    private ItemType type;
    private int tradeValue;

    public Item(String id, String name, String description,
                Rarity rarity, ItemType type, int tradeValue) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.rarity      = rarity;
        this.type        = type;
        this.tradeValue  = tradeValue;
    }

    // Wajib diimplementasi oleh setiap subclass
    public abstract String getInfo();

    // Getters
    public String getId()          { return id; }
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public Rarity getRarity()      { return rarity; }
    public ItemType getType()      { return type; }
    public int getTradeValue()     { return tradeValue; }

    // Setters
    public void setName(String name)               { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTradeValue(int tradeValue)       { this.tradeValue = tradeValue; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | Value: %d gold",
                rarity.getLabel(), name, type, tradeValue);
    }
}