package com.gameinventory.mapper;
import com.gameinventory.factory.ItemFactory;
import com.gameinventory.db.DBConnection;
import com.gameinventory.exception.ItemNotFoundException;
import com.gameinventory.model.Item;
import com.gameinventory.model.ItemType;
import com.gameinventory.model.Rarity;
import com.gameinventory.repository.ItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper implements ItemRepository {

    private final Connection conn;

    public ItemMapper() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public void save(Item item) {
        String sql = """
                INSERT INTO items (id, name, description, type, rarity, trade_value)
                VALUES (?, ?, ?, ?, ?, ?)
                ON CONFLICT (id) DO UPDATE
                SET name = EXCLUDED.name,
                    description = EXCLUDED.description,
                    trade_value = EXCLUDED.trade_value
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setString(3, item.getDescription());
            stmt.setString(4, item.getType().name());
            stmt.setString(5, item.getRarity().name());
            stmt.setInt(6, item.getTradeValue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal menyimpan item: " + e.getMessage());
        }
    }

    @Override
    public Item findById(String id) throws ItemNotFoundException {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
            throw new ItemNotFoundException(id);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Gagal mencari item: " + e.getMessage());
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY rarity, name";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                items.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Gagal mengambil data item: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void delete(String id) throws ItemNotFoundException {
        findById(id); // validasi dulu — lempar exception kalau tidak ada
        String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(" Gagal menghapus item: " + e.getMessage());
        }
    }

private Item mapRow(ResultSet rs)
        throws SQLException {

    return ItemFactory.fromResultSet(rs);
}
    }