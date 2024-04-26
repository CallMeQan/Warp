package com.github.callmeqan.warp;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import java.util.logging.Logger;

public class WarpEvent implements Listener {
    private static final Logger LOGGER = WarpWeapon.LOGGER;
    private static Location calcFinalLocation(Location original){
        double t = 12.0; // Change this only

        Vector direction = original.getDirection();
        double new_x = original.getX() + direction.getX() * t;
        double new_Y = original.getY() + direction.getY() * t;
        double new_Z = original.getZ() + direction.getZ() * t;
        original.setX(new_x);
        original.setY(new_Y);
        original.setZ(new_Z);
        return original;
    }
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getItem() == null) return;
            if(e.getItem().getItemMeta().equals(WarpItem.warp.getItemMeta())){
                LOGGER.info("Is running");

                Player player = e.getPlayer();
                Location playerLocation = player.getLocation();
                boolean teleported = player.teleport(calcFinalLocation(playerLocation));
                if (teleported){
                    player.playSound(player, Sound.BLOCK_GLASS_BREAK, 0.2f, player.getPitch());
                }
                player.setFallDistance(1);
            }
        }
    }

    @EventHandler
    public static void onFallDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getItemMeta().equals(WarpItem.warp.getItemMeta())){
                e.setDamage(0.5);
                // Reduce damage fall
            }
        }
    }
}
