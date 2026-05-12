package com.gameinventory.service;

import com.gameinventory.exception.PlayerNotFoundException;
import com.gameinventory.mapper.PlayerMapper;
import com.gameinventory.model.Player;

import java.util.List;
import java.util.UUID;

public class PlayerService {

    private final PlayerMapper playerMapper;

    public PlayerService() {
        this.playerMapper = new PlayerMapper();
    }

    public void createPlayer(String name) {
        Player player = new Player(
                UUID.randomUUID().toString(),
                name,
                100,  // hp
                10,   // attack
                5,    // defense
                500,  // gold
                20    // maxCapacity
        );
        playerMapper.save(player);
        System.out.println("✅ Player '" + name + "' berhasil dibuat!");
        System.out.println("  " + player);
    }

    public Player getPlayer(String id) throws PlayerNotFoundException {
        return playerMapper.findById(id);
    }

    public List<Player> getAllPlayers() {
        return playerMapper.findAll();
    }

    public void updatePlayer(Player player) {
        playerMapper.update(player);
    }
}