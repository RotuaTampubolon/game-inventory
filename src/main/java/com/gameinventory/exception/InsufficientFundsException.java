package com.gameinventory.exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(int harga, int gold) {
        super(String.format("❌ Gold tidak cukup! Harga: %d, Gold kamu: %d", harga, gold));
    }
}