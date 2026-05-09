package com.gameinventory.exception;

public class InventoryFullException extends Exception {
    public InventoryFullException(int maxCapacity) {
        super("❌ Inventory penuh! Kapasitas maksimal: " + maxCapacity + " item.");
    }
}