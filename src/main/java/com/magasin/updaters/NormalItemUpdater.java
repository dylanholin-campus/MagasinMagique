package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

/**
 * Stratégie concrète pour les items "normaux" du magasin.
 * 
 * Cette classe implémente l'interface ItemUpdater :
 * - implements : mot-clé Java pour implémenter une interface
 * - Obligation de fournir une implémentation pour toutes les méthodes abstraites
 * 
 * Règles métier pour les items normaux :
 * - Qualité diminue de 1 par jour
 * - Après péremption : diminue 2x plus vite
 * - Qualité jamais négative
 * - sellIn diminue chaque jour
 */
public class NormalItemUpdater implements ItemUpdater {
    
    /**
     * Implémentation de la méthode update() de l'interface ItemUpdater.
     * 
     * @Override : annotation qui vérifie qu'on redéfinit bien une méthode
     *            de l'interface (erreur de compilation si ce n'est pas le cas)
     * 
     * @param item L'item à mettre à jour (modifié directement, pas de retour)
     */
    @Override
    public void update(Item item) {
        
        // Étape 1 : Dégradation normale de la qualité
        // Condition : si la qualité est strictement supérieure à 0
        // > 0 : opérateur de comparaison "plus grand que"
        if (item.quality > 0) {
            // Action : diminuer la qualité de 1
            // item.quality = item.quality - 1 : affectation avec calcul
            item.quality = item.quality - 1;
        }
        
        // Étape 2 : Mise à jour de la date de péremption
        // Toujours exécutée (pas de condition)
        // sellIn diminue de 1 chaque jour pour tous les items sauf Kryptonite
        item.sellIn = item.sellIn - 1;
        
        // Étape 3 : Dégradation accélérée après péremption
        // Condition composée avec && (ET logique) :
        // - item.sellIn < 0 : si l'item est périmé
        // - && : ET
        // - item.quality > 0 : ET si la qualité est encore positive
        if (item.sellIn < 0 && item.quality > 0) {
            // Action : diminuer encore la qualité de 1 (total = -2 pour les items périmés)
            item.quality = item.quality - 1;
        }
        
        // Étape 4 : Protection contre les qualités négatives
        // Condition : si la qualité est devenue négative
        // < 0 : opérateur "plus petit que"
        if (item.quality < 0) {
            // Action : forcer la qualité à 0 (règle métier)
            item.quality = 0;
        }
        
        // Fin de la mise à jour pour un item normal !
        // Logique simple, lisible, et testable unitairement.
    }
}
