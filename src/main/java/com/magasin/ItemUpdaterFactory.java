package com.magasin;

import com.magasin.updaters.*;

/**
 * Factory Pattern : Crée la bonne stratégie de mise à jour
 * selon le type d'item.
 * 
 * Une Factory est une classe dont le rôle est de créer d'autres objets.
 * Elle encapsule la logique de création et rend le code plus flexible.
 */
public class ItemUpdaterFactory {
    
    /**
     * Méthode statique : peut être appelée sans créer d'objet Factory
     * Syntaxe : NomClasse.nomMethode()
     * 
     * Logique : Selon le nom de l'item, retourne la stratégie appropriée
     * 
     * @param item L'article pour lequel on veut la stratégie de mise à jour
     * @return La stratégie (implémentation de ItemUpdater) adaptée à cet item
     */
    public static ItemUpdater getUpdater(Item item) {
        
        // Switch : structure de contrôle qui teste une variable
        // contre plusieurs valeurs possibles (plus lisible que if/else multiples)
        switch (item.name) {
            
            // case "valeur" : si item.name equals "Comté"
            case "Comté":
                return new ComteUpdater();  // Crée un nouvel objet ComteUpdater
                
            case "Kryptonite":
                return new KryptoniteUpdater();
                
            case "Pass VIP Concert":
                return new PassVIPUpdater();
                
            case "Pouvoirs magiques":
                return new PouvoirsMagiquesUpdater();  // Nouvelle fonctionnalité !
                
            // default : si aucun case ne correspond (items normaux)
            default:
                return new NormalItemUpdater();
        }
    }
}
