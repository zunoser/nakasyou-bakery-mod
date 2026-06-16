package dev.nakasyou.bakery;

import net.fabricmc.api.ModInitializer;

public final class NakasyouBakeryMod implements ModInitializer {
    public static final String MOD_ID = "nakasyou-bakery";

    @Override
    public void onInitialize() {
        ModSounds.init();
    }
}
