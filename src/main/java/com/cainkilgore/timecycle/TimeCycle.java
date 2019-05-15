package com.cainkilgore.timecycle;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeCycle extends JavaPlugin implements CommandExecutor {

    boolean isTimeCycling = false;
    long currentTime = 0;

    public void onEnable() {
        getCommand("timecycle").setExecutor(this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                if(isTimeCycling) {
                    currentTime += 100;
                    Bukkit.getWorld("world").setTime(currentTime);
                }
            }
        }, 1, 1);
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
        if(!(s instanceof Player)) {
            s.sendMessage("You need to be in-game to use this command.");
            return false;
        }

        if(!isTimeCycling) {
            isTimeCycling = true;
            currentTime = ((Player)s).getWorld().getTime();
            s.sendMessage("Time cycling: " + isTimeCycling);
            return true;
        }
        isTimeCycling = false;
        s.sendMessage("Time cycling: " + isTimeCycling);
        return true;
    }
}

