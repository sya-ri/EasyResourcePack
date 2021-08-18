package dev.s7a.EasyResourcePack;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * サーバープロパティを操作します
 */
public class ServerProperties {
    /**
     * リソースパックが設定されていれば警告を表示する
     */
    public static void check() {
        try {
            Class<?> MinecraftServerClass = Class.forName("net.minecraft.server.MinecraftServer");
            Method GetMinecraftServerMethod = MinecraftServerClass.getMethod("getServer");
            Method GetResourcePackMethod = MinecraftServerClass.getMethod("getResourcePack");
            Object minecraftServer = GetMinecraftServerMethod.invoke(null);
            String url = (String) GetResourcePackMethod.invoke(minecraftServer);
            if (url != null && 0 < url.length()) {
                JavaPlugin plugin = Main.getPlugin();
                Logger logger = plugin.getLogger();
                logger.warning("server.properties の resource-pack が設定されています。設定を削除して /pack set " + url + " を実行してください。");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
