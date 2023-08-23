package net.arfay.factions.api;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnFirework
{
    public static void empty(final Player p) {
        p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
    }
    
    public static void small(final Player p) {
        final Firework fw = (Firework)p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        final FireworkMeta fm = fw.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(random()).build());
        fw.setFireworkMeta(fm);
    }
    
    public static void medium(final Player p) {
        final Firework fw = (Firework)p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        final FireworkMeta fm = fw.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random() }).build());
        fm.setPower(1);
        fw.setFireworkMeta(fm);
    }
    
    public static void big(final Player p) {
        final Firework fw = (Firework)p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        final FireworkMeta fm = fw.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(new Color[] { random(), random(), random(), random() }).build());
        fm.setPower(1);
        fw.setFireworkMeta(fm);
    }
    
    private static Color random() {
        final Random rnd = new Random();
        final int r = rnd.nextInt(255);
        final int g = rnd.nextInt(255);
        final int b = rnd.nextInt(255);
        return Color.fromRGB(r, g, b);
    }
}

