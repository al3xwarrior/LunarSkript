package com.al3x.lunarSkript;

public class ColorTranslator {

    private static String getColorCode(String color) {
        switch (color.toUpperCase()) {
            case "GREEN": return "&a";
            case "AQUA": return "&b";
            case "RED": return "&c";
            case "PURPLE": return "&d";
            case "YELLOW": return "&e";

            case "BLACK": return "&0";
            case "DARK_BLUE": return "&1";
            case "DARK_GREEN": return "&2";
            case "DARK_AQUA": return "&3";
            case "DARK_RED": return "&4";
            case "DARK_PURPLE": return "&5";
            case "GOLD": return "&6";
            case "GRAY": return "&7";
            case "DARK_GRAY": return "&8";
            case "BLUE": return "&9";

            default: return "&f";
        }
    }


    // Translate color codes to java.awt.Color
    public static java.awt.Color getJavaColor(String color) {

        String colorCode = getColorCode(color);

        switch (colorCode.substring(1)) {
            case "a": return java.awt.Color.GREEN;
            case "b": return java.awt.Color.CYAN;
            case "c": return java.awt.Color.RED;
            case "d": return java.awt.Color.PINK;
            case "e": return java.awt.Color.YELLOW;

            case "0": return java.awt.Color.BLACK;
            case "1": return java.awt.Color.BLUE;
            case "2": return java.awt.Color.GREEN.darker();
            case "3": return java.awt.Color.CYAN.darker();
            case "4": return java.awt.Color.RED.darker();
            case "5": return java.awt.Color.MAGENTA.darker();
            case "6": return java.awt.Color.ORANGE;
            case "7": return java.awt.Color.GRAY;
            case "8": return java.awt.Color.DARK_GRAY;
            case "9": return java.awt.Color.BLUE.darker();

            default: return java.awt.Color.WHITE;
        }
    }

    // Translate color codes to org.bukkit.Color
    public static org.bukkit.Color getBukkitColor(String colorcode) {
        switch (colorcode.substring(1)) {
            case "a": return org.bukkit.Color.LIME;
            case "b": return org.bukkit.Color.AQUA;
            case "c": return org.bukkit.Color.RED;
            case "d": return org.bukkit.Color.PURPLE;
            case "e": return org.bukkit.Color.YELLOW;

            case "0": return org.bukkit.Color.BLACK;
            case "1": return org.bukkit.Color.BLUE;
            case "2": return org.bukkit.Color.LIME;
            case "3": return org.bukkit.Color.AQUA;
            case "4": return org.bukkit.Color.RED;
            case "5": return org.bukkit.Color.PURPLE;
            case "6": return org.bukkit.Color.ORANGE;
            case "7": return org.bukkit.Color.GRAY;
            case "8": return org.bukkit.Color.fromRGB(64, 64, 64); // Dark gray
            case "9": return org.bukkit.Color.fromRGB(0, 0, 139);  // Dark blue (manual darker color)

            default: return org.bukkit.Color.WHITE;
        }
    }
}

