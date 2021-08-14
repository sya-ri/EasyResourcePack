package dev.s7a.EasyResourcePack;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

/**
 * コンフィグ - config.yml
 */
public class Config {
    private static @Nullable String url;

    /**
     * コンフィグを読み込む
     */
    public static void load() {
        JavaPlugin plugin = Main.getPlugin();
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        url = config.getString("url");
    }

    public static @Nullable String getUrl() {
        return url;
    }
}
