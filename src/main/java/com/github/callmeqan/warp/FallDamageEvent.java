package com.github.callmeqan.warp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Objects;

public class FallDamageEvent implements Listener {
    @EventHandler
    public static void onFallDamage(EntityDamageEvent e){
        HashMap<String, Boolean> playerActives = WarpEvent.getPlayersActive();
        if (e.getEntity() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getItemMeta() != null){
                if(player.getInventory().getItemInMainHand().getItemMeta().equals(WarpItem.warp.getItemMeta())){
                    String uuid = Objects.requireNonNull(player.getPlayerProfile().getId()).toString();
                    if(playerActives.get(uuid) != null && playerActives.get(uuid)){
                        e.setDamage(1);
                        playerActives.replace(uuid, false);
                    }
                }
            }
        }
    }
}
