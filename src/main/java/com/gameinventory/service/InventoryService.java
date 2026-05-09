package com.gameinventory.service;

import com.gameinventory.exception.InventoryFullException;
import com.gameinventory.exception.ItemNotFoundException;
import com.gameinventory.mapper.ItemMapper;
import com.gameinventory.model.Item;

import java.util.List;

public class InventoryService {

    private final ItemMapper itemMapper;
    private final int maxCapacity;

    public InventoryService(int maxCapacity) {
        this.itemMapper   = new ItemMapper();
        this.maxCapacity  = maxCapacity;
    }

    public void addItem(Item item) throws InventoryFullException {
        if (itemMapper.findAll().size() >= maxCapacity) {
            throw new InventoryFullException(maxCapacity);
        }
        itemMapper.save(item);
        System.out.println("✅ Item '" + item.getName() + "' berhasil ditambahkan.");
    }

    public void removeItem(String id) throws ItemNotFoundException {
        itemMapper.delete(id);
        System.out.println("✅ Item berhasil dihapus.");
    }

    public Item getItem(String id) throws ItemNotFoundException {
        return itemMapper.findById(id);
    }

    public List<Item> getAllItems() {
        return itemMapper.findAll();
    }
}