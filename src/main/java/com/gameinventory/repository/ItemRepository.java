package com.gameinventory.repository;

import com.gameinventory.exception.ItemNotFoundException;
import com.gameinventory.model.Item;

import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findById(String id) throws ItemNotFoundException;
    List<Item> findAll();
    void delete(String id) throws ItemNotFoundException;
}