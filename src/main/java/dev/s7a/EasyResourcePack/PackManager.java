package dev.s7a.EasyResourcePack;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * パックの管理をする
 */
public class PackManager {
    /**
     * プレイヤーにリソースパックを適用する
     *
     * @param player 適用するプレイヤー
     */
    public static void applyToPlayer(@NotNull Player player) {
        String url = Config.getUrl();
        if (url != null) {
            byte[] sha1 = Config.getSha1();
            if (sha1 != null) {
                player.setResourcePack(url, sha1);
            } else {
                player.setResourcePack(url);
            }
        }
    }

    /**
     * リソースパックをダウンロードする
     */
    private static boolean downloadPack(@NotNull String url, @NotNull String outputPath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputPath)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * SHA1 を更新する
     */
    public static void refreshHash() {
        String url = Config.getUrl();
        if (url != null && !url.isBlank()) {
            Config.setSha1(null);
            try {
                File outputFile = File.createTempFile("temp_", ".zip");
                String outputPath = outputFile.getAbsolutePath();
                if (downloadPack(url, outputPath)) {
                    byte[] sha1 = HashUtil.sha1(outputPath);
                    String sha1Text = HashUtil.bytesToString(sha1);
                    Config.setSha1(sha1);
                    JavaPlugin plugin = Main.getPlugin();
                    plugin.getLogger().info("リソースパックのSHA-1を更新しました: " + sha1Text);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Config.setSha1(null);
        }
    }

    /**
     * サーバー起動時に SHA1 を更新する
     */
    public static void refreshHashOnEnable() {
        byte[] sha1 = Config.getSha1();
        if (sha1 == null) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            JavaPlugin plugin = Main.getPlugin();
            scheduler.runTaskAsynchronously(plugin, PackManager::refreshHash);
        }
    }

    /**
     * URLが変更されていたら SHA1 を更新する
     *
     * @param sender  メッセージを送信する先
     * @param lastUrl 変更前のURL
     * @param url     変更後のURL
     */
    public static void refreshHashIfChanged(@NotNull CommandSender sender, @Nullable String lastUrl, @Nullable String url) {
        byte[] sha1 = Config.getSha1();
        if (!Objects.equals(lastUrl, url) || sha1 == null) {
            sender.sendMessage(Message.SHA1Refresh);
            BukkitScheduler scheduler = Bukkit.getScheduler();
            JavaPlugin plugin = Main.getPlugin();
            scheduler.runTaskAsynchronously(plugin, PackManager::refreshHash);
        }
    }
}
