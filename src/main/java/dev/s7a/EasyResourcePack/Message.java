package dev.s7a.EasyResourcePack;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * メッセージ
 */
public class Message {
    /**
     * メッセージプリフィックス
     */
    private final static @NotNull String Prefix = ChatColor.AQUA + "[EasyResourcePack] ";

    /**
     * プレイヤーのみが実行できる時に表示するエラー
     */
    public final static @NotNull String OnlyPlayerCommandError = Prefix + ChatColor.RED + "プレイヤーのみが実行できるコマンドです";

    /**
     * プレイヤー名を入力していなかった時に表示するエラー
     */
    public final static @NotNull String NotEnterPlayerNameError = Prefix + ChatColor.RED + "プレイヤー名を入力してください";
    /**
     * URLを入力していなかった時に表示するエラー
     */
    public final static @NotNull String NotEnterURLNameError = Prefix + ChatColor.RED + "URLを入力してください";
    /**
     * コンフィグをリロードする前のメッセージ
     */
    public final static @NotNull String ReloadCommand = Prefix + ChatColor.WHITE + "コンフィグをリロードします";
    /**
     * SHA-1を再生成する前のメッセージ
     */
    public final static @NotNull String SHA1Refresh = Prefix + ChatColor.WHITE + "SHA-1を再生成します";
    /**
     * 設定を変更する前のメッセージ
     */
    public final static @NotNull String SetCommand = Prefix + ChatColor.WHITE + "設定を変更します。";
    /**
     * コマンドヘルプ
     */
    public final static @NotNull List<@NotNull String> CommandHelp = List.of(
            Prefix + ChatColor.WHITE + "コマンド一覧",
            ChatColor.GRAY + "- /" + PackCommand.name + ChatColor.GOLD + " リソースパックを再読み込みさせます",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Reload + ChatColor.GOLD + " コンフィグをリロードします",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Get + ChatColor.GOLD + " 現在の設定を確認します",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Set + ChatColor.LIGHT_PURPLE + " <URL>" + ChatColor.GOLD + " リソースパックのURLを変更します",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Update + ChatColor.GOLD + " リソースパックのSHA-1を再生成します",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Force + ChatColor.GOLD + " 指定したプレイヤーのリソースパックを再読み込みさせます",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Force + ChatColor.LIGHT_PURPLE + " <URL>" + ChatColor.GOLD + " 指定したプレイヤーのリソースパックを変更します",
            ChatColor.GRAY + "- /" + PackCommand.name + " " + PackCommand.Argument.Help + ChatColor.GOLD + " コマンドヘルプを表示します"
    );

    /**
     * プレイヤーが見つからなかった時に表示するエラー
     *
     * @param name プレイヤー名
     */
    public static @NotNull String NotFoundPlayerNameError(@NotNull String name) {
        return Prefix + ChatColor.RED + name + " というプレイヤーは見つかりませんでした";
    }

    /**
     * パックをプレイヤーに適用する前のメッセージ
     *
     * @param name プレイヤー名
     */
    public static @NotNull String ApplyPackToPlayer(@NotNull String name) {
        return Prefix + ChatColor.WHITE + name + " のリソースパックを再読み込みします";
    }

    /**
     * パックをプレイヤーに適用する前のメッセージ
     *
     * @param name プレイヤー名
     * @param url  ダウンロードURL
     * @param sha1 ハッシュ値
     */
    public static @NotNull List<@NotNull String> ApplyPackToPlayer(@NotNull String name, @NotNull String url, @NotNull String sha1) {
        return List.of(
                Prefix + ChatColor.WHITE + name + " のリソースパックを変更します",
                ChatColor.GRAY + "URL: " + ChatColor.GREEN + url,
                ChatColor.GRAY + "SHA1: " + ChatColor.GREEN + sha1
        );
    }

    /**
     * 設定の確認メッセージ
     *
     * @param url  ダウンロードURL
     * @param sha1 ハッシュ値
     * @return メッセージ
     */
    public static @NotNull List<@NotNull String> GetCommand(@Nullable String url, @NotNull String sha1) {
        return List.of(
                Prefix + ChatColor.WHITE + "現在の設定",
                ChatColor.GRAY + "URL: " + ChatColor.GREEN + url,
                ChatColor.GRAY + "SHA1: " + ChatColor.GREEN + sha1
        );
    }
}
