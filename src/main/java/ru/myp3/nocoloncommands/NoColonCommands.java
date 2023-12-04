package ru.myp3.nocoloncommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class NoColonCommands extends JavaPlugin implements Listener {
    private String blockedMessage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        blockedMessage = getConfig().getString("message.blocked");
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("ncc")).setExecutor(new ReloadCommand(this));
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().contains(":")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(blockedMessage);
        }
    }

    public void reload() {
        reloadConfig();
    }

}
