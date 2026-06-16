package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.BreadTagger;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void nakasyouBakery$applyEatenBreadEffect(Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        BreadTagger.applyEatenBreadEffect(entity, (ItemStack) (Object) this);
    }
}
