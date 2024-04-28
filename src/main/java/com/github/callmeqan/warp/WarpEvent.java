package com.github.callmeqan.warp;

import com.github.callmeqan.warp.utils.LoggerUtil;
import com.github.callmeqan.warp.utils.VectorCalc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class WarpEvent implements Listener {
    private static final Logger LOGGER = WarpWeapon.LOGGER;
    private static final double maxDistanceTravel = 12.0;

    private static final HashMap<String, Boolean> playersActive = new HashMap<>();

    public static HashMap<String, Boolean> getPlayersActive(){return playersActive;}

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getItem() == null || e.getItem().getItemMeta() == null) return;
            if(e.getItem().getItemMeta().equals(WarpItem.warp.getItemMeta())){

                Player player = e.getPlayer();
                Location playerLocation = player.getLocation();
                // Temp location
                Location newLocation = VectorCalc.calculateFinalLocation(playerLocation, maxDistanceTravel);

                // Checking if touched a block
                RayTraceResult res = player.rayTraceBlocks(maxDistanceTravel);

                boolean teleported;
                if(res != null){
                    assert res.getHitBlock() != null;
                    if(!res.getHitBlock().isLiquid()){
                        newLocation = calcNewDistance(res, playerLocation, newLocation);
                        if(newLocation == null) return;
                        newLocation.setDirection(playerLocation.getDirection());
                        LOGGER.info(String.valueOf(playerLocation.distance(newLocation)));

                        if(playerLocation.distance(newLocation) <= 3.0){
                            player.sendMessage(Component.text("The distance is too short")
                                    .color(TextColor.color(255, 0, 0))
                            );
                            return;
                            // Skip because distance are too short
                        }
                    }
                }
                teleported = player.teleport(newLocation);
                if (teleported){
                    player.playSound(player, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.2f, player.getPitch());
                    String uuid = Objects.requireNonNull(player.getPlayerProfile().getId()).toString();
                    if(playersActive.get(uuid) == null || !playersActive.get(uuid)){
                        playersActive.put(uuid, true);
                    }
                }
            }
        }
    }

    @Nullable
    private static Location calcNewDistance(RayTraceResult res, Location playerLocation, Location appoxLocation) {
        assert res.getHitBlock() != null;
        assert res.getHitBlockFace() != null;

        Location block = res.getHitBlock().getLocation();
        double i = block.distance(appoxLocation);
        LOGGER.info("i: "+i);
        LoggerUtil.printLocation(block);
        if(maxDistanceTravel - i <= 0.0){
            return null;
        }
        return VectorCalc.calculateFinalLocation(playerLocation, maxDistanceTravel - i);
    }
}
