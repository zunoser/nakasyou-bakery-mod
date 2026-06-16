package dev.nakasyou.bakery;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
    public static final SoundEvent NAKASYOU_VOICE = SoundEvent.createVariableRangeEvent(
            Identifier.fromNamespaceAndPath(NakasyouBakeryMod.MOD_ID, "nakasyou_voice"));

    private ModSounds() {
    }

    public static void init() {
    }
}
