package net.arfay.factions.api;

public class StringUtil
{
    public static String getMessage(final String[] msg) {
        return String.join(" ", (CharSequence[])msg);
    }
}

