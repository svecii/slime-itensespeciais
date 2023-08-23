package net.arfay.factions.listeners;

import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import com.massivecraft.factions.entity.Faction;
import org.bukkit.plugin.Plugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import com.massivecraft.factions.Rel;
import com.massivecraft.massivecore.ps.PS;

import net.arfay.factions.Ice;
import net.arfay.factions.api.ActionBar;

import com.massivecraft.factions.entity.Board;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

public class ChunkClearListener implements Listener
{
    public void LimparChunk(final Block b) {
        final Chunk chunk = b.getLocation().getChunk();
        for (int x = 16; x > 0; --x) {
            for (int y = 255; y > 0; --y) {
                for (int z = 16; z > 0; --z) {
                    if (chunk.getBlock(x, x, z) != null) {
                        chunk.getBlock(x, y, z).setType(Material.AIR);
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        if (item == null) {
            return;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains("§eLimpador de Chunk")) {
            final MPlayer ps = MPlayer.get((Object)p);
            final Faction floc = Board.get((Object)p).getFactionAt(PS.valueOf(e.getBlock().getLocation()));
            if (floc.getId().equals("none") || !ps.isInOwnTerritory()) {
                e.setCancelled(true);
                p.sendMessage("§cVocê só pode utilizar o limpador de chunk em seu território.");
                return;
            }
            if (ps.getRole() != Rel.LEADER && ps.getRole() != Rel.OFFICER) {
                e.setCancelled(true);
                p.sendMessage("§cVocê não tem permissão para utilizar o limpador de chunk.");
                return;
            }
            final ArmorStand am = (ArmorStand)e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation().add(0.5, 0.5, 0.5), EntityType.ARMOR_STAND);
            am.setVisible(false);
            am.setBasePlate(false);
            am.setCanPickupItems(true);
            final String name = "§eLimpando chunk...";
            am.setCustomName(name);
            am.setSmall(true);
            am.setRemoveWhenFarAway(false);
            am.setGravity(false);
            am.setCustomNameVisible(true);
            final ItemStack item2 = new ItemStack(Material.DIAMOND_SPADE);
            item2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            final ItemMeta meta = item2.getItemMeta();
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
            item2.setItemMeta(meta);
            final ArmorStand am2 = (ArmorStand)e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation().add(0.9, -0.4, 0.01), EntityType.ARMOR_STAND);
            am2.setVisible(false);
            am2.setBasePlate(false);
            am2.setCanPickupItems(true);
            am2.setSmall(false);
            am2.getEquipment().setItemInHand(item2);
            am2.setRightArmPose(new EulerAngle(300.0, 0.0, 270.0));
            am2.setRemoveWhenFarAway(false);
            am2.setGravity(false);
            am2.setCustomNameVisible(false);
            new BukkitRunnable() {
                int tempo = 7;
                
                public void run() {
                    --this.tempo;
                    if (this.tempo == 6) {
                        ActionBar.sendActionBarMessage(p, "§aSua chunk será limpada em 5 segundos.");
                        p.playSound(p.getLocation(), Sound.GLASS, 1.0f, 1.0f);
                        return;
                    }
                    if (this.tempo == 5) {
                    	ActionBar.sendActionBarMessage(p, "§aSua chunk será limpada em 4 segundos.");
                        p.playSound(p.getLocation(), Sound.GLASS, 1.0f, 1.0f);
                        return;
                    }
                    if (this.tempo == 4) {
                    	ActionBar.sendActionBarMessage(p, "§aSua chunk será limpada em 3 segundos.");
                        p.playSound(p.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
                        return;
                    }
                    if (this.tempo == 3) {
                    	ActionBar.sendActionBarMessage(p, "Sua chunk será limpada em 2 segundos.");
                        p.playSound(p.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
                        return;
                    }
                    if (this.tempo == 2) {
                    	ActionBar.sendActionBarMessage(p, "§aSua chunk será limpada em 1 segundo.");
                        p.playSound(p.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
                        return;
                    }
                    if (this.tempo == 1) {
                        ChunkClearListener.this.LimparChunk(e.getBlock());
                        p.sendMessage("§aSua chunk foi limpada com sucesso!");
                        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                        am.remove();
                        am2.remove();
                    }
                }
            }.runTaskTimer((Plugin)Ice.get(), 0L, 20L);
        }
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            final ArmorStand as = (ArmorStand)e.getRightClicked();
            if (as.isSmall()) {
                e.setCancelled(true);
            }
        }
    }
}

