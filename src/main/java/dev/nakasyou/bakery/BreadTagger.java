package dev.nakasyou.bakery;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;

public final class BreadTagger {
    private static final String NAKASYOU0 = "nakasyou0";

    private BreadTagger() {
    }

    public static void tagCraftedBread(Player player, ItemStack stack) {
        if (!isBread(stack) || !NAKASYOU0.equals(player.getGameProfile().getName())) {
            return;
        }

        CompoundTag tag = new CompoundTag();
        tag.putBoolean("nakasyou_bakery", true);
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

    private static boolean isBread(ItemStack stack) {
        return !stack.isEmpty() && stack.is(Items.BREAD);
    }
}
