package com.gameinventory.factory;

import com.gameinventory.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ItemFactory {

    // Convert ResultSet -> Item
    public static Item fromResultSet(ResultSet rs)
            throws SQLException {

        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        Rarity rarity = Rarity.valueOf(
                rs.getString("rarity")
        );

        ItemType type = ItemType.valueOf(
                rs.getString("type")
        );

        int tradeValue = rs.getInt("trade_value");

        return switch (type) {

            case WEAPON ->
                    new MeleeWeapon(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            10,
                            "Sword"
                    );

            case ARMOR ->
                    new BodyArmor(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            5,
                            "Iron"
                    );

            case CONSUMABLE ->
                    new Potion(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            50
                    );

            case QUEST_ITEM ->
                    new QuestItem(
                            id,
                            name,
                            description,
                            rarity,
                            "Unknown Quest"
                    );
        };
    }

    // Helper create item dari CLI
    public static Item create(
            ItemType type,
            String name,
            String description,
            Rarity rarity,
            int tradeValue
    ) {

        String id = UUID.randomUUID().toString();

        return switch (type) {

            case WEAPON ->
                    new MeleeWeapon(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            10,
                            "Sword"
                    );

            case ARMOR ->
                    new BodyArmor(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            5,
                            "Iron"
                    );

            case CONSUMABLE ->
                    new Potion(
                            id,
                            name,
                            description,
                            rarity,
                            tradeValue,
                            50
                    );

            case QUEST_ITEM ->
                    new QuestItem(
                            id,
                            name,
                            description,
                            rarity,
                            "Unknown Quest"
                    );
        };
    }
}