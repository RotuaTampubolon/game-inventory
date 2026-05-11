package com.gameinventory.cli;
import com.gameinventory.factory.ItemFactory;
import com.gameinventory.exception.InventoryFullException;
import com.gameinventory.exception.ItemNotFoundException;
import com.gameinventory.model.*;
import com.gameinventory.service.InventoryService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class InventoryCLI {

    private final InventoryService service;
    private final Scanner scanner;

    public InventoryCLI(InventoryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("========================================");
        System.out.println("       🎮 GAME INVENTORY SYSTEM         ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showAllItems();
                case "2" -> addItem();
                case "3" -> searchItem();
                case "4" -> deleteItem();
                case "0" -> { running = false; System.out.println("👋 Goodbye!"); }
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Lihat semua item");
        System.out.println("2. Tambah item");
        System.out.println("3. Cari item by ID");
        System.out.println("4. Hapus item");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");
    }

    private void showAllItems() {
        List<Item> items = service.getAllItems();
        if (items.isEmpty()) {
            System.out.println("📭 Inventory kosong.");
            return;
        }
        System.out.println("\n📦 Daftar Item:");
        items.forEach(item -> System.out.println("  " + item));
    }

    private void addItem() {
        System.out.println("\n➕ Tambah Item Baru");

        System.out.print("Nama item   : ");
        String name = scanner.nextLine().trim();

        System.out.print("Deskripsi   : ");
        String desc = scanner.nextLine().trim();

        ItemType type = selectItemType();
        if (type == null) return;

        Rarity rarity = selectRarity();
        if (rarity == null) return;

        System.out.print("Trade value : ");
        int tradeValue;
        try {
            tradeValue = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️  Trade value harus angka.");
            return;
        }

Item item = ItemFactory.create(
        type,
        name,
        desc,
        rarity,
        tradeValue
);

        try {
            service.addItem(item);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchItem() {
        System.out.print("\n🔍 Masukkan ID item: ");
        String id = scanner.nextLine().trim();
        try {
            Item item = service.getItem(id);
            System.out.println("\n" + item.getInfo());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteItem() {
        System.out.print("\n🗑️  Masukkan ID item yang dihapus: ");
        String id = scanner.nextLine().trim();
        try {
            service.removeItem(id);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private ItemType selectItemType() {
        System.out.println("Tipe item: 1.WEAPON  2.ARMOR  3.CONSUMABLE  4.QUEST_ITEM");
        System.out.print("Pilihan: ");
        return switch (scanner.nextLine().trim()) {
            case "1" -> ItemType.WEAPON;
            case "2" -> ItemType.ARMOR;
            case "3" -> ItemType.CONSUMABLE;
            case "4" -> ItemType.QUEST_ITEM;
            default  -> { System.out.println("⚠️  Tipe tidak valid."); yield null; }
        };
    }

    private Rarity selectRarity() {
        System.out.println("Rarity: 1.COMMON  2.UNCOMMON  3.RARE  4.EPIC  5.LEGENDARY");
        System.out.print("Pilihan: ");
        return switch (scanner.nextLine().trim()) {
            case "1" -> Rarity.COMMON;
            case "2" -> Rarity.UNCOMMON;
            case "3" -> Rarity.RARE;
            case "4" -> Rarity.EPIC;
            case "5" -> Rarity.LEGENDARY;
            default  -> { System.out.println("⚠️  Rarity tidak valid."); yield null; }
        };
    }
}