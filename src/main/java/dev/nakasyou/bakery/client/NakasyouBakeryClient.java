package dev.nakasyou.bakery.client;

import dev.nakasyou.bakery.BakeryTotemPayload;
import dev.nakasyou.bakery.NakasyouBakeryMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class NakasyouBakeryClient implements ClientModInitializer {
    private static final Identifier NAKASYOU_ICON_MODEL =
            Identifier.fromNamespaceAndPath(NakasyouBakeryMod.MOD_ID, "nakasyou_icon");
    private static final int TOTEM_PARTICLE_COUNT = 30;

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(BakeryTotemPayload.TYPE,
                (payload, context) -> playBakeryTotemEffect(context.client(), payload.entityId()));
    }

    private static void playBakeryTotemEffect(Minecraft minecraft, int entityId) {
        ClientLevel level = minecraft.level;
        if (level == null) {
            return;
        }

        Entity entity = level.getEntity(entityId);
        if (entity == null) {
            return;
        }

        minecraft.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, TOTEM_PARTICLE_COUNT);
        level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F, false);

        if (entity == minecraft.player) {
            ItemStack stack = new ItemStack(Items.TOTEM_OF_UNDYING);
            stack.set(DataComponents.ITEM_MODEL, NAKASYOU_ICON_MODEL);
            minecraft.gameRenderer.displayItemActivation(stack);
        }
    }
}
