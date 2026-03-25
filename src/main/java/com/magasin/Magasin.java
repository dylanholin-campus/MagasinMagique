package com.magasin;

/**
 * Classe principale du magasin : gère la collection d'items
 * et coordonne leurs mises à jour quotidiennes.
 * 
 * Cette classe représente le "Context" dans le Pattern Strategy :
 * elle utilise les stratégies sans connaître leur implémentation exacte.
 */
public class Magasin {
    
    /** 
     * Tableau d'items : collection d'articles du magasin
     * - Item[] : tableau d'objets Item
     * - items : nom de la variable (pluriel car collection)
     * - Pas de private/protected : package-private (visible dans le même package)
     */
    public Item[] items;

    /**
     * Constructeur : initialise le magasin avec ses articles
     * 
     * @param items Tableau d'articles à gérer
     */
    public Magasin(Item[] items) {
        this.items = items;  // this.items = attribut de l'objet
    }

    /**
     * Méthode principale : met à jour tous les items du magasin
     * 
     * VERSION ORIGINALE (legacy) : 60 lignes de conditions imbriquées
     * Cette version est conservée pour le Golden Master et la comparaison.
     */
    public void updateQuality() {
        // CODE ORIGINAL - pour comparaison avec version refactorée
        
        // Boucle for traditionnelle : parcourt tous les éléments du tableau
        // - int i = 0 : commence à l'index 0
        // - i < items.length : continue tant qu'on n'a pas tout parcouru
        // - i++ : incrémente l'index à chaque tour
        for (int i = 0; i < items.length; i++) {
            
            // Structure conditionnelle complexe imbriquée :
            // !items[i].name.equals("Comté") : si le nom N'EST PAS "Comté"
            // && : opérateur ET logique
            // items[i].name.equals("Pass VIP Concert") : ET N'EST PAS "Pass VIP Concert"
            if (!items[i].name.equals("Comté")
                    && !items[i].name.equals("Pass VIP Concert")) {
                
                // Deuxième niveau de condition : qualité > 0 ?
                if (items[i].quality > 0) {
                    
                    // Troisième niveau : pas Kryptonite ?
                    if (!items[i].name.equals("Kryptonite")) {
                        // Action : diminue la qualité de 1
                        items[i].quality = items[i].quality - 1;
                    }
                }
                
            } else {
                // Si c'est Comté OU Pass VIP Concert
                
                // Quatrième niveau : qualité < 50 ?
                if (items[i].quality < 50) {
                    // Action : augmente la qualité de 1
                    items[i].quality = items[i].quality + 1;

                    // Cas spécial pour Pass VIP Concert
                    if (items[i].name.equals("Pass VIP Concert")) {
                        
                        // Cinquième niveau : moins de 11 jours ?
                        if (items[i].sellIn < 11) {
                            
                            // Sixième niveau : qualité encore < 50 ?
                            if (items[i].quality < 50) {
                                // Action : augmente encore de 1
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        // Septième niveau : moins de 6 jours ?
                        if (items[i].sellIn < 6) {
                            
                            // Huitième niveau : qualité encore < 50 ?
                            if (items[i].quality < 50) {
                                // Action : augmente encore de 1
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            // Mise à jour du sellIn (jours restants)
            // Sauf pour Kryptonite qui n'a pas de péremption
            if (!items[i].name.equals("Kryptonite")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            // Gestion des items périmés (sellIn < 0)
            if (items[i].sellIn < 0) {
                
                // Neuvième niveau : pas Comté ?
                if (!items[i].name.equals("Comté")) {
                    
                    // Dixième niveau : pas Pass VIP ?
                    if (!items[i].name.equals("Pass VIP Concert")) {
                        
                        // Onzième niveau : qualité > 0 ?
                        if (items[i].quality > 0) {
                            
                            // Douzième niveau : pas Kryptonite ?
                            if (!items[i].name.equals("Kryptonite")) {
                                // Action : diminue qualité de 1 (dégradation 2x après péremption)
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                        
                    } else {
                        // Cas spécial : Pass VIP périmé
                        // Action : qualité tombe à 0
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                    
                } else {
                    // Cas spécial : Comté périmé
                    // Treizième niveau : qualité < 50 ?
                    if (items[i].quality < 50) {
                        // Action : augmente qualité de 1 (Comté s'améliore avec le temps !)
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
        
        // FIN DU CODE ORIGINAL - 13 niveaux d'imbrication !
        // Comparez avec la version refactorée : 4 lignes simples !
    }
}
