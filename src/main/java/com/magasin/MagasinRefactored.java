package com.magasin;

/**
 * Version refactorée du magasin utilisant le Pattern Strategy.
 * 
 * Chaque type d'item a sa propre stratégie de mise à jour (voir package updaters/).
 * La Factory {@link ItemUpdaterFactory} sélectionne la bonne stratégie selon le nom de l'item.
 * 
 * Pour le code original (legacy), voir {@link Magasin}.
 */
public class MagasinRefactored {
    
    /** Tableau d'articles gérés par le magasin. */
    public Item[] items;

    /**
     * Constructeur : initialise le magasin avec ses articles.
     * 
     * @param items Tableau d'articles à gérer
     */
    public MagasinRefactored(Item[] items) {
        this.items = items;
    }

    /**
     * Met à jour la qualité et le sellIn de tous les items du magasin.
     * 
     * Utilise le Pattern Strategy : chaque type d'item a son propre algorithme
     * de mise à jour, sélectionné automatiquement par la Factory.
     */
    public void updateQuality() {
        for (Item item : items) {
            // La Factory retourne la stratégie adaptée au type d'item
            ItemUpdater updater = ItemUpdaterFactory.getUpdater(item);
            updater.update(item);
        }
    }
}
