package com.gameinventory.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemId) {
        super("❌ Item dengan ID '" + itemId + "' tidak ditemukan.");
    }
}