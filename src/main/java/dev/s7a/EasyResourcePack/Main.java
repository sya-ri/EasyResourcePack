package dev.s7a.EasyResourcePack;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * プラグインのメインクラス
 */
public class Main extends JavaPlugin {
    public static JavaPlugin plugin;

    {
        plugin = this;
    }

    @Override
    public void onEnable() {
        Config.load();
        EventListener.register();
    }

    /**
     * プラグインのインスタンスを取得する
     * @return プラグインのインスタンス
     */
    public static @NotNull JavaPlugin getPlugin() {
        return plugin;
    }
}
