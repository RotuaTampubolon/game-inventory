package com.gameinventory;

import com.gameinventory.cli.*;
import com.gameinventory.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Color.init(); // wajib dipanggil pertama

        InventoryService   inventoryService = new InventoryService(20);
        PlayerService      playerService    = new PlayerService();
        EquipService       equipService     = new EquipService();
        TradeService       tradeService     = new TradeService();

        InventoryCLI       inventoryCLI  = new InventoryCLI(inventoryService);
        PlayerCLI          playerCLI     = new PlayerCLI(playerService, equipService);
        TradeCLI           tradeCLI      = new TradeCLI(tradeService, playerService);
        InventoryFilterCLI filterCLI     = new InventoryFilterCLI(inventoryService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printBanner();

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> inventoryCLI.start();
                case "2" -> playerCLI.start();
                case "3" -> tradeCLI.start();
                case "4" -> filterCLI.start();
                case "0" -> {
                    running = false;
                    Color.println(Color.YELLOW, "\n👋 Sampai jumpa, Adventurer!");
                }
                default -> Color.println(Color.RED, "⚠️  Pilihan tidak valid.");
            }
        }

        Color.uninstall();
    }

      private static void printBanner() {
            Color.println(Color.YELLOW,
                "  ██████╗  █████╗ ███╗   ███╗███████╗");
            Color.println(Color.YELLOW,
                " ██╔════╝ ██╔══██╗████╗ ████║██╔════╝");
            Color.println(Color.YELLOW,
                " ██║  ███╗███████║██╔████╔██║█████╗  ");
            Color.println(Color.YELLOW,
                " ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  ");
            Color.println(Color.YELLOW,
                " ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗");
            Color.println(Color.YELLOW,
                "  ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");
            Color.println(Color.CYAN,
                " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            Color.println(Color.MAGENTA,
                "      I N V E N T O R Y   S Y S T E M  ");
            Color.println(Color.CYAN,
                " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            Color.println(Color.GREEN,
                "\n  >> Selamat datang, Adventurer! <<");
            Color.println(Color.WHITE,
                "  Kelola inventorymu dan taklukkan dunia!\n");
        }

    private static void printMainMenu() {
        Color.println(Color.CYAN,
            "\n +----------------------------------+");
        Color.println(Color.YELLOW,
            "  |         [ MAIN  MENU ]          |");
        Color.println(Color.CYAN,
            " +----------------------------------+");
        Color.println(Color.GREEN,
            "  | [1] Item Management             |");
        Color.println(Color.GREEN,
            "  | [2] Player Management           |");
        Color.println(Color.YELLOW,
            "  | [3] Trade                       |");
        Color.println(Color.MAGENTA,
            "  | [4] Filter & Sort Inventory     |");
        Color.println(Color.RED,
            "  | [0] Keluar                      |");
        Color.println(Color.CYAN,
            " +----------------------------------+");
        Color.print(Color.CYAN, "  >> Pilihan kamu: ");
    }
}
