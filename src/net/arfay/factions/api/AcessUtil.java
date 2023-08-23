package net.arfay.factions.api;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.factions.entity.BoardColl;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class AcessUtil
{
    public static boolean isValidLocation(final Location l, final Player p) {
        final BoardColl coll = BoardColl.get();
        final Faction faction = coll.getFactionAt(PS.valueOf(l));
        if (faction.getId().equals("warzone") || faction.getId().equals("safezone")) {
            p.sendMessage("§cVocê não pode utilziar este item neste local.");
            return false;
        }
        return true;
    }
}

