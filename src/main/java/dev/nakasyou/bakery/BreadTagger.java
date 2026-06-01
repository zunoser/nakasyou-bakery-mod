package dev.nakasyou.bakery;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;

public final class BreadTagger {
    private static final String NAKASYOU0 = "nakasyou0";
    private static final String BAKERY_TAG = "nakasyou_bakery";
    private static final float ROTTEN_FLESH_HUNGER_CHANCE = 0.8F;
    private static final int ROTTEN_FLESH_HUNGER_DURATION_TICKS = 600;

    private BreadTagger() {
    }

    public static void tagCraftedBread(Player player, ItemStack stack) {
        if (!isBread(stack) || !NAKASYOU0.equals(player.getName().getString())) {
            return;
        }

        CompoundTag tag = new CompoundTag();
        tag.putBoolean(BAKERY_TAG, true);
        tag.putString("source", "crafted");
        tag.putString("baker", NAKASYOU0);

        stack.set(DataComponents.CUSTOM_NAME, Component.literal("nakasyou bakeryのパン").withStyle(ChatFormatting.GOLD));
        stack.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("nakasyou bakeryが愛を込めて生産").withStyle(ChatFormatting.GRAY)
        )));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public static void tagVillagerTradeBread(ItemStack stack) {
        if (!isBread(stack)) {
            return;
        }

        CompoundTag tag = new CompoundTag();
        tag.putBoolean("not_fair_trade", true);
        tag.putString("source", "villager_trade");
        tag.putString("labor", "forced");

        stack.set(DataComponents.CUSTOM_NAME, Component.literal("Not fair trade bread").withStyle(ChatFormatting.RED));
        stack.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("奴隷労働によって生産").withStyle(ChatFormatting.GRAY),
                Component.literal("[Not fair trade]").withStyle(ChatFormatting.DARK_RED)
        )));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public static void applyNonBakeryBreadEffect(Level level, LivingEntity entity, ItemStack stack) {
        if (level.isClientSide || !(entity instanceof Player) || !isBread(stack) || isNakasyouBakeryBread(stack)) {
            return;
        }

        if (entity.getRandom().nextFloat() < ROTTEN_FLESH_HUNGER_CHANCE) {
            entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, ROTTEN_FLESH_HUNGER_DURATION_TICKS, 0));
        }
    }

    private static boolean isNakasyouBakeryBread(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        return data.copyTag().getBooleanOr(BAKERY_TAG, false);
    }

    private static boolean isBread(ItemStack stack) {
        return !stack.isEmpty() && stack.is(Items.BREAD);
    }
}
