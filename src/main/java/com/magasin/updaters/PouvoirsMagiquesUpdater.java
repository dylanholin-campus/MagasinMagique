package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie concrète pour les "Pouvoirs magiques" - NOUVELLE FONCTIONNALITÉ !
 * 
 * Cette classe démontre comment ajouter facilement un nouveau type d'item
 * avec le Pattern Strategy, sans modifier le code existant.
 * 
 * Règles métier spécifiques aux "Pouvoirs magiques" :
 * - Qualité se dégrade 2x plus vite que les items normaux
 * - Après péremption : dégrade 4x plus vite (2x normal + 2x périmé)
 * - Qualité jamais négative
 * - sellIn diminue normalement
 * 
 * Exemple : (5,20) → (4,18) → (3,16) → (2,14)...
 */
public class PouvoirsMagiquesUpdater implements ItemUpdater {
    
    /**
     * Implémentation de la mise à jour pour les "Pouvoirs magiques".
     * 
     * @Override : garantie qu'on implémente bien la méthode de l'interface
     * 
     * @param item L'item "Pouvoirs magiques" à mettre à jour
     */
    @Override
    public void update(Item item) {
        
        // Étape 1 : Dégradation accélérée (2x plus vite)
        // Condition : qualité positive ?
        if (item.quality > 0) {
            // Action : diminuer la qualité de 2 (au lieu de 1 pour les items normaux)
            // Logique : les pouvoirs magiques se dissipent plus rapidement
            item.quality = item.quality - 2;
        }
        
        // Étape 2 : Mise à jour normale du sellIn
        // Identique aux autres items (sauf Kryptonite)
        item.sellIn = item.sellIn - 1;
        
        // Étape 3 : Dégradation ULTRA-accélérée après péremption
        // Condition composée : item périmé ET qualité encore positive
        if (item.sellIn < 0 && item.quality > 0) {
            // Action : diminuer encore la qualité de 2
            // Total pour items périmés : -4 par jour (2 + 2)
            // Logique : les pouvoirs magiques périmés sont très instables !
            item.quality = item.quality - 2;
        }
        
        // Étape 4 : Protection contre les qualités négatives
        // Règle universelle : aucun item ne peut avoir une qualité négative
        if (item.quality < 0) {
            // Action : forcer à 0
            item.quality = 0;
        }
        
        // Fin de la mise à jour pour les "Pouvoirs magiques" !
        // 
        // AVANTAGES du Pattern Strategy démontrés ici :
        // ✅ Ajout de fonctionnalité SANS modifier le code existant
        // ✅ Logique isolée et testable unitairement  
        // ✅ Facile à comprendre et maintenir
        // ✅ Respect du principe Open/Closed (ouvert pour extension, fermé pour modification)
    }
}
