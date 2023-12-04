package ru.myp3.nocoloncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.Bukkit.getPluginManager;

public class ReloadCommand implements CommandExecutor {
    private final NoColonCommands plugin;

    public ReloadCommand(NoColonCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ncc")) {
            if (cmd.getName().equalsIgnoreCase("ncc")) {
                if (args.length == 0) {
                    sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("message.ncc_info")));
                    return true;
                } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("nocoloncommands.reload")) {
                        getPluginManager().disablePlugin(plugin);
                        getPluginManager().enablePlugin(plugin);
                        plugin.reload();
                        sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("message.reload")));
                    } else {
                        sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("message.no_permission")));
                    }
                    return true;
                } else {
                    sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("message.no_subcommand")));
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}