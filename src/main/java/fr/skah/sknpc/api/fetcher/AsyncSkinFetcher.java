package fr.skah.sknpc.api.fetcher;

/*
 *  * @Created on 2022 - 18:56
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import fr.skah.sknpc.api.model.NpcSkinTexture;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class AsyncSkinFetcher implements ISkinFetcher {

    private final ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void fetchSkinFromUsername(JavaPlugin javaPlugin, String username, BiConsumer<Boolean, NpcSkinTexture> callback) {
        service.submit(() -> new SyncSkinFetcher().fetchSkinFromUsername(javaPlugin, username, callback));
        service.shutdown();
    }

    @Override
    public void fetchSkinFromUuid(JavaPlugin javaPlugin, String uuid, BiConsumer<Boolean, NpcSkinTexture> callback) {
        service.submit(() -> new SyncSkinFetcher().fetchSkinFromUuid(javaPlugin, uuid, callback));
        service.shutdown();
    }

}
