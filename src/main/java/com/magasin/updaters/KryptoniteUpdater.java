package com.magasin.updaters;

import com.magasin.Item;
import com.magasin.ItemUpdater;

public class KryptoniteUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Kryptonite ne change jamais (qualité 80 constante, pas de date de péremption)
        // Rien à faire
    }
}
