package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie de mise à jour pour "Pass VIP Concert".
 * 
 * Règles spécifiques :
 * - La qualité augmente à mesure que le concert approche
 * - +1 par jour normalement
 * - +2 par jour quand sellIn <= 10
 * - +3 par jour quand sellIn <= 5
 * - Qualité tombe à 0 après le concert (sellIn < 0)
 * - Qualité ne dépasse jamais 50
 */
public class PassVIPUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Qualité augmente (max 50) avec bonus selon la proximité du concert
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            // +1 bonus si 10 jours ou moins
            if (item.sellIn < 11 && item.quality < 50) {
                item.quality = item.quality + 1;
            }

            // +1 bonus si 5 jours ou moins
            if (item.sellIn < 6 && item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }

        item.sellIn = item.sellIn - 1;

        // Après le concert : qualité tombe à 0
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
