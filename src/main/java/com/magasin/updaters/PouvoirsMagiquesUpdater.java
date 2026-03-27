package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie de mise à jour pour les "Pouvoirs magiques".
 * 
 * Règles métier :
 * - Qualité diminue de 2 par jour (min 0)
 * - Après péremption : qualité diminue de 4 par jour
 * - Le sellIn diminue de 1 chaque jour
 * 
 * Exemple : (5,20) → (4,18) → (3,16) → (2,14)...
 */
public class PouvoirsMagiquesUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Qualité diminue de 2 (dégradation 2x plus rapide que normal)
        if (item.quality > 0) {
            item.quality = item.quality - 2;
        }
        
        item.sellIn = item.sellIn - 1;
        
        // Après péremption : qualité diminue encore de 2 (total -4/jour)
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 2;
        }
        
        // Qualité ne peut pas être négative
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
