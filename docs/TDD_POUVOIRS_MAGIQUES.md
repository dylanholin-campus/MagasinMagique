# 🧪 Démarche TDD : Ajout de "Pouvoirs magiques"

## Qu'est-ce que le TDD ?

Le TDD (Test-Driven Development) consiste à écrire les tests **avant** le code.
Le cycle se répète en 3 étapes :

```
🔴 RED    → Écrire un test qui échoue
🟢 GREEN  → Écrire le code minimum pour que le test passe
🔵 REFACTOR → Nettoyer le code sans casser les tests
```

---

## Phase 1 : 🔴 RED — Écriture des tests

On commence par définir le comportement attendu de "Pouvoirs magiques" à partir des règles métier :

> Les éléments "Pouvoirs magiques" voient leur qualité se dégrader deux fois plus vite que les objets normaux.

### Tests écrits (avant le code)

**Test 1 : Qualité diminue de 2 par jour**
```java
@Test
void pouvoirsMagiques_qualiteDiminueDe2() {
    Item[] items = new Item[] { new Item("Pouvoirs magiques", 5, 20) };
    MagasinRefactored app = new MagasinRefactored(items);
    app.updateQuality();
    assertEquals(4, app.items[0].sellIn);  // sellIn normal
    assertEquals(18, app.items[0].quality); // -2 au lieu de -1
}
```

Raisonnement : un item normal perd 1 de qualité par jour. "Pouvoirs magiques" doit perdre 2 (le double).

**Test 2 : Après péremption, qualité diminue de 4**
```java
@Test
void pouvoirsMagiques_apresPeremption_qualiteDiminueDe4() {
    Item[] items = new Item[] { new Item("Pouvoirs magiques", 0, 20) };
    MagasinRefactored app = new MagasinRefactored(items);
    app.updateQuality();
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(16, app.items[0].quality); // -4 (2x normal périmé)
}
```

Raisonnement : un item normal périmé perd 2 de qualité. "Pouvoirs magiques" périmé doit perdre 4 (le double).

**Test 3 : Qualité jamais négative**
```java
@Test
void pouvoirsMagiques_qualiteJamaisNegative() {
    Item[] items = new Item[] { new Item("Pouvoirs magiques", 5, 1) };
    MagasinRefactored app = new MagasinRefactored(items);
    app.updateQuality();
    assertEquals(4, app.items[0].sellIn);
    assertTrue(app.items[0].quality >= 0); // jamais négatif
}
```

Raisonnement : règle universelle du système, la qualité ne descend jamais en dessous de 0.

### Résultat à ce stade

Les 3 tests **échouent** car `MagasinRefactored` ne connaît pas encore "Pouvoirs magiques". L'item est traité comme un article normal (qualité -1 au lieu de -2).

---

## Phase 2 : 🟢 GREEN — Implémentation minimale

### Étape 1 : Créer la stratégie

Fichier : `src/main/java/com/magasin/updaters/PouvoirsMagiquesUpdater.java`

```java
public class PouvoirsMagiquesUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Dégradation 2x plus vite
        if (item.quality > 0) {
            item.quality = item.quality - 2;
        }

        // sellIn diminue normalement
        item.sellIn = item.sellIn - 1;

        // Après péremption : encore -2 (total -4)
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 2;
        }

        // Qualité jamais négative
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}
```

### Étape 2 : Enregistrer dans la Factory

Fichier : `src/main/java/com/magasin/ItemUpdaterFactory.java`

```java
case "Pouvoirs magiques":
    return new PouvoirsMagiquesUpdater();
```

### Résultat

Les 3 tests **passent** :
```
pouvoirsMagiques_qualiteDiminueDe2()              ✅ PASSED
pouvoirsMagiques_apresPeremption_qualiteDiminueDe4() ✅ PASSED
pouvoirsMagiques_qualiteJamaisNegative()           ✅ PASSED
```

---

## Phase 3 : 🔵 REFACTOR — Nettoyage

Ajout de commentaires explicatifs et documentation dans `PouvoirsMagiquesUpdater.java` sans changer la logique.

Vérification que les **18 tests existants passent toujours** (comportement d'origine préservé).

---

## Résumé de la démarche

| Phase | Action | Tests |
|-------|--------|-------|
| 🔴 RED | Écriture de 3 tests basés sur les règles métier | 3 tests FAIL |
| 🟢 GREEN | Création de `PouvoirsMagiquesUpdater` + ajout dans Factory | 3 tests PASS |
| 🔵 REFACTOR | Ajout de commentaires, documentation | 18+3 = 21 tests PASS |

### Pourquoi le Pattern Strategy facilite le TDD

Avec le Pattern Strategy, ajouter "Pouvoirs magiques" n'a nécessité que :
- **1 nouvelle classe** (`PouvoirsMagiquesUpdater`)
- **1 ligne dans la Factory** (`case "Pouvoirs magiques"`)
- **0 modification** du code existant

Sans le refactoring, il aurait fallu modifier la méthode `updateQuality()` de 60 lignes, avec un risque de casser le comportement des autres items.

---

## Fichiers concernés

| Fichier | Rôle |
|---------|------|
| `src/test/java/com/magasin/MagasinTest.java` (lignes 155-183) | Tests TDD |
| `src/main/java/com/magasin/updaters/PouvoirsMagiquesUpdater.java` | Stratégie |
| `src/main/java/com/magasin/ItemUpdaterFactory.java` | Enregistrement |
