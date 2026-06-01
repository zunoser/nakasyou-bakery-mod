package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.CraftingPlayerContext;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResultContainer.class)
public abstract class ResultContainerMixin {
    @Inject(method = "setItem", at = @At("HEAD"))
    private void nakasyouBakery$tagCraftedBreadBeforeResultSlotUpdate(int slot, ItemStack stack, CallbackInfo ci) {
        if (slot == 0) {
            CraftingPlayerContext.tagCraftedBread(stack);
        }
    }
}
