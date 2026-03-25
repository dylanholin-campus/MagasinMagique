package com.magasin;

/**
 * Version refactorée du magasin utilisant le Pattern Strategy.
 * 
 * Comparez cette version avec Magasin.java :
 * - ORIGINAL : 60 lignes, 13 niveaux d'imbrication
 * - REFACTORÉ : 4 lignes, 0 niveau d'imbrication
 * 
 * Cette classe démontre la puissance du Pattern Strategy !
 */
public class MagasinRefactored {
    
    /** 
     * Tableau d'items : même structure que la version originale
     */
    public Item[] items;

    /**
     * Constructeur : identique à la version originale
     * 
     * @param items Tableau d'articles à gérer
     */
    public MagasinRefactored(Item[] items) {
        this.items = items;
    }

    /**
     * VERSION REFACTORÉE avec Pattern Strategy : 4 lignes !
     * 
     * Logique :
     * 1. Pour chaque item
     * 2. Demander à la Factory la bonne stratégie
     * 3. Appliquer la stratégie
     * 4. C'est tout ! 🎉
     */
    public void updateQuality() {
        
        // Boucle for-each : syntaxe moderne plus lisible
        // for (Type variable : collection) : parcourt chaque élément
        for (Item item : items) {
            
            // Étape 1 : Obtenir la stratégie adaptée à cet item
            // ItemUpdaterFactory.getUpdater(item) : appel méthode statique
            // La Factory analyse le nom et retourne la bonne implémentation
            ItemUpdater updater = ItemUpdaterFactory.getUpdater(item);
            
            // Étape 2 : Appliquer la stratégie
            // Polymorphisme : updater peut être n'importe quelle implémentation
            // mais on appelle toujours la même méthode update()
            updater.update(item);
            
            // Fin de la logique ! Plus besoin de 13 niveaux de if/else !
        }
    }
}
