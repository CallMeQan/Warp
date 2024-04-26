package com.github.callmeqan.warp;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class PlayerDataStoring {
    private static final HashMap<String, Integer> players = new HashMap<>();
    public static void setCounter(String uuid, int num){
        if(players.containsKey(uuid)){
            players.replace(uuid,num);
        }
        players.put(uuid, num);
    }
    public static int getCounter(String uuid){
        if(players.containsKey(uuid)){
            return players.get(uuid);
        }
        return 0;
    }
}
