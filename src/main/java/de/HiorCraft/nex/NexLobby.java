package de.HiorCraft.nex;

import de.HiorCraft.nex.commands.TestCommand;
import de.HiorCraft.nex.double_jump.DoubleJump;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class NexLobby extends JavaPlugin {

    @Override
    public void onEnable() {


        // The Logo and Version
        NexLobby instance = this;
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

        new TestCommand("test").register();

        // Register
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        //getServer().getPluginManager().registerEvents(new SetHub(getDataFolder()), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("bye <3");
        getLogger().info("Essential SMP Plugin is stopping...");
    }
}
