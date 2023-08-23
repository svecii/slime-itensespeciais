package net.arfay.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.AcessUtil;
import net.arfay.factions.itens.TntThrowItem;

import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class TntThrowListener implements Listener
{
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        if (item.getType() == Material.TNT && item.isSimilar(TntThrowItem.get().getItem()) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (!AcessUtil.isValidLocation(p.getLocation(), p)) {
                return;
            }
            if (p.getItemInHand().getAmount() > 1) {
                p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
            }
            else {
                p.setItemInHand(new ItemStack(Material.AIR));
            }
            final TNTPrimed tnt = (TNTPrimed)p.getWorld().spawn(p.getLocation(), (Class)TNTPrimed.class);
            tnt.setVelocity(p.getLocation().getDirection().normalize().multiply(1.2));
        }
    }
}

