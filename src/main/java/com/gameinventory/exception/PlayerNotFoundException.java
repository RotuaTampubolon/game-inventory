package com.gameinventory.exception;

public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException(String playerId) {
        super("❌ Player dengan ID '" + playerId + "' tidak ditemukan.");
    }
}