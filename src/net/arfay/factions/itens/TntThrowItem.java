package net.arfay.factions.itens;

import java.util.Arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.ItemBuilder;
import net.arfay.factions.objects.Especial;

public class TntThrowItem implements Especial {
    public static TntThrowItem get() {
        return new TntThrowItem();
    }
    
    @Override
    public Integer getId() {
        return 46;
    }
    
    @Override
    public String getName() {
        return "Arremessavel";
    }
    
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.TNT).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("§cTNT Arremessável").lore(Arrays.asList("§7Use para arremessar uma TNT para", "§7onde você estiver mirando.")).build();
    }
}

