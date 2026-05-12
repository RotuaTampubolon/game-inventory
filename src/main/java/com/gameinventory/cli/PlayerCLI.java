package com.gameinventory.cli;

import com.gameinventory.exception.PlayerNotFoundException;
import com.gameinventory.model.*;
import com.gameinventory.service.EquipService;
import com.gameinventory.service.PlayerService;

import java.util.List;
import java.util.Scanner;

public class PlayerCLI {

    private final PlayerService playerService;
    private final EquipService equipService;
    private final Scanner scanner;

    public PlayerCLI(PlayerService playerService, EquipService equipService) {
        this.playerService = playerService;
        this.equipService  = equipService;
        this.scanner       = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showAllPlayers();
                case "2" -> createPlayer();
                case "3" -> showPlayerStats();
                case "4" -> equipMenu();
                case "5" -> useItemMenu();
                case "0" -> running = false;
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- PLAYER MENU ---");
        System.out.println("1. Lihat semua player");
        System.out.println("2. Buat player baru");
        System.out.println("3. Lihat stats player");
        System.out.println("4. Equip / Unequip item");
        System.out.println("5. Gunakan item");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }

    private void showAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("📭 Belum ada player.");
            return;
        }
        System.out.println("\n👥 Daftar Player:");
        players.forEach(p -> System.out.println("  " + p));
    }

    private void createPlayer() {
        System.out.println("\n➕ Buat Player Baru");
        System.out.print("Nama player: ");
        String name = scanner.nextLine().trim();
        try {
            playerService.createPlayer(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showPlayerStats() {
        Player player = selectPlayer();
        if (player == null) return;
        System.out.println("\n" + player);
        equipService.showEquippedItems(player);
    }

    private void equipMenu() {
        Player player = selectPlayer();
        if (player == null) return;

        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("📭 Inventory kosong.");
            return;
        }

        System.out.println("\nPilih item (ketik nomor):");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, inventory.get(i).getName());
        }
        System.out.print("Pilihan: ");

        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            Item item = inventory.get(idx);

            System.out.println("1. Equip  2. Unequip");
            System.out.print("Pilihan: ");
            String action = scanner.nextLine().trim();

            if (action.equals("1"))      equipService.equip(player, item);
            else if (action.equals("2")) equipService.unequip(player, item);
            else System.out.println("⚠️  Pilihan tidak valid.");

        } catch (Exception e) {
            System.out.println("⚠️  Input tidak valid.");
        }
    }

    private void useItemMenu() {
        Player player = selectPlayer();
        if (player == null) return;

        List<Item> usableItems = player.getInventory().stream()
                .filter(i -> i instanceof Usable)
                .toList();

        if (usableItems.isEmpty()) {
            System.out.println("📭 Tidak ada item yang bisa digunakan.");
            return;
        }

        System.out.println("\nItem yang bisa digunakan:");
        for (int i = 0; i < usableItems.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, usableItems.get(i).getInfo());
        }
        System.out.print("Pilihan: ");

        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            equipService.useItem(player, usableItems.get(idx));
        } catch (Exception e) {
            System.out.println("⚠️  Input tidak valid.");
        }
    }

    private Player selectPlayer() {
        System.out.print("\nMasukkan ID player: ");
        String id = scanner.nextLine().trim();
        try {
            return playerService.getPlayer(id);
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}