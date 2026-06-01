package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.CraftingPlayerContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin {
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

    @Inject(method = "finishPlacingRecipe", at = @At("HEAD"), require = 0)
    private void nakasyouBakery$pushRecipeBookCraftingPlayer(ServerLevel level, RecipeHolder<CraftingRecipe> recipe, CallbackInfo ci) {
        CraftingPlayerContext.push(this.owner());
    }

    @Inject(method = "finishPlacingRecipe", at = @At("RETURN"), require = 0)
    private void nakasyouBakery$popRecipeBookCraftingPlayer(ServerLevel level, RecipeHolder<CraftingRecipe> recipe, CallbackInfo ci) {
        CraftingPlayerContext.pop();
    }
}
