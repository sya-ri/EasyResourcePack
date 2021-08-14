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
     * プレイヤー名を入力していなかった時に表示するエラー
     */
    public final static String NotEnterPlayerNameError = Prefix + ChatColor.RED + "プレイヤー名を入力してください";

    /**
     * プレイヤーが見つからなかった時に表示するエラー
     *
     * @param name プレイヤー名
     */
    public static String NotFoundPlayerNameError(String name) {
        return Prefix + ChatColor.RED + name + " というプレイヤーは見つかりませんでした";
    }

    /**
     * パックをプレイヤーに適用する前のメッセージ
     *
     * @param name プレイヤー名
     */
    public static String ApplyPackToPlayer(String name) {
        return Prefix + ChatColor.WHITE + name + " のリソースパックを再読み込みします";
    }

    /**
     * コンフィグをリロードする前のメッセージ
     */
    public final static String ReloadCommand = Prefix + ChatColor.WHITE + "コンフィグをリロードします";

    /**
     * コマンドヘルプ
     */
    public final static List<String> CommandHelp = List.of(
            Prefix + ChatColor.WHITE + "コマンド一覧",
            ChatColor.GRAY + "- /" + PackCommand.name + ChatColor.GOLD + " リソースパックを再読み込みさせます",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Reload + ChatColor.GOLD + " コンフィグをリロードします",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Force + ChatColor.GOLD + " 指定したプレイヤーのリソースパックを再読み込みさせます"
    );
}
