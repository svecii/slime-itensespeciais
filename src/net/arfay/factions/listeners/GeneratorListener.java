package net.arfay.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.entity.Faction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import com.massivecraft.factions.Rel;
import com.massivecraft.massivecore.ps.PS;

import net.arfay.factions.Ice;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

public class GeneratorListener implements Listener
{
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final MPlayer mp = MPlayer.get((Object)p);
        final Faction fac = mp.getFaction();
        final Block b = e.getBlockPlaced();
        final Location bloc = b.getLocation();
        final Faction loc = BoardColl.get().getFactionAt(PS.valueOf(b.getLocation()));
        final ItemStack item = p.getItemInHand();
        if (item == null) {
            return;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains("§eGerador de Camadas")) {
            if (!loc.getName().equals(fac.getName())) {
                e.setCancelled(true);
                p.sendMessage("§cVocê só pode utilizar este gerador de camadas em seu território.");
                return;
            }
            if (mp.getRole() == Rel.RECRUIT) {
                e.setCancelled(true);
                p.sendMessage("§cVocê não tem permissão para utilizar o gerador de camadas.");
                return;
            }
            new BukkitRunnable() {
                public void run() {
                    bloc.setY(bloc.getY() - 1.0);
                    final Block next = b.getWorld().getBlockAt((int)bloc.getX(), (int)bloc.getY(), (int)bloc.getZ());
                    if (next.getType() == Material.AIR && bloc.getY() != 0.0) {
                        next.setType(Material.BEDROCK);
                    }
                    else {
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)Ice.get(), 0L, 40L);
        }
    }
}
