package dev.nakasyou.bakery.mixin;

import dev.nakasyou.bakery.BreadTagger;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantOffer.class)
public abstract class MerchantOfferMixin {
    @Inject(method = "assemble", at = @At("RETURN"))
    private void nakasyouBakery$tagVillagerTradeBreadBeforeResultSlotUpdate(CallbackInfoReturnable<ItemStack> cir) {
        BreadTagger.tagVillagerTradeBread(cir.getReturnValue());
    }
}
