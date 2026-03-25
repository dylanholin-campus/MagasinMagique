package com.magasin.utils;

import com.magasin.Item;
import com.magasin.MagasinRefactored;

public class TestPouvoirsMagiques {
    public static void main(String[] args) {
        // Test de la nouvelle fonctionnalité "Pouvoirs magiques"
        Item[] items = new Item[] {
            new Item("Pouvoirs magiques", 5, 20),
            new Item("Pouvoirs magiques", 0, 20),
            new Item("Pouvoirs magiques", -1, 20),
            new Item("Pouvoirs magiques", 5, 1),
            new Item("Pouvoirs magiques", 5, 0)
        };

        MagasinRefactored app = new MagasinRefactored(items);
        
        System.out.println("=== TEST POUVOIRS MAGIQUES ===");
        
        System.out.println("\n=== ÉTAT INITIAL ===");
        for (Item item : app.items) {
            System.out.println(item);
        }
        
        app.updateQuality();
        
        System.out.println("\n=== ÉTAT APRÈS MISE À JOUR ===");
        for (Item item : app.items) {
            System.out.println(item);
        }
        
        System.out.println("\n=== VÉRIFICATION ===");
        System.out.println("Pouvoirs magiques (5,20) → (4,18) : qualité -2");
        System.out.println("Pouvoirs magiques (0,20) → (-1,16) : qualité -4 (périmé)");
        System.out.println("Pouvoirs magiques (-1,20) → (-2,16) : qualité -4 (déjà périmé)");
        System.out.println("Pouvoirs magiques (5,1) → (4,0) : qualité -1 (pas négatif)");
        System.out.println("Pouvoirs magiques (5,0) → (4,0) : qualité reste 0");
    }
}
