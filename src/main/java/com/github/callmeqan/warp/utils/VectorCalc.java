package com.github.callmeqan.warp.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;

public class VectorCalc {

    public static Location calculateFinalLocation(Location original, @Nullable Vector direction, double distance){
        if(direction == null){
            direction = original.getDirection();
        }
        double new_x = original.getX() + direction.getX() * distance;
        double new_Y = original.getY() + direction.getY() * distance;
        double new_Z = original.getZ() + direction.getZ() * distance;
        original.setX(new_x);
        original.setY(new_Y);
        original.setZ(new_Z);
        return original;
    }

    public static Location calculateFinalLocation(Location original, double distance){
        return calculateFinalLocation(original, null, distance);
    }
}
