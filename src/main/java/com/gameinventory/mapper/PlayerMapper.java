package com.gameinventory.mapper;

import com.gameinventory.db.DBConnection;
import com.gameinventory.exception.PlayerNotFoundException;
import com.gameinventory.model.Player;
import com.gameinventory.repository.PlayerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper implements PlayerRepository {

    private final Connection conn;

    public PlayerMapper() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public void save(Player player) {
        String sql = """
                INSERT INTO players (id, name, hp, attack, defense, gold, max_capacity)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, player.getId());
            stmt.setString(2, player.getName());
            stmt.setInt(3, player.getHp());
            stmt.setInt(4, player.getAttack());
            stmt.setInt(5, player.getDefense());
            stmt.setInt(6, player.getGold());
            stmt.setInt(7, player.getMaxCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal menyimpan player: " + e.getMessage());
        }
    }

    @Override
    public Player findById(String id) throws PlayerNotFoundException {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapRow(rs);
            throw new PlayerNotFoundException(id);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal mencari player: " + e.getMessage());
        }
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY name";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) players.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal mengambil data player: " + e.getMessage());
        }
        return players;
    }

    @Override
    public void update(Player player) {
        String sql = """
                UPDATE players
                SET hp = ?, attack = ?, defense = ?, gold = ?
                WHERE id = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, player.getHp());
            stmt.setInt(2, player.getAttack());
            stmt.setInt(3, player.getDefense());
            stmt.setInt(4, player.getGold());
            stmt.setString(5, player.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal update player: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws PlayerNotFoundException {
        findById(id);
        String sql = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal menghapus player: " + e.getMessage());
        }
    }

    private Player mapRow(ResultSet rs) throws SQLException {
        return new Player(
                rs.getString("id"),
                rs.getString("name"),
                rs.getInt("hp"),
                rs.getInt("attack"),
                rs.getInt("defense"),
                rs.getInt("gold"),
                rs.getInt("max_capacity")
        );
    }
}