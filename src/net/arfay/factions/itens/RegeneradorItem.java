package net.arfay.factions.itens;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.arfay.factions.api.ItemBuilder;
import net.arfay.factions.objects.Especial;

public class RegeneradorItem implements Especial {
    public static RegeneradorItem get() {
        return new RegeneradorItem();
    }
    
    @Override
    public Integer getId() {
        return 373;
    }
    
    @Override
    public String getName() {
        return "Regenerador";
    }
    
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.POTION).removeAttributes().name("§aRegenerador").lore(Arrays.asList("§7Ao utilizar este item você e todos", "§7os membros de sua facção", "§7terão a vida restaurada!")).build();
    }
}
