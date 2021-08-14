package dev.s7a.EasyResourcePack;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        public final static List<String> all = List.of(
                Reload
        );
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1 || !sender.hasPermission(Permission.AdminCommand)) {
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
            if (argsLength < 1) {
                return Argument.all;
            } else if (argsLength == 1) {
                String arg0 = args[0].toLowerCase();
                return Argument.all.stream().filter((arg) -> arg.startsWith(arg0)).collect(Collectors.toList());
            }
        }
        return List.of();
    }
}
