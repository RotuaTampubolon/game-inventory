package com.gameinventory.cli;

public class Color {

    // ANSI escape prefix
    private static final String ESC = "\u001B[";

    // Warna teks
    public static final String RESET   = ESC + "0m";
    public static final String RED     = ESC + "31m";
    public static final String GREEN   = ESC + "32m";
    public static final String YELLOW  = ESC + "33m";
    public static final String BLUE    = ESC + "34m";
    public static final String MAGENTA = ESC + "35m";
    public static final String CYAN    = ESC + "36m";
    public static final String WHITE   = ESC + "37m";

    // Bold
    public static final String BOLD    = ESC + "1m";

    // Warna khusus per rarity
    public static final String RARITY_COMMON    = WHITE;
    public static final String RARITY_UNCOMMON  = GREEN;
    public static final String RARITY_RARE      = BLUE;
    public static final String RARITY_EPIC      = MAGENTA;
    public static final String RARITY_LEGENDARY = YELLOW;

    // Inisialisasi Jansi — wajib dipanggil sekali di Main
    public static void init() {
        // No-op: Jansi dependency removed.
    }

    public static void uninstall() {
        // No-op: Jansi dependency removed.
    }

    // Helper print dengan warna
    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }

    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }

    public static String colorize(String color, String text) {
        return color + text + RESET;
    }
}