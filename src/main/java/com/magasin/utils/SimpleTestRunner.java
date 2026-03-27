package com.magasin.utils;

import com.magasin.Item;
import com.magasin.Magasin;
import com.magasin.MagasinRefactored;

public class SimpleTestRunner {
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== LANCEMENT DES TESTS UNITAIRES ===\n");
        
        // Tests existants
        testNormalItem();
        testComteItem();
        testKryptoniteItem();
        testPassVIPItem();
        
        // Nouvelle fonctionnalité
        testPouvoirsMagiquesItem();
        
        // Tests edge cases
        testQualityBounds();
        testExpiredItems();
        
        System.out.println("\n=== RÉSULTATS ===");
        System.out.println("Tests réussis : " + testsPassed);
        System.out.println("Tests échoués : " + testsFailed);
        System.out.println("Total : " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("✅ TOUS LES TESTS SONT PASSÉS !");
        } else {
            System.out.println("❌ CERTAINS TESTS ONT ÉCHOUÉ !");
        }
    }
    
    private static void testNormalItem() {
        System.out.println("--- Test Article Normal ---");
        
        Item[] items = new Item[] { new Item("Article normal", 2, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        
        assertEquals("Article normal", app.items[0].name, "Nom inchangé");
        assertEquals(1, app.items[0].sellIn, "SellIn diminué de 1");
        assertEquals(19, app.items[0].quality, "Quality diminué de 1");
        
        System.out.println("✅ Article normal : OK\n");
    }
    
    private static void testComteItem() {
        System.out.println("--- Test Comté ---");
        
        Item[] items = new Item[] { new Item("Comté", 2, 0) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        
        assertEquals("Comté", app.items[0].name, "Nom inchangé");
        assertEquals(1, app.items[0].sellIn, "SellIn diminué de 1");
        assertEquals(1, app.items[0].quality, "Quality augmenté de 1");
        
        System.out.println("✅ Comté : OK\n");
    }
    
    private static void testKryptoniteItem() {
        System.out.println("--- Test Kryptonite ---");
        
        Item[] items = new Item[] { new Item("Kryptonite", 0, 80) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        
        assertEquals("Kryptonite", app.items[0].name, "Nom inchangé");
        assertEquals(0, app.items[0].sellIn, "SellIn inchangé");
        assertEquals(80, app.items[0].quality, "Quality inchangé");
        
        System.out.println("✅ Kryptonite : OK\n");
    }
    
    private static void testPassVIPItem() {
        System.out.println("--- Test Pass VIP Concert ---");
        
        Item[] items = new Item[] { new Item("Pass VIP Concert", 15, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        
        assertEquals("Pass VIP Concert", app.items[0].name, "Nom inchangé");
        assertEquals(14, app.items[0].sellIn, "SellIn diminué de 1");
        assertEquals(21, app.items[0].quality, "Quality augmenté de 1");
        
        System.out.println("✅ Pass VIP Concert : OK\n");
    }
    
    private static void testPouvoirsMagiquesItem() {
        System.out.println("--- Test Pouvoirs Magiques ---");
        
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 5, 20) };
        MagasinRefactored app = new MagasinRefactored(items);
        app.updateQuality();
        
        assertEquals("Pouvoirs magiques", app.items[0].name, "Nom inchangé");
        assertEquals(4, app.items[0].sellIn, "SellIn diminué de 1");
        assertEquals(18, app.items[0].quality, "Quality diminué de 2 (20→18)");
        
        System.out.println("✅ Pouvoirs Magiques : OK\n");
    }
    
    private static void testQualityBounds() {
        System.out.println("--- Test Limites de Qualité ---");
        
        // Test qualité max (50)
        Item[] items1 = new Item[] { new Item("Comté", 2, 50) };
        Magasin app1 = new Magasin(items1);
        app1.updateQuality();
        assertEquals(50, app1.items[0].quality, "Qualité ne dépasse pas 50");
        
        // Test qualité min (0)
        Item[] items2 = new Item[] { new Item("Article normal", 2, 0) };
        Magasin app2 = new Magasin(items2);
        app2.updateQuality();
        assertEquals(0, app2.items[0].quality, "Qualité ne devient pas négative");
        
        System.out.println("✅ Limites de Qualité : OK\n");
    }
    
    private static void testExpiredItems() {
        System.out.println("--- Test Articles Périmés ---");
        
        // Article normal périmé
        Item[] items1 = new Item[] { new Item("Article normal", 0, 20) };
        Magasin app1 = new Magasin(items1);
        app1.updateQuality();
        assertEquals(18, app1.items[0].quality, "Qualité diminue 2x plus vite après péremption");
        
        // Comté périmé
        Item[] items2 = new Item[] { new Item("Comté", -1, 0) };
        Magasin app2 = new Magasin(items2);
        app2.updateQuality();
        assertEquals(2, app2.items[0].quality, "Comté continue d'améliorer après péremption");
        
        System.out.println("✅ Articles Périmés : OK\n");
    }
    
    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected.equals(actual)) {
            testsPassed++;
        } else {
            testsFailed++;
            System.out.println("❌ ÉCHEC : " + message);
            System.out.println("   Attendu : " + expected);
            System.out.println("   Obtenu : " + actual);
        }
    }
    
    private static void assertEquals(int expected, int actual, String message) {
        if (expected == actual) {
            testsPassed++;
        } else {
            testsFailed++;
            System.out.println("❌ ÉCHEC : " + message);
            System.out.println("   Attendu : " + expected);
            System.out.println("   Obtenu : " + actual);
        }
    }
}
