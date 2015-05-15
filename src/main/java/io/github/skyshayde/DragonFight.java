package io.github.skyshayde;

import org.spongepowered.api.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Skyshayde.
 * skyshayde.github.io
 */

public class DragonFight {

    public static boolean isDragonNew(String DragonID) {
        return false;
    }

    public static DragonFight newFight(String dragonID, String playername) {
        if(!dragons.contains(dragonID)) {
            DragonFight fight = new DragonFight(dragonID);
            return new DragonFight(dragonID);
        } else {
            return null;
        }
    }

    static List<Player> players = new ArrayList<Player>();
    static List<String> dragons = new ArrayList<String>();
    static HashMap<String,DragonFight> fights = new HashMap<>();

    public DragonFight(String dragonID){
        dragons.add(dragonID);
    }
}
