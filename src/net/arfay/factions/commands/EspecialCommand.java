package net.arfay.factions.commands;

import org.bukkit.inventory.meta.ItemMeta;

import net.arfay.factions.api.Helper;
import net.arfay.factions.manager.EspecialManager;
import net.arfay.factions.objects.Especial;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class EspecialCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!sender.hasPermission("perm.admin")) {
            sender.sendMessage("§cVocê não possui permissão para executar este comando.");
            return true;
        }
        switch (args.length) {
            case 3: {
                final String targetName = args[0];
                final String itemNameOrId = args[1];
                final String preAmount = args[2];
                if (!Helper.isInteger(preAmount)) {
                    sender.sendMessage("§cVocê inseriu uma quantia inválida.");
                    return true;
                }
                final Integer amount = Integer.parseInt(preAmount);
                Especial item = null;
                if (Helper.isInteger(itemNameOrId)) {
                    final Integer id = Integer.parseInt(itemNameOrId);
                    item = EspecialManager.getItem(id);
                }
                else {
                    item = EspecialManager.getItem(itemNameOrId);
                }
                if (item == null) {
                    sender.sendMessage("§cEste item não existe.");
                    return true;
                }
                final ItemStack item2 = item.getItem();
                final Player player = Bukkit.getPlayerExact(targetName);
                if (player == null) {
                    sender.sendMessage("§cEste jogador não está online.");
                    return true;
                }
                item2.setAmount((int)amount);
                final ItemStack itemstack = item2;
                if (item.getId() == 2) {
                    final ItemMeta itemsMeta = itemstack.getItemMeta();
                    itemsMeta.setDisplayName("§f(§a" + amount + "§f)§e Lançador");
                    itemstack.setAmount(1);
                    itemstack.setItemMeta(itemsMeta);
                }
                player.getInventory().addItem(new ItemStack[] { itemstack });
                sender.sendMessage("§aO jogador " + player.getName() + " recebeu " + amount + " " + item.getName() + ".");
                return true;
            }
            default: {
                sender.sendMessage("§cUtilize /item <jogador> <" + this.getItemList() + "> <quantia>.");
                return true;
            }
        }
    }
    
    private String getItemList() {
        final StringBuilder builder = new StringBuilder();
        EspecialManager.getItems().forEach(item -> builder.append(String.valueOf(item.getName()) + ", "));
        return builder.toString().substring(0, builder.length() - 2);
    }
}

