package com.magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour vérifier la préservation du comportement legacy
 * et le bon fonctionnement du refactoring Pattern Strategy.
 */
class MagasinTest {

    // === Tests du comportement ORIGINAL (Magasin.java) ===

    @Test
    void articleNormal_qualiteEtSellInDiminuent() {
        Item[] items = new Item[] { new Item("Article normal", 10, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void articleNormal_apresPeremption_qualiteDiminueDeuxFois() {
        Item[] items = new Item[] { new Item("Article normal", 0, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void articleNormal_qualiteJamaisNegative() {
        Item[] items = new Item[] { new Item("Article normal", 5, 0) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void comte_qualiteAugmente() {
        Item[] items = new Item[] { new Item("Comté", 2, 10) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void comte_apresPeremption_qualiteAugmenteDeuxFois() {
        Item[] items = new Item[] { new Item("Comté", -1, 10) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }

    @Test
    void comte_qualiteJamaisAuDessus50() {
        Item[] items = new Item[] { new Item("Comté", 5, 50) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void kryptonite_jamaisModifie() {
        Item[] items = new Item[] { new Item("Kryptonite", 0, 80) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void passVIP_qualiteAugmente() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 15, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void passVIP_augmenteDe2_quand10JoursOuMoins() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 10, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void passVIP_augmenteDe3_quand5JoursOuMoins() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 5, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void passVIP_qualiteTombeA0_apresConcert() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 0, 20) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    // === Tests de COMPARAISON : Original vs Refactoré ===

    @Test
    void comparaison_articleNormal_identique() {
        Item[] items1 = new Item[] { new Item("Article normal", 10, 20) };
        Item[] items2 = new Item[] { new Item("Article normal", 10, 20) };
        new Magasin(items1).updateQuality();
        new MagasinRefactored(items2).updateQuality();
        assertEquals(items1[0].sellIn, items2[0].sellIn);
        assertEquals(items1[0].quality, items2[0].quality);
    }

    @Test
    void comparaison_comte_identique() {
        Item[] items1 = new Item[] { new Item("Comté", 2, 10) };
        Item[] items2 = new Item[] { new Item("Comté", 2, 10) };
        new Magasin(items1).updateQuality();
        new MagasinRefactored(items2).updateQuality();
        assertEquals(items1[0].sellIn, items2[0].sellIn);
        assertEquals(items1[0].quality, items2[0].quality);
    }

    @Test
    void comparaison_kryptonite_identique() {
        Item[] items1 = new Item[] { new Item("Kryptonite", 0, 80) };
        Item[] items2 = new Item[] { new Item("Kryptonite", 0, 80) };
        new Magasin(items1).updateQuality();
        new MagasinRefactored(items2).updateQuality();
        assertEquals(items1[0].sellIn, items2[0].sellIn);
        assertEquals(items1[0].quality, items2[0].quality);
    }

    @Test
    void comparaison_passVIP_identique() {
        Item[] items1 = new Item[] { new Item("Pass VIP Concert", 5, 20) };
        Item[] items2 = new Item[] { new Item("Pass VIP Concert", 5, 20) };
        new Magasin(items1).updateQuality();
        new MagasinRefactored(items2).updateQuality();
        assertEquals(items1[0].sellIn, items2[0].sellIn);
        assertEquals(items1[0].quality, items2[0].quality);
    }

    // === Test nouvelle fonctionnalité : Pouvoirs magiques ===

    @Test
    void pouvoirsMagiques_qualiteDiminueDe2() {
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 5, 20) };
        MagasinRefactored app = new MagasinRefactored(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void pouvoirsMagiques_apresPeremption_qualiteDiminueDe4() {
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 0, 20) };
        MagasinRefactored app = new MagasinRefactored(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    @Test
    void pouvoirsMagiques_qualiteJamaisNegative() {
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 5, 1) };
        MagasinRefactored app = new MagasinRefactored(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertTrue(app.items[0].quality >= 0);
    }
}
