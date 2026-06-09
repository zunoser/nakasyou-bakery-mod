package dev.nakasyou.bakery;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
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
    private static final float TOTEM_EFFECT_CHANCE = 0.2F;
    private static final int TOTEM_REGENERATION_DURATION_TICKS = 900;
    private static final int TOTEM_ABSORPTION_DURATION_TICKS = 100;
    private static final int TOTEM_FIRE_RESISTANCE_DURATION_TICKS = 800;

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

    public static void applyEatenBreadEffect(Level level, LivingEntity entity, ItemStack stack) {
        if (!(entity instanceof ServerPlayer) || !isBread(stack)) {
            return;
        }

        if (isNakasyouBakeryBread(stack)) {
            applyBakeryBreadTotemEffect(level, entity);
        } else {
            applyNonBakeryBreadHungerEffect(entity);
        }
    }

    private static void applyBakeryBreadTotemEffect(Level level, LivingEntity entity) {
        if (entity.getRandom().nextFloat() >= TOTEM_EFFECT_CHANCE) {
            return;
        }

        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, TOTEM_REGENERATION_DURATION_TICKS, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, TOTEM_ABSORPTION_DURATION_TICKS, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, TOTEM_FIRE_RESISTANCE_DURATION_TICKS, 0));
        level.broadcastEntityEvent(entity, EntityEvent.PROTECTED_FROM_DEATH);
    }

    private static void applyNonBakeryBreadHungerEffect(LivingEntity entity) {
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
