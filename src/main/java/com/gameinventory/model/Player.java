package com.gameinventory.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String id;
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int gold;
    private int maxCapacity;
    private List<Item> inventory;
    private Item equippedWeapon;
    private Item equippedArmor;

    public Player(String id, String name, int hp, int attack,
                  int defense, int gold, int maxCapacity) {
        this.id           = id;
        this.name         = name;
        this.hp           = hp;
        this.maxHp        = hp;
        this.attack       = attack;
        this.defense      = defense;
        this.gold         = gold;
        this.maxCapacity  = maxCapacity;
        this.inventory    = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor  = null;
    }

    // Getters
    public String getId()          { return id; }
    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttack()         { return attack; }
    public int getDefense()        { return defense; }
    public int getGold()           { return gold; }
    public int getMaxCapacity()    { return maxCapacity; }
    public List<Item> getInventory()  { return inventory; }
    public Item getEquippedWeapon()   { return equippedWeapon; }
    public Item getEquippedArmor()    { return equippedArmor; }

    // Setters
    public void setHp(int hp)           { this.hp = Math.max(0, Math.min(hp, maxHp)); }
    public void setAttack(int attack)   { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setGold(int gold)       { this.gold = gold; }
    public void setEquippedWeapon(Item item) { this.equippedWeapon = item; }
    public void setEquippedArmor(Item item)  { this.equippedArmor = item; }

    public boolean isAlive()          { return hp > 0; }
    public boolean isInventoryFull()  { return inventory.size() >= maxCapacity; }

    public void addToInventory(Item item)    { inventory.add(item); }
    public void removeFromInventory(Item item) { inventory.remove(item); }

    @Override
    public String toString() {
        return String.format(
            "👤 %s | HP: %d/%d | ATK: %d | DEF: %d | Gold: %d | Items: %d/%d",
            name, hp, maxHp, attack, defense, gold,
            inventory.size(), maxCapacity
        );
    }
}