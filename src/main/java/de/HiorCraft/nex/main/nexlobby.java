package de.HiorCraft.nex.main;

import de.HiorCraft.nex.double_jump.DoubleJump;
import de.HiorCraft.nex.hub.SetHub;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.ConsoleCommandSender;


public final class nexlobby extends JavaPlugin {

    @Override
    public void onEnable() {


        // The Logo and Version
        nexlobby instance = this;
        String green = "§a";
        ConsoleCommandSender console = getServer().getConsoleSender();
        String versionLine = "Plugin-Version: " + getDescription().getVersion();
        String[] logo = {
                green + " _   _  _______ __   __  ____   _____  _____  _____  _____",
                green + "| \\ | ||__   __|\\ \\ / / / __ \\ |  __ \\|_   _|/ ____|/ ____|",
                green + "|  \\| |   | |    \\ V / | |  | || |__) | | | | (___ | (___  ",
                green + "| . ` |   | |     > <  | |  | ||  _  /  | |  \\___ \\ \\___ \\ ",
                green + "| |\\  |   | |    / . \\ | |__| || | \\ \\ _| |_ ____) |____) |",
                green + "|_| \\_|   |_|   /_/ \\_\\____/ |_|  \\_\\_____|_____/|_____/ "
        };

        for (String line : logo) {
            console.sendMessage(line);
        }
        console.sendMessage("§6" + versionLine);



        // Register
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new SetHub(getDataFolder()), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Essential SMP Plugin is stopping...");
    }
}
