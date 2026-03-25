package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

public class ComteUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // La qualité augmente chaque jour
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
        
        // La date de péremption diminue
        item.sellIn = item.sellIn - 1;
        
        // Après péremption, la qualité continue d'augmenter
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
