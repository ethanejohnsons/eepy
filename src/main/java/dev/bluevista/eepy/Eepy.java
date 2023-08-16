package dev.bluevista.eepy;

import io.papermc.paper.event.player.PlayerDeepSleepEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Eepy extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getLogger().info("I'm eepy.");
    }

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event) {
        String name = event.getPlayer().getName();
        World world = event.getPlayer().getWorld();

        if (world.getTime() >= 13_000 || (world.getTime() < 13_000 && world.isThundering())) {
            world.getPlayers().forEach(player -> {
                player.sendMessage(String.format("%s is eeping through the night.", name));
            });
        }
    }

    @EventHandler
    public void onPlayerDeepSleepEvent(PlayerDeepSleepEvent event) {
        World world = event.getPlayer().getWorld();
        world.setTime(0); // day time
        world.setThundering(false); // no more weather
    }

}
