package com.gameinventory.repository;

import com.gameinventory.exception.PlayerNotFoundException;
import com.gameinventory.model.Player;

import java.util.List;

public interface PlayerRepository {
    void save(Player player);
    Player findById(String id) throws PlayerNotFoundException;
    List<Player> findAll();
    void update(Player player);
    void delete(String id) throws PlayerNotFoundException;
}