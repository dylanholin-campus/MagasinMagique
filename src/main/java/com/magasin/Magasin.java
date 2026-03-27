package com.magasin;

/**
 * Classe principale du magasin : gère la collection d'items
 * et coordonne leurs mises à jour quotidiennes.
 * 
 * Cette classe contient le code ORIGINAL (legacy) avec ses conditions imbriquées.
 * Pour la version refactorée avec le Pattern Strategy, voir {@link MagasinRefactored}.
 */
public class Magasin {
    
    /** Tableau d'articles gérés par le magasin. */
    public Item[] items;

    /**
     * Constructeur : initialise le magasin avec ses articles.
     * 
     * @param items Tableau d'articles à gérer
     */
    public Magasin(Item[] items) {
        this.items = items;
    }

    /**
     * Met à jour la qualité et le sellIn de tous les items du magasin.
     * 
     * VERSION ORIGINALE (legacy) conservée pour le Golden Master.
     * Pour la version refactorée, voir {@link MagasinRefactored#updateQuality()}.
     * 
     * Règles métier appliquées :
     * - Article normal : qualité -1/jour, -2/jour après péremption, min 0
     * - Comté : qualité +1/jour, +2/jour après péremption, max 50
     * - Kryptonite : ne change jamais (qualité 80, sellIn fixe)
     * - Pass VIP Concert : qualité +1/jour, +2 si <= 10 jours, +3 si <= 5 jours, tombe à 0 après le concert
     */
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            // Items qui NE SONT PAS Comté ni Pass VIP : qualité diminue
            if (!items[i].name.equals("Comté")
                    && !items[i].name.equals("Pass VIP Concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Kryptonite")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                // Comté ou Pass VIP Concert : qualité augmente (max 50)
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    // Pass VIP : bonus si le concert approche
                    if (items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            // Mise à jour du sellIn (sauf Kryptonite)
            if (!items[i].name.equals("Kryptonite")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            // Gestion des items périmés (sellIn < 0)
            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Comté")) {
                    if (!items[i].name.equals("Pass VIP Concert")) {
                        // Article normal périmé : qualité diminue encore de 1
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Kryptonite")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        // Pass VIP périmé : qualité tombe à 0
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    // Comté périmé : qualité continue d'augmenter (max 50)
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
