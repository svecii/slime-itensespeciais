package net.arfay.factions.listeners;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Vector;

import net.arfay.factions.Ice;
import net.arfay.factions.itens.LauncherItem;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class LauncherListener implements Listener
{
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = player.getItemInHand();
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName() || itemStack.getType() != Material.FIREWORK || !itemStack.getItemMeta().getDisplayName().contains("Lançador")) {
            return;
        }
        event.setCancelled(true);
        if (player.getLocation().getY() >= 220.0) {
            return;
        }
        final int amount = Integer.parseInt(itemStack.getItemMeta().getDisplayName().split(" ")[0].replaceAll("[^0-9]", ""));
        final int amount_total = amount - 20;
        final ItemStack laucherItem = LauncherItem.get().getItem();
        final ItemMeta laucherMeta = laucherItem.getItemMeta();
        laucherMeta.setDisplayName("§f(§a" + amount_total + "§f) §eLançador");
        laucherItem.setItemMeta(laucherMeta);
        if (amount == 0) {
            event.setCancelled(true);
            player.getInventory().setItemInHand((ItemStack)null);
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 20.0f, 20.0f);
            player.sendMessage("§cSeu lançador acabou!");
            return;
        }
        final Vector vel = player.getVelocity().setY(4.0);
        player.setVelocity(vel);
        player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 5.0f, 1.0f);
        player.setItemInHand(laucherItem);
        if (!player.hasMetadata("launcher")) {
            player.setMetadata("launcher", (MetadataValue)new FixedMetadataValue((Plugin)Ice.get(), (Object)true));
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            final Player player = (Player)event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.hasMetadata("launcher") && player.getMetadata("launcher").get(0).asBoolean()) {
                event.setCancelled(true);
                event.setDamage(0.0);
                player.removeMetadata("launcher", (Plugin)Ice.get());
            }
        }
    }
    
    @EventHandler
    public void onKick(final PlayerKickEvent event) {
        final Player player = event.getPlayer();
        if (player.hasMetadata("launcher")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (player.hasMetadata("launcher")) {
            player.removeMetadata("launcher", (Plugin)Ice.get());
        }
    }
}

