# 📊 Guide du Coverage de Code

## 🎯 Pourquoi le coverage dans ce projet ?

Le coverage de code mesure quelles parties du code sont exécutées pendant les tests. Dans ce projet de refactoring, il est particulièrement utile pour :

### ✅ **Validation du refactoring**
- **MagasinRefactored** : 96% coverage → Le nouveau code est bien testé
- **com.magasin.updaters** : 82% coverage → Les stratégies sont validées
- Preuve que le Pattern Strategy est correctement implémenté

### ✅ **Confiance dans les changements**
- Garantie que les nouvelles fonctionnalités ne cassent pas l'existant
- Identification des zones à risque avant modification
- Documentation vivante de ce qui est testé

### ✅ **Qualité technique**
- Évite la régression lors de l'ajout de nouveaux types d'items
- Aide à maintenir la qualité lors de l'évolution du code
- Objectif mesurable pour l'équipe

---

## 📈 Résultats actuels

| Package | Coverage | État | Notes |
|---------|----------|------|-------|
| `com.magasin` | **96%** | ✅ Excellent | Cœur métier bien couvert |
| `com.magasin.updaters` | **82%** | ✅ Bon | Stratégies testées |
| `com.magasin.utils` | **0%** | ⚠️ À améliorer | Classes utilitaires |
| **Total** | **25%** | ⚠️ Moyen | Impacté par `utils` |

---

## 🔍 Ce que le coverage révèle

### **Bien couvert (✅)**
```java
// MagasinRefactored.updateQuality() - 96%
for (Item item : items) {
    ItemUpdater updater = ItemUpdaterFactory.getUpdater(item);
    updater.update(item);
}
```
- La logique principale est testée via `MagasinTest`
- Chaque stratégie est validée individuellement

### **Partiellement couvert (⚠️)**
```java
// ItemUpdaterFactory.getUpdater() - 90%
switch (itemName) {
    case "Comté": return new ComteUpdater();
    case "Kryptonite": return new KryptoniteUpdater();
    // ... autres cas
}
```
- Tous les cas existants sont testés
- Nouveaux types d'items nécessiteront des tests

### **Non couvert (❌)**
```java
// com.magasin.utils - 0%
public class SimpleTestRunner {
    // Classes de test manuel
}
```
- Classes utilitaires pour tests manuels
- Pas critiques pour le fonctionnement

---

## 🛠️ Comment utiliser le coverage

### **Commandes**
```bash
# Lancer les tests avec coverage
./gradlew test jacocoTestReport

# Vérifier les objectifs de coverage
./gradlew jacocoTestCoverageVerification

# Tout en une fois
./gradlew test jacocoTestReport jacocoTestCoverageVerification
```

### **Voir les rapports**
- **HTML** : `build/reports/jacoco/test/html/index.html`
- **XML** : `build/reports/jacoco/test/jacocoTestReport.xml`

### **Interpréter les couleurs**
- 🟩 **Vert** : Code couvert
- 🟥 **Rouge** : Code non couvert
- 🟨 **Jaune** : Partiellement couvert

---

## 🎯 Objectifs de coverage

### **Règles configurées**
```gradle
// Règle générale : 20% minimum
// Code métier : 80% minimum (updaters)
// Classes principales : 90% minimum
// Factory : 85% minimum
```

### **Pourquoi ces chiffres ?**
- **20% global** : Réaliste avec les utilitaires exclues
- **80% updaters** : Garantie que chaque stratégie fonctionne
- **90%+ classes principales** : Cœur métier robuste

---

## 📝 Améliorer le coverage

### **Si vous ajoutez un nouvel item**
1. Créer la classe `NewItemUpdater`
2. Ajouter le cas dans `ItemUpdaterFactory`
3. **Ajouter un test dans `MagasinTest`**
4. Vérifier que le coverage reste bon

### **Exemple pour un nouvel item**
```java
@Test
@DisplayName("Nouvel item: Dragon sacré")
void dragonSacré_qualitéAugmente() {
    Item[] items = new Item[]{new Item("Dragon sacré", 10, 30)};
    MagasinRefactored app = new MagasinRefactored(items);
    app.updateQuality();
    
    assertEquals("Dragon sacré", app.items[0].name);
    assertEquals(9, app.items[0].sellIn);
    assertEquals(31, app.items[0].quality); // +1 par jour
}
```

---

## 💡 Bonnes pratiques

### **✅ Ce que le coverage garantit**
- Le code testé fonctionne comme attendu
- Les régressions sont détectées
- La qualité est maintenue dans le temps

### **❌ Ce que le coverage ne garantit pas**
- La **qualité** des tests (juste qu'ils s'exécutent)
- Les **cas limites** non testés
- La **logique métier** correcte

### **🎯 Recommandation**
- **Maintenir > 80%** sur le code métier
- **Ajouter des tests** pour chaque nouvelle fonctionnalité
- **Vérifier le coverage** avant chaque commit important

---

## 📊 Conclusion

Le coverage dans ce projet n'est pas juste un chiffre, c'est :

1. **Preuve** que le refactoring a réussi
2. **Filet de sécurité** pour les évolutions futures  
3. **Documentation** de ce qui est validé
4. **Confiance** dans la qualité du code

Avec 96% sur le cœur métier, le projet est prêt pour la production et les futures évolutions ! 🚀
