package net.arfay.factions.api;

import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.enchantments.Enchantment;
import java.util.Arrays;
import org.bukkit.Material;

public class RecipeItem {
    public RecipeItem() {
        final ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.DOUBLE_PLANT).name("§6Moeda").lore(Arrays.asList("§7Utilize est\u00e1 moeda para dar", "§7upgrade no level de sua fac\u00e7\u00e3o", "§7atrav\u00e9s do §f\"/f upgrades\"§7.")).enchant(Enchantment.DURABILITY, 1).removeAttributes().build());
        r.shape(new String[] { "** ", "** ", "   " });
        r.setIngredient('*', Material.INK_SACK, 11);
        Bukkit.addRecipe((Recipe)r);
    }
}
