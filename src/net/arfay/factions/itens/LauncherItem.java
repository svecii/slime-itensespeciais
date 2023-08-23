package net.arfay.factions.itens;

import java.util.Arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.ItemBuilder;
import net.arfay.factions.objects.Especial;

public class LauncherItem implements Especial {
    public static LauncherItem get() {
        return new LauncherItem();
    }
    
    @Override
    public Integer getId() {
        return 2;
    }
    
    @Override
    public String getName() {
        return "Launcher";
    }
    
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.FIREWORK).enchant(Enchantment.DURABILITY, 1).removeAttributes().lore(Arrays.asList("§7Utilize este item para ser lançado para cima.")).build();
    }
}

