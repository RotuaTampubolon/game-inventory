package com.gameinventory.service;

import com.gameinventory.exception.ItemNotFoundException;
import com.gameinventory.model.*;
import com.gameinventory.mapper.PlayerMapper;

public class EquipService {

    private final PlayerMapper playerMapper;

    public EquipService() {
        this.playerMapper = new PlayerMapper();
    }

    public void equip(Player player, Item item) {
        if (!(item instanceof Equippable equippable)) {
            System.out.println("⚠️  " + item.getName() + " tidak bisa diequip.");
            return;
        }

        // Cek slot — unequip dulu kalau sudah ada yang dipakai
        if (item instanceof Weapon && player.getEquippedWeapon() != null) {
            unequip(player, player.getEquippedWeapon());
        } else if (item instanceof Armor && player.getEquippedArmor() != null) {
            unequip(player, player.getEquippedArmor());
        }

        equippable.equip(player);

        // Simpan referensi item yang diequip
        if (item instanceof Weapon) player.setEquippedWeapon(item);
        else if (item instanceof Armor) player.setEquippedArmor(item);

        playerMapper.update(player);
    }

    public void unequip(Player player, Item item) {
        if (!(item instanceof Equippable equippable)) {
            System.out.println("⚠️  " + item.getName() + " tidak bisa diunequip.");
            return;
        }

        equippable.unequip(player);

        if (item instanceof Weapon) player.setEquippedWeapon(null);
        else if (item instanceof Armor) player.setEquippedArmor(null);

        playerMapper.update(player);
    }

    public void useItem(Player player, Item item) {
        if (!(item instanceof Usable usable)) {
            System.out.println("⚠️  " + item.getName() + " tidak bisa digunakan.");
            return;
        }

        usable.use(player);

        // Hapus dari inventory kalau consumed on use
        if (usable.isConsumedOnUse()) {
            player.removeFromInventory(item);
            System.out.println("🗑️  " + item.getName() + " habis digunakan.");
        }

        playerMapper.update(player);
    }

    public void showEquippedItems(Player player) {
        System.out.println("\n⚔️  Equipped Weapon : " +
                (player.getEquippedWeapon() != null
                        ? player.getEquippedWeapon().getName()
                        : "None"));
        System.out.println("🛡️  Equipped Armor  : " +
                (player.getEquippedArmor() != null
                        ? player.getEquippedArmor().getName()
                        : "None"));
    }
}