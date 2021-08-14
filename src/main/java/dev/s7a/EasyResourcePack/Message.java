package dev.s7a.EasyResourcePack;

import org.bukkit.ChatColor;

/**
 * メッセージ
 */
public class Message {
    /**
     * メッセージプリフィックス
     */
    private final static String Prefix = ChatColor.AQUA + "[EasyResourcePack] ";

    /**
     * プレイヤーのみが実行できる時に表示するエラー
     */
    public final static String OnlyPlayerCommandError = Prefix + ChatColor.RED + "プレイヤーのみが実行できるコマンドです";
}
