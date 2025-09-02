package de.HiorCraft.nex.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.LocationArgument;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CreatHubCommand extends CommandAPICommand {
    public CreatHubCommand(String commandName, JavaPlugin plugin) {
        super(commandName);

        withPermission("nexlobby.sethub");

        withOptionalArguments(new LocationArgument("location"));
        withOptionalArguments(new FloatArgument("pitch"));
        withOptionalArguments(new FloatArgument("yaw"));

        executesPlayer((player, args) -> {

            YamlConfiguration config = (YamlConfiguration) plugin.getConfig();

            Location location = (Location) (args.get(0));
            Float pitch = (Float) args.get(1);
            Float yaw = (Float) args.get(2);

            if (location == null) location = player.getLocation();
            if (yaw == null) yaw = player.getYaw();
            if (pitch == null) pitch = player.getPitch();

            config.set("hub.world", location.getWorld().getName());
            config.set("hub.x", location.getX());
            config.set("hub.y", location.getY());
            config.set("hub.z", location.getZ());
            config.set("hub.yaw", yaw);
            config.set("hub.pitch", pitch);

            plugin.saveConfig();

            player.sendMessage("§6Hub-Position wurde erfolgreich gesetzt.");

        });
    }
}
