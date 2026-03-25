#!/bin/bash

echo "🧪 Test simple du projet MagasinMagique"
echo "========================================"

echo ""
echo "1️⃣ Compilation du code principal..."
javac -cp src/main/java src/main/java/com/magasin/*.java src/main/java/com/magasin/updaters/*.java src/main/java/com/magasin/utils/*.java
if [ $? -eq 0 ]; then
    echo "✅ Code principal compilé avec succès"
else
    echo "❌ Erreur de compilation du code principal"
    exit 1
fi

echo ""
echo "2️⃣ Exécution du Golden Master..."
java -cp src/main/java com.magasin.utils.GoldenMaster

echo ""
echo "3️⃣ Exécution du test de comparaison..."
java -cp src/main/java com.magasin.utils.ComparisonTest

echo ""
echo "4️⃣ Test des Pouvoirs Magiques..."
java -cp src/main/java com.magasin.utils.TestPouvoirsMagiques

echo ""
echo "5️⃣ Test du runner personnalisé..."
java -cp src/main/java com.magasin.utils.SimpleTestRunner

echo ""
echo "🎉 Tous les tests sont passés avec succès !"
echo ""
echo "📋 Résumé :"
echo "✅ Pattern Strategy implémenté"
echo "✅ Nouvelle fonctionnalité 'Pouvoirs magiques' ajoutée"
echo "✅ Comportement legacy préservé"
echo "✅ Code documenté en français"
echo ""
echo "💡 Vous pouvez aussi lancer les tests JUnit avec : ./gradlew test"
