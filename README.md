# Magasin Magique : Refactoring avec Pattern Strategy

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

Refactoring d'un système d'inventaire en utilisant le Pattern Strategy pour améliorer la maintenabilité tout en préservant 100% du comportement existant.

> Projet original : [buchu73/MagasinMagique](https://github.com/buchu73/MagasinMagique)

## Installation

```bash
git clone https://github.com/dylanholin-campus/MagasinMagique.git
cd MagasinMagique
./test-simple.sh
```

## Usage

### Lancer les tests

```bash
# Tests JUnit via Gradle
./gradlew test

# Tests complets (compilation + comparaison + golden master)
./test-simple.sh

# Comparaison avec projet original
java -cp src/main/java com.magasin.utils.ComparisonTest
```

## Architecture

Le projet utilise le Pattern Strategy pour remplacer 60 lignes de conditions imbriquées par 4 lignes de code polymorphe :

```
Magasin (Context)
    ↓
ItemUpdater (Strategy Interface)
    ↓
NormalItemUpdater | ComteUpdater | KryptoniteUpdater | PassVIPUpdater | PouvoirsMagiquesUpdater
```

### Nouvelle fonctionnalité

- **Pouvoirs magiques** : Items avec dégradation 2x plus rapide que les items normaux

## Résultats

| Métrique | Avant | Après | Amélioration |
|----------|-------|-------|--------------|
| Lignes de code | 60 | 4 | -93% |
| Complexité cyclomatique | 15 | 1 | -93% |
| Testabilité | Difficile (tout couplé) | Chaque stratégie isolée | Améliorée |
| Extensibilité | Difficile | Facile | Améliorée |

## Documentation

Voir le dossier [`docs/`](./docs/) pour la documentation complète.

## Technologies

- Java 11+
- Pattern Strategy
- Gradle
- JUnit 5

## Tests

- 18/18 tests JUnit réussis (`./gradlew test`)
- 100% de préservation du comportement legacy
- 12/12 items identiques à l'original
- Golden Master validé

## Contributing

Les contributions sont bienvenues ! Pour les questions, suggestions ou rapports de bugs :

1. Ouvrir une issue pour discuter des changements
2. Forker le projet
3. Créer une branche pour votre feature
4. Soumettre une pull request

## License

MIT License - voir le fichier [LICENSE](LICENSE) pour les détails.
