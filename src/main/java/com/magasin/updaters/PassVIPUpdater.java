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
        // Étape 1 : Augmentation de la qualité avant le concert
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            // Bonus : +1 supplémentaire si 10 jours ou moins
            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            // Bonus : +1 supplémentaire si 5 jours ou moins
            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        // Étape 2 : Mise à jour du sellIn
        item.sellIn = item.sellIn - 1;

        // Étape 3 : Après le concert, qualité tombe à 0
        if (item.sellIn < 0) {
            item.quality = item.quality - item.quality;
        }
    }
}
