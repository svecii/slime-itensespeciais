package net.arfay.factions.itens;

import java.util.Arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.ItemBuilder;
import net.arfay.factions.objects.Especial;

public class ClearChunkItem implements Especial {
    public static ClearChunkItem get() {
        return new ClearChunkItem();
    }
    
    @Override
    public Integer getId() {
        return 120;
    }
    
    @Override
    public String getName() {
        return "Limpador";
    }
    
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ENDER_PORTAL_FRAME).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("§eLimpador de Chunk").lore(Arrays.asList("§7Ao colocar este item em uma", "§7chunk do seu território, ele", "§7irá limpar toda a chunk.", "", "§eEle removerá todos os blocos.")).build();
    }
}
