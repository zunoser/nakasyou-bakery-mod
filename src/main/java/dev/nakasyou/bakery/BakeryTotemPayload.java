package dev.nakasyou.bakery;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record BakeryTotemPayload(int entityId) implements CustomPacketPayload {
    public static final Type<BakeryTotemPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(NakasyouBakeryMod.MOD_ID, "bakery_totem"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BakeryTotemPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, BakeryTotemPayload::entityId, BakeryTotemPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
