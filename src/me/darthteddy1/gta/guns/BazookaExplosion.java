package me.darthteddy1.gta.guns;

import me.darthteddy1.gta.Core;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BazookaExplosion extends BukkitRunnable {

    List<BlockState> states;

    public BazookaExplosion(ArrayList<BlockState> blocks){
        states = blocks;
    }

    public BazookaExplosion(List<Block> blocks){
        states = new ArrayList<>();
        for(Block b : blocks){
            states.add(b.getState());
        }
    }

    @Override
    public void run() {
        regen();
    }

    public void regen(){
        for(final BlockState state : states){
            Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable() {

                @Override
                public void run() {
                    state.update(true);
                }
            }, 20);
        }
    }

}
