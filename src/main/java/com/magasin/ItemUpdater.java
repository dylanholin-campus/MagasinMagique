package com.magasin;

/**
 * Interface Strategy du Pattern Strategy.
 * 
 * Une interface en Java est un "contrat" qui définit les méthodes
 * que toutes les classes implémentant cette interface doivent fournir.
 * 
 * Pattern Strategy : Permet de définir une famille d'algorithmes
 * interchangeables pour mettre à jour différents types d'items.
 */
public interface ItemUpdater {
    
    /**
     * Méthode abstraite que toutes les stratégies doivent implémenter.
     * 
     * "abstract" signifie : pas d'implémentation ici, obligatoire
     * dans les classes concrètes qui implémentent cette interface.
     * 
     * Logique métier : chaque type d'item a sa propre façon de
     * mettre à jour sa qualité et sa date de péremption.
     * 
     * @param item L'article à mettre à jour (modifié en place)
     */
    void update(Item item);
}
