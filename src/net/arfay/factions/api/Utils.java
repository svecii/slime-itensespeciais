package net.arfay.factions.api;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public class Utils {
    public static void removeItem(final Player player, final ItemStack item) {
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        }
        else {
            player.setItemInHand((ItemStack)null);
        }
    }
}

