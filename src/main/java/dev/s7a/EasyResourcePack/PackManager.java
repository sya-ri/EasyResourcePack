package dev.s7a.EasyResourcePack;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * パックの管理をする
 */
public class PackManager {
    public static void applyToPlayer(@NotNull Player player) {
        String url = Config.getUrl();
        if (url != null) {
            player.setResourcePack(url);
        }
    }
}
