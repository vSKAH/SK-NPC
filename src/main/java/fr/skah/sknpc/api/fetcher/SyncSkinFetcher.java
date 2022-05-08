package fr.skah.sknpc.api.fetcher;

/*
 *  * @Created on 2022 - 18:56
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */
import fr.skah.sknpc.api.model.NpcSkinTexture;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class SyncSkinFetcher implements ISkinFetcher {

    @Override
    public void fetchSkinFromUsername(JavaPlugin javaPlugin, String username, BiConsumer<Boolean, NpcSkinTexture> callback) {
        fetchUrl(javaPlugin, profileApiEndpoint, username, (aBoolean, map) -> {
            fetchSkinFromUuid(javaPlugin, String.valueOf(map.get("id")), (callback));
        });
    }

    @Override
    public void fetchSkinFromUuid(JavaPlugin javaPlugin, String uuid, BiConsumer<Boolean, NpcSkinTexture> callback) {
        fetchUrl(javaPlugin, textureApiEndpoint, uuid, (aBoolean, map) -> {
            ArrayList<LinkedHashMap> arrayList = (ArrayList<LinkedHashMap>) map.get("properties");
            LinkedHashMap<String, String> linkedHashMap = arrayList.get(0);
            callback.accept(true, new NpcSkinTexture(linkedHashMap.get("value"), linkedHashMap.get("signature")));
        });
    }
}
