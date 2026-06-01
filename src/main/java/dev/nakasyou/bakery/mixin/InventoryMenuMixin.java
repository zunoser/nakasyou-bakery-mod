package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.CraftingPlayerContext;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin {
    @Shadow
    protected abstract Player owner();

    @Inject(method = "slotsChanged", at = @At("HEAD"))
    private void nakasyouBakery$pushCraftingPlayer(Container container, CallbackInfo ci) {
        CraftingPlayerContext.push(this.owner());
    }

    @Inject(method = "slotsChanged", at = @At("RETURN"))
    private void nakasyouBakery$popCraftingPlayer(Container container, CallbackInfo ci) {
        CraftingPlayerContext.pop();
    }
}
