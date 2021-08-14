package dev.s7a.EasyResourcePack;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * コマンド /pack
 */
public class PackCommand implements CommandExecutor, TabCompleter {
    /**
     * コマンドの登録を行う
     */
    public static void register() {
        JavaPlugin plugin = Main.getPlugin();
        PluginCommand command = plugin.getCommand("pack");
        if (command != null) {
            PackCommand packCommand = new PackCommand();
            command.setExecutor(packCommand);
            command.setTabCompleter(packCommand);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PackManager.applyToPlayer(player);
            } else {
                sender.sendMessage(Message.OnlyPlayerCommandError);
            }
        } else {
            sender.sendMessage("Not Implemented");
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
