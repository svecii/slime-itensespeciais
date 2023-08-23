package net.arfay.factions.listeners;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.Listener;

public class RegeneradorListener implements Listener
{
    @EventHandler
    public void onDrinkPotion(final PlayerItemConsumeEvent e) {
        final Player p = e.getPlayer();
        final MPlayer mp = MPlayer.get((Object)p);
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aRegenerador")) {
            e.setCancelled(true);
            if (!mp.hasFaction()) {
                e.setCancelled(true);
                return;
            }
            p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
            p.sendMessage("");
            p.sendMessage("§a ▪ Poção de regeração utilizada com sucesso.");
            p.sendMessage("§e ▪ Seus membros teve a vida restaurada!");
            p.sendMessage("");
            if (mp.hasFaction() && mp.getFaction().getOnlinePlayers().size() > 1) {
                for (final Player todos : mp.getFaction().getOnlinePlayers()) {
                    if (todos.getMaxHealth() == 26.0) {
                        todos.setMaxHealth(26.0);
                        todos.setHealth(26.0);
                    }
                    else {
                        todos.resetMaxHealth();
                        todos.setHealth(20.0);
                    }
                }
            }
        }
    }
}
