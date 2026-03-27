package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie de mise à jour pour les articles normaux.
 * 
 * Règles métier :
 * - Qualité diminue de 1 par jour (min 0)
 * - Après péremption : qualité diminue de 2 par jour
 * - Le sellIn diminue de 1 chaque jour
 */
public class NormalItemUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Qualité diminue de 1 (si encore positive)
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        
        item.sellIn = item.sellIn - 1;
        
        // Après péremption : qualité diminue encore de 1 (total -2/jour)
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
