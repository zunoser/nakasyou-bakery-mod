package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.BreadTagger;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantResultSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantResultSlot.class)
public abstract class MerchantResultSlotMixin {
    @Inject(method = "onTake", at = @At("HEAD"))
    private void nakasyouBakery$tagVillagerTradeBread(Player player, ItemStack stack, CallbackInfo ci) {
        BreadTagger.tagVillagerTradeBread(stack);
    }
}
