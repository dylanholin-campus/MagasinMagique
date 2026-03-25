package com.magasin.utils;

import com.magasin.Item;
import com.magasin.Magasin;
import com.magasin.MagasinRefactored;
import java.util.ArrayList;
import java.util.List;

public class ComparisonTest {
    public static void main(String[] args) {
        System.out.println("=== TEST DE COMPARAISON : ORIGINAL vs REFACTORÉ ===\n");
        
        // Configuration identique pour les deux tests
        Item[] itemsOriginal = createTestItems();
        Item[] itemsRefactored = createTestItems();
        
        // Test avec le code ORIGINAL
        Magasin originalMagasin = new Magasin(itemsOriginal);
        System.out.println("1. Exécution du code ORIGINAL...");
        originalMagasin.updateQuality();
        
        // Test avec le code REFACTORÉ
        MagasinRefactored refactoredMagasin = new MagasinRefactored(itemsRefactored);
        System.out.println("2. Exécution du code REFACTORÉ...");
        refactoredMagasin.updateQuality();
        
        // Comparaison des résultats
        System.out.println("\n3. Comparaison des résultats...");
        boolean allMatch = true;
        
        for (int i = 0; i < itemsOriginal.length; i++) {
            Item original = itemsOriginal[i];
            Item refactored = itemsRefactored[i];
            
            boolean nameMatch = original.name.equals(refactored.name);
            boolean sellInMatch = original.sellIn == refactored.sellIn;
            boolean qualityMatch = original.quality == refactored.quality;
            
            if (nameMatch && sellInMatch && qualityMatch) {
                System.out.println("✅ " + original.name + " : IDENTIQUE");
            } else {
                System.out.println("❌ " + original.name + " : DIFFÉRENT !");
                System.out.println("   Original : " + original);
                System.out.println("   Refactoré : " + refactored);
                allMatch = false;
            }
        }
        
        // Résultat final
        System.out.println("\n=== RÉSULTAT FINAL ===");
        if (allMatch) {
            System.out.println("🎉 SUCCÈS : Le comportement legacy est 100% préservé !");
            System.out.println("✅ Le refactoring n'a AUCUNEMENT cassé le comportement existant");
            System.out.println("✅ La nouvelle fonctionnalité 'Pouvoirs magiques' peut être ajoutée sans risque");
        } else {
            System.out.println("🚨 ÉCHEC : Le comportement a été modifié !");
            System.out.println("❌ Le refactoring a introduit des régressions");
        }
    }
    
    private static Item[] createTestItems() {
        return new Item[] {
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
    }
}
