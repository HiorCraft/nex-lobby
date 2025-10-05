package de.HiorCraft.nex.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("NexLobby").getConfig();
        World world = Bukkit.getWorld(config.getString("hub.world"));
        if (world == null) return;
        double x = config.getDouble("hub.x");
        double y = config.getDouble("hub.y");
        double z = config.getDouble("hub.z");
        float yaw = (float) config.getDouble("hub.yaw");
        float pitch = (float) config.getDouble("hub.pitch");

        Location hubLocation = new Location(world, x, y, z, yaw, pitch);
        event.getPlayer().teleport(hubLocation);
        event.getPlayer().sendMessage("§6Willkommen zurück, §e" + event.getPlayer().getName() + "§6!");
    }

}