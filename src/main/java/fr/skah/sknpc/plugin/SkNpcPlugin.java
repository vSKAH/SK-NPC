package fr.skah.sknpc.plugin;

/*
 *  * @Created on 2022 - 00:48
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import fr.skah.sknpc.api.interfaces.NpcHandler;
import fr.skah.sknpc.plugin.manager.NpcManager;
import fr.skah.sknpc.plugin.nms.MinecraftVersion;
import fr.skah.sknpc.plugin.commands.NpcCommand;
import fr.skah.sknpc.plugin.listeners.PlayerListener;
import fr.skah.sknpc.plugin.nms.v1_18_R2.NMSNpc;
import fr.skah.sknpc.plugin.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SkNpcPlugin extends JavaPlugin {

    private static SkNpcPlugin skNpcPlugin;

    private NpcHandler npcController;


    @Override
    public void onEnable() {
        skNpcPlugin = this;
        new NpcManager();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        nms();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginCommand("npc").setExecutor(new NpcCommand(this));

        new Metrics(this, 14660).addCustomChart(new Metrics.SingleLineChart("npcs", () -> NpcManager.getInstance().getCacheSize()));
    }

    @Override
    public void onDisable() {

    }


    private void nms() {
        getLogger().log(Level.INFO, "Server Version Detected : " + MinecraftVersion.getServerVersion());
        if (MinecraftVersion.getCurrent() == MinecraftVersion.V.v1_18)
            npcController = new NMSNpc();
    }


    public static SkNpcPlugin getSkNpcPlugin() {
        return skNpcPlugin;
    }

    public NpcHandler getNpcController() {
        return npcController;
    }
}
