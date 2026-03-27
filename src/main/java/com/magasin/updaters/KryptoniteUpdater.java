package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie de mise à jour pour la "Kryptonite".
 * 
 * Règles métier :
 * - La qualité reste fixe à 80
 * - Le sellIn ne change jamais
 * - Aucune modification n'est appliquée
 */
public class KryptoniteUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Kryptonite : aucune modification (qualité et sellIn fixes)
    }
}
