package dev.s7a.EasyResourcePack;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * コマンド /pack
 */
public class PackCommand implements CommandExecutor, TabCompleter {
    /**
     * コマンド名
     */
    public static final @NotNull String name = "pack";

    /**
     * コマンドの登録を行う
     */
    public static void register() {
        JavaPlugin plugin = Main.getPlugin();
        PluginCommand command = plugin.getCommand(name);
        if (command != null) {
            PackCommand packCommand = new PackCommand();
            command.setExecutor(packCommand);
            command.setTabCompleter(packCommand);
        }
    }

    /**
     * タブ補完を実行します
     *
     * @param arguments 全候補
     * @param arg       入力中の引数
     * @return 候補に一致する引数
     */
    private static @NotNull List<@NotNull String> complete(@NotNull List<@NotNull String> arguments, @NotNull String arg) {
        return arguments.stream().filter((a) -> a.startsWith(arg)).collect(Collectors.toList());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        int argsLength = args.length;
        if (argsLength < 1 || !sender.hasPermission(Permission.AdminCommand)) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PackManager.applyToPlayer(player);
            } else {
                sender.sendMessage(Message.OnlyPlayerCommandError);
            }
        } else {
            String arg0 = args[0].toLowerCase();
            switch (arg0) {
                case Argument.Reload: {
                    sender.sendMessage(Message.ReloadCommand);
                    String lastUrl = Config.getUrl();
                    Config.load();
                    String url = Config.getUrl();
                    PackManager.refreshHashIfChanged(sender, lastUrl, url);
                    break;
                }
                case Argument.Get: {
                    String url = Config.getUrl();
                    byte[] sha1 = Config.getSha1();
                    String sha1Text = HashUtil.bytesToString(sha1);
                    sender.sendMessage(Message.GetCommand(url, sha1Text).toArray(new String[0]));
                    break;
                }
                case Argument.Set: {
                    if (argsLength < 2) {
                        sender.sendMessage(Message.NotEnterURLNameError);
                    } else {
                        sender.sendMessage(Message.SetCommand);
                        String lastUrl = Config.getUrl();
                        String url = args[1];
                        Config.setUrl(url);
                        PackManager.refreshHashIfChanged(sender, lastUrl, url);
                    }
                    break;
                }
                case Argument.Update: {
                    sender.sendMessage(Message.SHA1Refresh);
                    BukkitScheduler scheduler = Bukkit.getScheduler();
                    JavaPlugin plugin = Main.getPlugin();
                    scheduler.runTaskAsynchronously(plugin, PackManager::refreshHash);
                    break;
                }
                case Argument.Force: {
                    if (argsLength < 2) {
                        sender.sendMessage(Message.NotEnterPlayerNameError);
                    } else {
                        String arg1 = args[1];
                        String targetName;
                        Collection<? extends Player> players;
                        if (arg1.equals("@a")) {
                            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                            targetName = "全プレイヤー(" + onlinePlayers.size() + ")";
                            players = onlinePlayers;
                        } else {
                            Player targetPlayer = Bukkit.getPlayer(arg1);
                            if (targetPlayer != null) {
                                targetName = targetPlayer.getName();
                                players = List.of(targetPlayer);
                            } else {
                                sender.sendMessage(Message.NotFoundPlayerNameError(arg1));
                                break;
                            }
                        }
                        if (argsLength < 3) {
                            sender.sendMessage(Message.ApplyPackToPlayer(targetName));
                            players.forEach(PackManager::applyToPlayer);
                        } else {
                            String url = args[2];
                            byte[] sha1 = PackManager.hash(url);
                            sender.sendMessage(Message.ApplyPackToPlayer(targetName, url, HashUtil.bytesToString(sha1)).toArray(new String[0]));
                            players.forEach(player -> PackManager.applyToPlayer(player, url, sha1));
                        }
                    }
                    break;
                }
                default: {
                    sender.sendMessage(Message.CommandHelp.toArray(new String[0]));
                }
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender.hasPermission(Permission.AdminCommand)) {
            int argsLength = args.length;
            if (argsLength == 0) {
                return Argument.all;
            } else if (argsLength == 1) {
                String arg0 = args[0].toLowerCase();
                return complete(Argument.all, arg0);
            } else if (argsLength == 2) {
                String arg0 = args[0].toLowerCase();
                if (arg0.equals(Argument.Force)) {
                    Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
                    List<String> onlinePlayerNames = onlinePlayers.stream().map(Player::getName).collect(Collectors.toList());
                    onlinePlayerNames.add("@a");
                    return complete(onlinePlayerNames, args[1]);
                }
            }
        }
        return List.of();
    }

    public static class Argument {
        public final static @NotNull String Reload = "reload";
        public final static @NotNull String Get = "get";
        public final static @NotNull String Set = "set";
        public final static @NotNull String Update = "update";
        public final static @NotNull String Force = "force";
        public final static @NotNull String Help = "help";

        public final static @NotNull List<@NotNull String> all = List.of(
                Reload,
                Get,
                Set,
                Update,
                Force,
                Help
        );
    }
}
