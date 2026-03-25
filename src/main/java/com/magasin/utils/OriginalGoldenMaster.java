package com.magasin.utils;

import com.magasin.Item;
import com.magasin.Magasin;

public class OriginalGoldenMaster {
    public static void main(String[] args) {
        // Configuration des items de test selon les règles métier
        Item[] items = new Item[] {
            new Item("Comté", 2, 0),
            new Item("Comté", -1, 0),
            new Item("Comté", 2, 49),
            new Item("Pass VIP Concert", 15, 20),
            new Item("Pass VIP Concert", 10, 20),
            new Item("Pass VIP Concert", 5, 20),
            new Item("Pass VIP Concert", 0, 20),
            new Item("Kryptonite", 0, 80),
            new Item("Article normal", 2, 20),
            new Item("Article normal", 0, 20),
            new Item("Article normal", -1, 20),
            new Item("Article normal", 2, 0)
        };

        Magasin app = new Magasin(items);
        
        System.out.println("=== CODE ORIGINAL - ÉTAT INITIAL ===");
        for (Item item : app.items) {
            System.out.println(item);
        }
        
        // Exécuter la mise à jour
        app.updateQuality();
        
        System.out.println("\n=== CODE ORIGINAL - ÉTAT APRÈS MISE À JOUR ===");
        for (Item item : app.items) {
            System.out.println(item);
        }
    }
}
