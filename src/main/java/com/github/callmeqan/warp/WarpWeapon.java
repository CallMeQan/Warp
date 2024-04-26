package com.github.callmeqan.warp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class WarpWeapon extends JavaPlugin {
    private static WarpWeapon INSTANCE;
    public static final Logger LOGGER = Logger.getLogger("WarpWeapon");

    public static WarpWeapon getINSTANCE() {return INSTANCE;}

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        WarpItem.init();
        Objects.requireNonNull(getCommand("warpweapon")).setExecutor(new WarpCommand());
        getServer().getPluginManager().registerEvents(new WarpEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
