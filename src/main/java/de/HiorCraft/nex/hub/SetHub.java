package de.HiorCraft.nex.hub;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class SetHub implements Listener {

    private File hubFile;
    private FileConfiguration hubConfig;

    public SetHub(File dataFolder) {
        createHubFile(dataFolder);
    }

    private void createHubFile(File dataFolder) {
        hubFile = new File(dataFolder, "hub.yml");
        if (!hubFile.exists()) {
            dataFolder.mkdirs();
            // Hier ggf. eine leere Datei anlegen:
            try { hubFile.createNewFile(); } catch (IOException ignored) {}
        }
        hubConfig = YamlConfiguration.loadConfiguration(hubFile);
    }

    public boolean handleSetHubCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("sethub")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cNur Spieler können diesen Befehl ausführen.");
                return true;
            }

            Player player = (Player) sender;
            if (!player.hasPermission("nexlobby.sethub")) {
                player.sendMessage("§cDu hast keine Rechte, um diesen Befehl auszuführen.");
                return true;
            }

            Location loc = player.getLocation();
            hubConfig.set("hub.world", loc.getWorld().getName());
            hubConfig.set("hub.x", loc.getX());
            hubConfig.set("hub.y", loc.getY());
            hubConfig.set("hub.z", loc.getZ());
            hubConfig.set("hub.yaw", loc.getYaw());
            hubConfig.set("hub.pitch", loc.getPitch());

            try {
                hubConfig.save(hubFile);
                player.sendMessage("§6Hub-Position wurde erfolgreich gesetzt.");
            } catch (IOException e) {
                player.sendMessage("§cFehler beim Speichern der Hub-Position.");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (hubFile == null || !hubFile.exists()) return;

        String worldName = hubConfig.getString("hub.world");
        World world = Bukkit.getWorld(worldName);
        if (world == null) return;

        double x = hubConfig.getDouble("hub.x");
        double y = hubConfig.getDouble("hub.y");
        double z = hubConfig.getDouble("hub.z");
        float yaw = (float) hubConfig.getDouble("hub.yaw");
        float pitch = (float) hubConfig.getDouble("hub.pitch");

        Location hubLocation = new Location(world, x, y, z, yaw, pitch);
        event.getPlayer().teleport(hubLocation);
        event.getPlayer().sendMessage("§6Willkommen im Hub!");
    }
}