package dev.s7a.EasyResourcePack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * イベント処理を行う
 */
public class EventListener implements Listener {
    /**
     * イベントの登録を行う
     */
    public static void register() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        JavaPlugin plugin = Main.getPlugin();
        pluginManager.registerEvents(new EventListener(), plugin);
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        JavaPlugin plugin = Main.getPlugin();
        scheduler.runTask(plugin, () -> PackManager.applyToPlayer(player));
    }
}
