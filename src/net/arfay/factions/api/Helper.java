package net.arfay.factions.api;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import org.bukkit.Location;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.Iterator;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class Helper
{
    private Helper() {
    }
    
    public static boolean isInteger(final Object o) {
        return o instanceof Integer;
    }
    
    public static boolean isLong(final String o) {
        try {
            Long.parseLong(o);
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean isDouble(final String o) {
        try {
            final double d = Double.parseDouble(o);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isByte(final String input) {
        try {
            Byte.parseByte(input);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isShort(final String input) {
        try {
            Short.parseShort(input);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isFloat(final String input) {
        try {
            Float.parseFloat(input);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isString(final Object o) {
        return o instanceof String;
    }
    
    public static boolean isBoolean(final Object o) {
        return o instanceof Boolean;
    }
    
    public static String removeChar(final String s, final char c) {
        String r = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != c) {
                r = String.valueOf(r) + s.charAt(i);
            }
        }
        return r;
    }
    
    public static String removeFirstChar(final String s, final char c) {
        String r = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != c) {
                r = String.valueOf(r) + s.charAt(i);
                break;
            }
        }
        return r;
    }
    
    public static String capitalize(final String content) {
        if (content.length() < 2) {
            return content;
        }
        final String first = content.substring(0, 1).toUpperCase();
        return String.valueOf(first) + content.substring(1);
    }
    
    public static String plural(final int count, final String word, final String ending) {
        return (count == 1) ? word : (String.valueOf(word) + ending);
    }
    
    public static String toColor(final String hexValue) {
        if (hexValue == null) {
            return "";
        }
        return ChatColor.getByChar(hexValue).toString();
    }
    
    public static List<String> fromArray(final String... values) {
        final List<String> results = new ArrayList<String>();
        Collections.addAll(results, values);
        results.remove("");
        return results;
    }
    
    public static Set<String> fromArray2(final String... values) {
        final HashSet<String> results = new HashSet<String>();
        Collections.addAll(results, values);
        results.remove("");
        return results;
    }
    
    public static List<Player> fromPlayerArray(final Player... values) {
        final List<Player> results = new ArrayList<Player>();
        Collections.addAll(results, values);
        return results;
    }
    
    public static String[] toArray(final List<String> list) {
        return list.toArray(new String[list.size()]);
    }
    
    public static String[] removeFirst(final String[] args) {
        final List<String> out = fromArray(args);
        if (!out.isEmpty()) {
            out.remove(0);
        }
        return toArray(out);
    }
    
    public static String toMessage(final String[] args) {
        String out = "";
        for (final String arg : args) {
            out = String.valueOf(out) + arg + " ";
        }
        return out.trim();
    }
    
    public static String toMessage(final String[] args, final String sep) {
        String out = "";
        for (final String arg : args) {
            out = String.valueOf(out) + arg + ", ";
        }
        return stripTrailing(out, ", ");
    }
    
    public static String toMessage(final List<String> args, final String sep) {
        String out = "";
        for (final String arg : args) {
            out = String.valueOf(out) + arg + sep;
        }
        return stripTrailing(out, sep);
    }
    
    public static String parseColors(final String msg) {
        return msg.replace("&", "§");
    }
    
    public static String stripColors(final String msg) {
        String out = msg.replaceAll("[&][0-9a-f]", "");
        out = out.replaceAll(String.valueOf('\u00c2'), "");
        return out.replaceAll("[§][0-9a-f]", "");
    }
    
    public static String getLastColorCode(String msg) {
        msg = msg.replaceAll(String.valueOf('\u00c2'), "").trim();
        if (msg.length() < 2) {
            return "";
        }
        final String one = msg.substring(msg.length() - 2, msg.length() - 1);
        final String two = msg.substring(msg.length() - 1);
        if (one.equals("§")) {
            return String.valueOf(one) + two;
        }
        if (one.equals("&")) {
            return toColor(two);
        }
        return "";
    }
    
    public static String cleanTag(final String tag) {
        return stripColors(tag).toLowerCase();
    }
    
    public static String stripTrailing(final String msg, final String sep) {
        if (msg.length() < sep.length()) {
            return msg;
        }
        String out = msg;
        final String first = msg.substring(0, sep.length());
        final String last = msg.substring(msg.length() - sep.length(), msg.length());
        if (first.equals(sep)) {
            out = msg.substring(sep.length());
        }
        if (last.equals(sep)) {
            out = msg.substring(0, msg.length() - sep.length());
        }
        return out;
    }
    
    public static String generatePageSeparator(final String sep) {
        String out = "";
        for (int i = 0; i < 320; ++i) {
            out = String.valueOf(out) + sep;
        }
        return out;
    }
    
    @Deprecated
    public static boolean isOnline(final String playerName) {
        final Collection<Player> online = getOnlinePlayers();
        for (final Player o : online) {
            if (o.getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isOnline(final UUID playerUniqueId) {
        final Collection<Player> online = getOnlinePlayers();
        for (final Player o : online) {
            if (o.getUniqueId().equals(playerUniqueId)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean testURL(final String strUrl) {
        try {
            final URL url = new URL(strUrl);
            final HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.connect();
            if (urlConn.getResponseCode() != 200) {
                return false;
            }
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public static String escapeQuotes(final String str) {
        if (str == null) {
            return "";
        }
        return str.replace("'", "''");
    }
    
    public static String toLocationString(final Location loc) {
        return String.valueOf(loc.getBlockX()) + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getName();
    }
    
    public static boolean isSameBlock(final Location loc, final Location loc2) {
        return loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ();
    }
    
    public static boolean isSameLocation(final Location loc, final Location loc2) {
        return loc.getX() == loc2.getX() && loc.getY() == loc2.getY() && loc.getZ() == loc2.getZ();
    }
    
    public static Map<String, Integer> sortByValue(final Map<String, Integer> map) {
        final List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(final Map.Entry<String, Integer> o1, final Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        final Map<String, Integer> result = new LinkedHashMap<>();
        for (final Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    public static boolean isVanished(final Player player) {
        return player != null && player.hasMetadata("vanished") && !player.getMetadata("vanished").isEmpty() && player.getMetadata("vanished").get(0).asBoolean();
    }
    
    public static Collection<Player> getOnlinePlayers() {
        try {
            final Method method = Bukkit.class.getDeclaredMethod("getOnlinePlayers", (Class<?>[])new Class[0]);
            final Object players = method.invoke(null, new Object[0]);
            if (players instanceof Player[]) {
                return new ArrayList<Player>(Arrays.asList((Player[])players));
            }
            return (Collection<Player>)players;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Player>();
        }
    }
    
    public static int checkSpaceInventory(final Inventory inventory) {
        int slot = 0;
        for (int i = 0; i < inventory.getSize(); ++i) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                ++slot;
            }
        }
        return slot;
    }
    
    public static String allArgs(final int start, final String[] args) {
        String temp = "";
        for (int i = start; i < args.length; ++i) {
            temp = String.valueOf(temp) + args[i] + " ";
        }
        return temp.trim();
    }
}

