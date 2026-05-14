package com.gameinventory.cli;

import com.gameinventory.exception.PlayerNotFoundException;
import com.gameinventory.model.Item;
import com.gameinventory.model.Player;
import com.gameinventory.service.PlayerService;
import com.gameinventory.service.TradeService;

import java.util.List;
import java.util.Scanner;

public class TradeCLI {

    private final TradeService tradeService;
    private final PlayerService playerService;
    private final Scanner scanner;

    public TradeCLI(TradeService tradeService, PlayerService playerService) {
        this.tradeService  = tradeService;
        this.playerService = playerService;
        this.scanner       = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> buyFromShop();
                case "2" -> sellToShop();
                case "3" -> tradeBetweenPlayers();
                case "0" -> running = false;
                default  -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- TRADE MENU ---");
        System.out.println("1. Beli item dari toko");
        System.out.println("2. Jual item ke toko");
        System.out.println("3. Trade antar player");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }

    private void buyFromShop() {
        Player player = selectPlayer("Pilih player pembeli");
        if (player == null) return;

        System.out.println("\n🏪 Fitur beli dari toko belum didukung oleh TradeService saat ini.");
    }

    private void sellToShop() {
        Player player = selectPlayer("Pilih player penjual");
        if (player == null) return;

        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("📭 Inventory kosong.");
            return;
        }

        System.out.println("\n💰 Item yang bisa dijual (harga jual = 50% harga asli):");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("  %d. %s | Jual: %d gold%n",
                    i + 1, inventory.get(i).getName(),
                    inventory.get(i).getTradeValue() / 2);
        }

        System.out.print("Pilih item (nomor): ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            tradeService.sellItem(player, inventory.get(idx));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void tradeBetweenPlayers() {
        System.out.println("\n🔄 Trade Antar Player");

        Player sender = selectPlayer("Pilih player pertama (pengirim)");
        if (sender == null) return;

        Player receiver = selectPlayer("Pilih player kedua (penerima)");
        if (receiver == null) return;

        Item senderItem   = selectItemFromInventory(sender, "Pilih item dari " + sender.getName());
        if (senderItem == null) return;

        Item receiverItem = selectItemFromInventory(receiver, "Pilih item dari " + receiver.getName());
        if (receiverItem == null) return;

        try {
            tradeService.tradeItems(sender, senderItem, receiver, receiverItem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Player selectPlayer(String prompt) {
        System.out.print("\n" + prompt + " (ID): ");
        try {
            return playerService.getPlayer(scanner.nextLine().trim());
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Item selectItemFromInventory(Player player, String prompt) {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("📭 " + player.getName() + " tidak punya item.");
            return null;
        }

        System.out.println("\n" + prompt + ":");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, inventory.get(i).getName());
        }

        System.out.print("Pilihan: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            return inventory.get(idx);
        } catch (Exception e) {
            System.out.println("⚠️  Input tidak valid.");
            return null;
        }
    }
}