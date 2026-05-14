package com.gameinventory.service;

import com.gameinventory.model.Item;
import com.gameinventory.model.ItemType;
import com.gameinventory.model.Rarity;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryCollection {

    // Akses cepat item by ID
    private final Map<String, Item> itemMap;

    // Item diurutkan by trade value (tertinggi duluan)
    private final PriorityQueue<Item> itemsByValue;

    // Lacak rarity yang sudah pernah ditemukan
    private final Set<Rarity> discoveredRarities;

    // Riwayat aksi terakhir (untuk fitur undo sederhana)
    private final Stack<String> actionHistory;

    public InventoryCollection(List<Item> items) {
        this.itemMap = new LinkedHashMap<>();
        this.itemsByValue = new PriorityQueue<>(
                Comparator.comparingInt(Item::getTradeValue).reversed()
        );
        this.discoveredRarities = new HashSet<>();
        this.actionHistory = new Stack<>();

        // Inisialisasi dari list yang ada
        for (Item item : items) {
            addItem(item);
        }
    }

    public void addItem(Item item) {
        itemMap.put(item.getId(), item);
        itemsByValue.offer(item);
        discoveredRarities.add(item.getRarity());
        actionHistory.push("ADD: " + item.getName());
    }

    public void removeItem(Item item) {
        itemMap.remove(item.getId());
        itemsByValue.remove(item);
        actionHistory.push("REMOVE: " + item.getName());
    }

    // Cari item by ID — O(1)
    public Optional<Item> findById(String id) {
        return Optional.ofNullable(itemMap.get(id));
    }

    // Filter by tipe
    public List<Item> filterByType(ItemType type) {
        return itemMap.values().stream()
                .filter(i -> i.getType() == type)
                .collect(Collectors.toList());
    }

    // Filter by rarity
    public List<Item> filterByRarity(Rarity rarity) {
        return itemMap.values().stream()
                .filter(i -> i.getRarity() == rarity)
                .collect(Collectors.toList());
    }

    // Sort by trade value (tertinggi duluan)
    public List<Item> sortByValue() {
        PriorityQueue<Item> copy = new PriorityQueue<>(itemsByValue);
        List<Item> sorted = new ArrayList<>();
        while (!copy.isEmpty()) sorted.add(copy.poll());
        return sorted;
    }

    // Sort by nama A-Z
    public List<Item> sortByName() {
        return itemMap.values().stream()
                .sorted(Comparator.comparing(Item::getName))
                .collect(Collectors.toList());
    }

    // Rarity apa saja yang sudah ditemukan
    public Set<Rarity> getDiscoveredRarities() {
        return Collections.unmodifiableSet(discoveredRarities);
    }

    // Lihat aksi terakhir
    public String getLastAction() {
        return actionHistory.isEmpty() ? "Tidak ada riwayat." : actionHistory.peek();
    }

    // Semua item sebagai list biasa
    public List<Item> getAllItems() {
        return new ArrayList<>(itemMap.values());
    }

    public int size() {
        return itemMap.size();
    }
}