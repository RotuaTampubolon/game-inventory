package com.gameinventory.model;

public class QuestItem extends Item
        implements Tradeable {

    private String questName;

    public QuestItem(String id,
                     String name,
                     String description,
                     Rarity rarity,
                     String questName) {

        super(
                id,
                name,
                description,
                rarity,
                ItemType.QUEST_ITEM,
                0
        );

        this.questName = questName;
    }

    public String getQuestName() {
        return questName;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public String getInfo() {

        return String.format(
                "%s | Quest Item | Quest: %s | Cannot be traded",
                getRarity().getLabel(),
                questName
        );
    }
}