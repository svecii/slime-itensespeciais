package net.arfay.factions.manager;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import net.arfay.factions.itens.ClearChunkItem;
import net.arfay.factions.itens.GeneratorItem;
import net.arfay.factions.itens.LauncherItem;
import net.arfay.factions.itens.RegeneradorItem;
import net.arfay.factions.itens.TntThrowItem;
import net.arfay.factions.objects.Especial;
public class EspecialManager {
	private static List<Especial> items = new ArrayList<>();
	
	
    public EspecialManager() {
        EspecialManager.items.add(new TntThrowItem());
        EspecialManager.items.add(new LauncherItem());
        EspecialManager.items.add(new GeneratorItem());
        EspecialManager.items.add(new ClearChunkItem());
        EspecialManager.items.add(new RegeneradorItem());
    }
    
    public static List<Especial> getItems() {
        return EspecialManager.items;
    }
    
    public static Especial getItem(final String name) {
        for (final Especial item : EspecialManager.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
    
    public static Especial getItem(final Integer id) {
        for (final Especial item : EspecialManager.items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public static Especial getItem(final ItemStack item) {
        return EspecialManager.items.stream().filter(customItem -> customItem.getItem().isSimilar(item)).findFirst().orElse(null);
    }
}
