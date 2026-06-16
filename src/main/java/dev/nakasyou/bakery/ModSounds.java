package dev.nakasyou.bakery;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
    public static final SoundEvent NAKASYOU_VOICE = register("nakasyou_voice");

    private ModSounds() {
    }

    private static SoundEvent register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(NakasyouBakeryMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {
    }
}
