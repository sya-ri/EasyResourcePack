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
    public static final String name = "pack";

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

    public static class Argument {
        public final static String Reload = "reload";
        public final static String Refresh = "refresh";
        public final static String Force = "force";
        public final static String Help = "help";

        public final static List<String> all = List.of(
                Reload,
                Refresh,
                Force,
                Help
        );
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
                    Config.load();
                    break;
                }
                case Argument.Refresh: {
                    sender.sendMessage(Message.RefreshCommand);
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
                        if (arg1.equals("@a")) {
                            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                            sender.sendMessage(Message.ApplyPackToPlayer("全プレイヤー(" + onlinePlayers.size() + ")"));
                            onlinePlayers.forEach(PackManager::applyToPlayer);
                        } else {
                            Player targetPlayer = Bukkit.getPlayer(arg1);
                            if (targetPlayer != null) {
                                String targetPlayerName = targetPlayer.getName();
                                sender.sendMessage(Message.ApplyPackToPlayer(targetPlayerName));
                                PackManager.applyToPlayer(targetPlayer);
                            } else {
                                sender.sendMessage(Message.NotFoundPlayerNameError(arg1));
                            }
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

    /**
     * タブ補完を実行します
     *
     * @param arguments 全候補
     * @param arg       入力中の引数
     * @return 候補に一致する引数
     */
    private static List<String> complete(List<String> arguments, String arg) {
        return arguments.stream().filter((a) -> a.startsWith(arg)).collect(Collectors.toList());
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
}
