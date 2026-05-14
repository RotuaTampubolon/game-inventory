package com.gameinventory;

import com.gameinventory.cli.*;
import com.gameinventory.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService    inventoryService = new InventoryService(20);
        PlayerService       playerService    = new PlayerService();
        EquipService        equipService     = new EquipService();
        TradeService        tradeService     = new TradeService();

        InventoryCLI        inventoryCLI     = new InventoryCLI(inventoryService);
        PlayerCLI           playerCLI        = new PlayerCLI(playerService, equipService);
        TradeCLI            tradeCLI         = new TradeCLI(tradeService, playerService);
        InventoryFilterCLI  filterCLI        = new InventoryFilterCLI(inventoryService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("       🎮 GAME INVENTORY SYSTEM         ");
            System.out.println("========================================");
            System.out.println("1. Item Management");
            System.out.println("2. Player Management");
            System.out.println("3. Trade");
            System.out.println("4. Filter & Sort Inventory");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> inventoryCLI.start();
                case "2" -> playerCLI.start();
                case "3" -> tradeCLI.start();
                case "4" -> filterCLI.start();
                case "0" -> { running = false; System.out.println("👋 Goodbye!"); }
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }
}