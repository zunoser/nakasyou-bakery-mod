package dev.nakasyou.bakery;

import java.util.ArrayDeque;
import java.util.Deque;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class CraftingPlayerContext {
    private static final ThreadLocal<Deque<Player>> CURRENT_PLAYERS = ThreadLocal.withInitial(ArrayDeque::new);

    private CraftingPlayerContext() {
    }

    public static void push(Player player) {
        CURRENT_PLAYERS.get().push(player);
    }

    public static void pop() {
        Deque<Player> players = CURRENT_PLAYERS.get();
        if (!players.isEmpty()) {
            players.pop();
        }
        if (players.isEmpty()) {
            CURRENT_PLAYERS.remove();
        }
    }

    public static void tagCraftedBread(ItemStack stack) {
        Deque<Player> players = CURRENT_PLAYERS.get();
        if (players.isEmpty()) {
            CURRENT_PLAYERS.remove();
            return;
        }

        BreadTagger.tagCraftedBread(players.peek(), stack);
    }
}
