package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie de mise à jour pour le "Comté".
 * 
 * Règles métier :
 * - La qualité augmente de 1 par jour (max 50)
 * - Après péremption : la qualité augmente de 2 par jour (max 50)
 * - Le sellIn diminue de 1 chaque jour
 */
public class ComteUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Qualité augmente chaque jour (max 50)
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
        
        item.sellIn = item.sellIn - 1;
        
        // Après péremption, qualité augmente encore de 1
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
