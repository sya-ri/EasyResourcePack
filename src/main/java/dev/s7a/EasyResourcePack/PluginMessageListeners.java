package dev.s7a.EasyResourcePack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/**
 * プラグインメッセージを使ってリソースパックの更新を行う
 */
public class PluginMessageListeners implements PluginMessageListener {
    /**
     * リスナーの登録をする
     */
    public static void register() {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(Main.getPlugin(), "easyresourcepack:force", new PluginMessageListeners());
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte[] message) {
        if (!Config.usePluginMessage) return;
        ByteArrayDataInput data = ByteStreams.newDataInput(message);
        String playerName = data.readUTF();
        Player target = Bukkit.getPlayer(playerName);
        if (target != null) {
            PackManager.applyToPlayer(target);
        }
    }
}
