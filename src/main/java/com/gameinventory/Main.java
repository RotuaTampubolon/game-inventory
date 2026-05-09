package com.gameinventory;

import com.gameinventory.cli.InventoryCLI;
import com.gameinventory.service.InventoryService;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryService(20);
        InventoryCLI cli = new InventoryCLI(service);
        cli.start();
    }
}