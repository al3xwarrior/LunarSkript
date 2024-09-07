package com.al3x.lunarSkript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class LunarSkript extends JavaPlugin {

    LunarSkript instance;
    SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);

        try {
            addon.loadClasses("com.al3x.lunarSkript", "elements");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("[LunarSkript] Enabled! (By Al3x)");
    }

    public LunarSkript getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}
