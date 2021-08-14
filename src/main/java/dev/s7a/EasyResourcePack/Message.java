package dev.s7a.EasyResourcePack;

import org.bukkit.ChatColor;

import java.util.List;

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

    /**
     * コンフィグをリロードする前のメッセージ
     */
    public final static String ReloadCommand = Prefix + ChatColor.WHITE + "コンフィグをリロードします";

    /**
     * コマンドヘルプ
     */
    public final static List<String> CommandHelp = List.of(
            Prefix + ChatColor.WHITE + "コマンド一覧",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Reload + ChatColor.GOLD + " コンフィグをリロードします"
    );
}
