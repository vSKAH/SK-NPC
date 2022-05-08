package fr.skah.sknpc.plugin.listeners;

/*
 *  * @Created on 2022 - 01:34
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import fr.skah.sknpc.plugin.SkNpcPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerListener implements Listener {

    private SkNpcPlugin npcPlugin;

    public PlayerListener(SkNpcPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {


    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {

        final Player player = event.getPlayer();


    }


}
