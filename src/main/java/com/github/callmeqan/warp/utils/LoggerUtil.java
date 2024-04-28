package com.github.callmeqan.warp.utils;

import com.github.callmeqan.warp.WarpWeapon;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger LOGGER = WarpWeapon.LOGGER;

    public static void printVector(Vector vector){
        printCoord(vector.getX(), vector.getY(), vector.getZ());
    }
    public static void printLocation(Location location){
        printCoord(location.x(), location.y(), location.z());
    }

    public static void printCoord(double x, double y, double z){
        LOGGER.info(x+" "+y+" "+z);
    }
}
