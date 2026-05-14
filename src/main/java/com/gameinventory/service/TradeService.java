package com.gameinventory.service;

import com.gameinventory.exception.InsufficientFundsException;
import com.gameinventory.exception.InventoryFullException;
import com.gameinventory.mapper.PlayerMapper;
import com.gameinventory.model.Item;
import com.gameinventory.model.Player;
import com.gameinventory.model.Tradeable;

public class TradeService {

    private final PlayerMapper playerMapper;

    public TradeService() {
        this.playerMapper = new PlayerMapper();
    }

    // Player beli item dari toko
    public void buyItem(Player player, Item item)
            throws InsufficientFundsException, InventoryFullException {

        if (player.isInventoryFull()) {
            throw new InventoryFullException(player.getMaxCapacity());
        }

        int harga = item.getTradeValue();
        if (player.getGold() < harga) {
            throw new InsufficientFundsException(harga, player.getGold());
        }

        player.setGold(player.getGold() - harga);
        player.addToInventory(item);
        playerMapper.update(player);

        System.out.printf("✅ %s berhasil dibeli! Gold tersisa: %d%n",
                item.getName(), player.getGold());
    }

    // Player jual item ke toko
    public void sellItem(Player player, Item item)
            throws Exception {

        if (!(item instanceof Tradeable tradeable)) {
            throw new Exception("❌ " + item.getName() + " tidak bisa dijual.");
        }

        if (!tradeable.isTradeable()) {
            throw new Exception("❌ " + item.getName() + " tidak bisa diperjualbelikan.");
        }

        int nilaiJual = item.getTradeValue() / 2; // jual setengah harga
        player.setGold(player.getGold() + nilaiJual);
        player.removeFromInventory(item);
        playerMapper.update(player);

        System.out.printf("✅ %s berhasil dijual seharga %d gold! Gold sekarang: %d%n",
                item.getName(), nilaiJual, player.getGold());
    }

    // Trade item antar dua player
    public void tradeItems(Player sender, Item senderItem,
                           Player receiver, Item receiverItem)
            throws Exception {

        if (!(senderItem instanceof Tradeable t1) || !t1.isTradeable()) {
            throw new Exception("❌ " + senderItem.getName() + " tidak bisa diperdagangkan.");
        }
        if (!(receiverItem instanceof Tradeable t2) || !t2.isTradeable()) {
            throw new Exception("❌ " + receiverItem.getName() + " tidak bisa diperdagangkan.");
        }

        sender.removeFromInventory(senderItem);
        sender.addToInventory(receiverItem);

        receiver.removeFromInventory(receiverItem);
        receiver.addToInventory(senderItem);

        playerMapper.update(sender);
        playerMapper.update(receiver);

        System.out.printf("🔄 Trade berhasil! %s memberikan '%s' dan mendapat '%s'%n",
                sender.getName(), senderItem.getName(), receiverItem.getName());
    }
}