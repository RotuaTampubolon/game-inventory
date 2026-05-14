package com.gameinventory.cli;

import com.gameinventory.model.*;
import com.gameinventory.service.InventoryCollection;
import com.gameinventory.service.InventoryService;

import java.util.List;
import java.util.Scanner;

public class InventoryFilterCLI {

    private final InventoryService inventoryService;
    private final Scanner scanner;

    public InventoryFilterCLI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner          = new Scanner(System.in);
    }

    public void start() {
        InventoryCollection collection =
                new InventoryCollection(inventoryService.getAllItems());

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showSortedByValue(collection);
                case "2" -> showSortedByName(collection);
                case "3" -> showFilterByType(collection);
                case "4" -> showFilterByRarity(collection);
                case "5" -> showDiscoveredRarities(collection);
                case "6" -> showLastAction(collection);
                case "0" -> running = false;
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- FILTER & SORT MENU ---");
        System.out.println("1. Sort by trade value (tertinggi)");
        System.out.println("2. Sort by nama (A-Z)");
        System.out.println("3. Filter by tipe");
        System.out.println("4. Filter by rarity");
        System.out.println("5. Lihat rarity yang sudah ditemukan");
        System.out.println("6. Lihat aksi terakhir");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }

    private void showSortedByValue(InventoryCollection collection) {
        System.out.println("\n💰 Item (diurutkan by value):");
        printItems(collection.sortByValue());
    }

    private void showSortedByName(InventoryCollection collection) {
        System.out.println("\n🔤 Item (diurutkan by nama):");
        printItems(collection.sortByName());
    }

    private void showFilterByType(InventoryCollection collection) {
        System.out.println("Tipe: 1.WEAPON  2.ARMOR  3.CONSUMABLE  4.QUEST_ITEM");
        System.out.print("Pilihan: ");
        ItemType type = switch (scanner.nextLine().trim()) {
            case "1" -> ItemType.WEAPON;
            case "2" -> ItemType.ARMOR;
            case "3" -> ItemType.CONSUMABLE;
            case "4" -> ItemType.QUEST_ITEM;
            default  -> null;
        };
        if (type == null) { System.out.println("⚠️  Tidak valid."); return; }
        System.out.println("\n🔍 Item dengan tipe " + type + ":");
        printItems(collection.filterByType(type));
    }

    private void showFilterByRarity(InventoryCollection collection) {
        System.out.println("Rarity: 1.COMMON  2.UNCOMMON  3.RARE  4.EPIC  5.LEGENDARY");
        System.out.print("Pilihan: ");
        Rarity rarity = switch (scanner.nextLine().trim()) {
            case "1" -> Rarity.COMMON;
            case "2" -> Rarity.UNCOMMON;
            case "3" -> Rarity.RARE;
            case "4" -> Rarity.EPIC;
            case "5" -> Rarity.LEGENDARY;
            default  -> null;
        };
        if (rarity == null) { System.out.println("⚠️  Tidak valid."); return; }
        System.out.println("\n🔍 Item dengan rarity " + rarity.getLabel() + ":");
        printItems(collection.filterByRarity(rarity));
    }

    private void showDiscoveredRarities(InventoryCollection collection) {
        System.out.println("\n🗺️  Rarity yang sudah ditemukan:");
        collection.getDiscoveredRarities()
                .forEach(r -> System.out.println("  " + r.getLabel()));
    }

    private void showLastAction(InventoryCollection collection) {
        System.out.println("\n🕓 Aksi terakhir: " + collection.getLastAction());
    }

    private void printItems(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("  📭 Tidak ada item.");
            return;
        }
        items.forEach(i -> System.out.println("  " + i));
    }
}