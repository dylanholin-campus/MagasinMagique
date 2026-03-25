package com.magasin;

/**
 * Classe représentant un article dans le magasin magique.
 * 
 * Cette classe est un simple "POJO" (Plain Old Java Object) qui contient
 * les informations de base d'un article. Elle ne doit PAS être modifiée
 * selon les règles du projet !
 */
public class Item {

    /** 
     * Nom de l'article (ex: "Comté", "Kryptonite", "Pass VIP Concert")
     * - public : accessible depuis n'importe quelle classe
     * - String : chaîne de caractères pour stocker le nom
     */
    public String name;

    /** 
     * Nombre de jours restants avant péremption
     * - public : accessible depuis n'importe quelle classe
     * - int : nombre entier (peut être négatif si périmé)
     */
    public int sellIn;

    /** 
     * Qualité de l'article (0 à 50, sauf Kryptonite = 80)
     * - public : accessible depuis n'importe quelle classe
     * - int : nombre entier représentant la valeur/précision
     */
    public int quality;

    /**
     * Constructeur : méthode appelée lors de la création d'un nouvel Item
     * 
     * @param name Le nom de l'article
     * @param sellIn Les jours restants avant péremption
     * @param quality La qualité actuelle de l'article
     * 
     * Syntaxe Java :
     * - public : visible de partout
     * - Item() : même nom que la classe = constructeur
     * - this.name : fait référence à l'attribut de l'objet (pas au paramètre)
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;        // this.name = attribut de l'objet
        this.sellIn = sellIn;   // sellIn = paramètre du constructeur
        this.quality = quality; // this.quality = attribut de l'objet
    }

    /**
     * Méthode toString() : convertit l'objet en chaîne de caractères
     * 
     * @Override : annotation indiquant qu'on redéfinit une méthode héritée
     *            de la classe Object (toutes les classes Java héritent de Object)
     * 
     * @return Représentation textuelle de l'item au format "nom, sellIn, quality"
     */
    @Override
    public String toString() {
        // Concaténation de chaînes avec l'opérateur +
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
