package com.gameinventory;

import com.gameinventory.cli.InventoryCLI;
import com.gameinventory.cli.PlayerCLI;
import com.gameinventory.service.EquipService;
import com.gameinventory.service.InventoryService;
import com.gameinventory.service.PlayerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService(20);
        PlayerService    playerService    = new PlayerService();
        EquipService     equipService     = new EquipService();

        InventoryCLI inventoryCLI = new InventoryCLI(inventoryService);
        PlayerCLI    playerCLI    = new PlayerCLI(playerService, equipService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("       🎮 GAME INVENTORY SYSTEM         ");
            System.out.println("========================================");
            System.out.println("1. Item Management");
            System.out.println("2. Player Management");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> inventoryCLI.start();
                case "2" -> playerCLI.start();
                case "0" -> { running = false; System.out.println("👋 Goodbye!"); }
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }
}