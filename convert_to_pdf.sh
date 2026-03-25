#!/bin/bash

# Script pour convertir le guide Markdown en PDF
# Nécessite : pandoc (sudo apt install pandoc)

echo "🔄 Conversion du guide Pattern Strategy en PDF..."

# Vérifier si pandoc est installé
if ! command -v pandoc &> /dev/null; then
    echo "❌ Pandoc n'est pas installé."
    echo "📦 Installation : sudo apt install pandoc"
    echo "🌐 Ou visitez : https://pandoc.org/installing/"
    exit 1
fi

# Conversion en PDF
pandoc docs/PATTERN_STRATEGY_THEORIE.md \
    -o Pattern_Strategy_Guide.pdf \
    --pdf-engine=xelatex \
    --variable=geometry:a4paper \
    --variable=margin:1in \
    --variable=colorlinks:true \
    --variable=linkcolor:blue \
    --variable=urlcolor:blue \
    --variable=toccolor:blue \
    --table-of-contents \
    --number-sections \
    --highlight-style=github

if [ $? -eq 0 ]; then
    echo "✅ PDF généré avec succès : Pattern_Strategy_Guide.pdf"
    echo "📁 Fichier disponible dans : $(pwd)"
else
    echo "❌ Erreur lors de la génération du PDF"
fi
