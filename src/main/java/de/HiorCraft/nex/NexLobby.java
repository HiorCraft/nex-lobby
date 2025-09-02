package de.HiorCraft.nex;

import de.HiorCraft.nex.commands.CreatHubCommand;
import de.HiorCraft.nex.listener.DoubleJump;
import de.HiorCraft.nex.listener.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class NexLobby extends JavaPlugin {


    @Override
    public void onEnable() {
      getLogger().info("NexLobby Plugin is starting...");

        new CreatHubCommand("setHub", this).register();

        //CommandManager
        PluginManager manager = getServer().getPluginManager();


        // Register
        manager.registerEvents(new DoubleJump(), this);
        manager.registerEvents(new PlayerJoin(), this);


        saveDefaultConfig();

        getLogger().info("NexLobby Plugin has started successfully");
    }

    @Override
    public void onDisable() {
        getLogger().info("NexLobby Plugin is stopping...");


        getLogger().info("bye <3");
    }
}
