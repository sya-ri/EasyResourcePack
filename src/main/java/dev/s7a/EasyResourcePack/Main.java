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

    /**
     * プラグインのインスタンスを取得する
     *
     * @return プラグインのインスタンス
     */
    public static @NotNull JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        ServerProperties.check();
        Config.load();
        EventListener.register();
        PackCommand.register();
        PackManager.refreshHashOnEnable();
        PluginMessageListeners.register();
    }
}
