package com.magasin;

/**
 * Interface Strategy pour la mise à jour des items.
 * 
 * Chaque type d'item (Comté, Kryptonite, Pass VIP, etc.) possède
 * sa propre implémentation avec ses règles métier spécifiques.
 * Voir le package {@code com.magasin.updaters} pour les implémentations.
 */
public interface ItemUpdater {
    
    /**
     * Met à jour la qualité et le sellIn d'un item selon ses règles métier.
     * 
     * @param item L'article à mettre à jour (modifié en place)
     */
    void update(Item item);
}
