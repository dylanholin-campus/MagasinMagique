package com.magasin;

import com.magasin.updaters.*;

/**
 * Factory qui retourne la bonne stratégie de mise à jour
 * selon le type d'item.
 * 
 * Pour ajouter un nouveau type d'item :
 * 1. Créer une classe dans le package updaters/ qui implémente {@link ItemUpdater}
 * 2. Ajouter un case dans le switch ci-dessous
 */
public class ItemUpdaterFactory {
    
    /**
     * Retourne la stratégie de mise à jour adaptée au type d'item.
     * 
     * @param item L'article pour lequel on veut la stratégie
     * @return L'implémentation de {@link ItemUpdater} adaptée à cet item
     */
    public static ItemUpdater getUpdater(Item item) {
        switch (item.name) {
            case "Comté":
                return new ComteUpdater();
            case "Kryptonite":
                return new KryptoniteUpdater();
            case "Pass VIP Concert":
                return new PassVIPUpdater();
            case "Pouvoirs magiques":
                return new PouvoirsMagiquesUpdater();
            default:
                return new NormalItemUpdater();
        }
    }
}
