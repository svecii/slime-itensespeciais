package net.arfay.factions.itens;

import java.util.Arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.ItemBuilder;
import net.arfay.factions.objects.Especial;

public class GeneratorItem implements Especial {
    public static GeneratorItem get() {
        return new GeneratorItem();
    }
    
    @Override
    public Integer getId() {
        return 7;
    }
    
    @Override
    public String getName() {
        return "Gerador";
    }
    
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.BEDROCK).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("§eGerador de Camadas").lore(Arrays.asList("§7Utilize este item gerar", "§7camadas de bedrock.")).build();
    }
}

