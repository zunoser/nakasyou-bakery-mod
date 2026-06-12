package dev.nakasyou.bakery;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public final class NakasyouBakeryMod implements ModInitializer {
    public static final String MOD_ID = "nakasyou-bakery";

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.clientboundPlay().register(BakeryTotemPayload.TYPE, BakeryTotemPayload.CODEC);
    }
}
