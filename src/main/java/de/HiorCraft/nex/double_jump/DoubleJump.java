package de.HiorCraft.nex.double_jump;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {
    @EventHandler
    public void onPlayerTouch(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.SURVIVAL)
            return;

        Material blockType = player.getLocation().clone().subtract(0.0, 1.0, 0.0).getBlock().getType();

        if (blockType == Material.AIR)
            return;

        player.setAllowFlight(true);
        player.setFlying(false);
        player.setFallDistance(0.0f);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.SURVIVAL)
            return;

        event.setCancelled(true);
        player.setAllowFlight(false);

        // Explosion Partikel
        player.spawnParticle(Particle.EXPLOSION, player.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

        // Sprung-Boost
        Vector direction = player.getLocation().getDirection().multiply(2).setY(1);
        player.setVelocity(direction);

        player.setFallDistance(0.0f);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
}





