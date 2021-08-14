package dev.s7a.EasyResourcePack;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * コンフィグ - config.yml
 */
public class Config {
    private static @Nullable String url;
    public static byte @Nullable [] sha1;

    /**
     * コンフィグを読み込む
     */
    public static void load() {
        JavaPlugin plugin = Main.getPlugin();
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        url = config.getString("url");
        if (url != null && !url.isBlank()) {
            String sha1Text = config.getString("sha1");
            Logger logger = plugin.getLogger();
            if (sha1Text != null && sha1Text.length() != 40) {
                logger.warning("不正な長さのSHA-1です: " + sha1Text);
                sha1 = null;
            } else {
                sha1 = HashUtil.stringToBytes(sha1Text);
                if (!Objects.equals(sha1Text, HashUtil.bytesToString(sha1))) {
                    logger.warning("不正なSHA-1です: " + sha1Text);
                    sha1 = null;
                }
            }
        } else {
            sha1 = null;
        }
    }

    public static @Nullable String getUrl() {
        return url;
    }

    public static byte @Nullable [] getSha1() {
        return sha1;
    }

    public static void setSha1(byte @Nullable [] sha1) {
        JavaPlugin plugin = Main.getPlugin();
        FileConfiguration config = plugin.getConfig();
        config.set("sha1", HashUtil.bytesToString(sha1));
        plugin.saveConfig();
        Config.sha1 = sha1;
    }
}
