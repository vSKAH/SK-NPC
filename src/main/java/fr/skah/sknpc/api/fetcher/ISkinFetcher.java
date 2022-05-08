package fr.skah.sknpc.api.fetcher;

/*
 *  * @Created on 2022 - 13:33
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.skah.sknpc.api.model.NpcSkinTexture;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public interface ISkinFetcher {
    String profileApiEndpoint = "https://api.mojang.com/users/profiles/minecraft/%s";
    String textureApiEndpoint = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";

    void fetchSkinFromUsername(JavaPlugin javaPlugin, String username, BiConsumer<Boolean, NpcSkinTexture> callback);

    void fetchSkinFromUuid(JavaPlugin javaPlugin, String uuid, BiConsumer<Boolean, NpcSkinTexture> callback);

    default CompletableFuture<NpcSkinTexture> fetchSkinFromUsername(JavaPlugin javaPlugin, String username) {
        final CompletableFuture<NpcSkinTexture> completableFuture = new CompletableFuture<>();
        fetchSkinFromUsername(javaPlugin, username, (aBoolean, npcSkinTexture) -> completableFuture.complete(npcSkinTexture));
        return completableFuture;
    }

    default  CompletableFuture<NpcSkinTexture> fetchSkinFromUuid(JavaPlugin javaPlugin, String uuid) {
        final CompletableFuture<NpcSkinTexture> completableFuture = new CompletableFuture<>();
        fetchSkinFromUuid(javaPlugin, uuid, (aBoolean, npcSkinTexture) -> completableFuture.complete(npcSkinTexture));
        return completableFuture;
    }

    default void fetchUrl(JavaPlugin javaPlugin, String fetchUrl, String param, BiConsumer<Boolean, Map<String, Object>> callback) {
        HttpURLConnection connection = null;
        InputStream responseStream = null;
        Map responseMap = null;


        try {
            URL url = new URL(String.format(fetchUrl, param));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            responseMap = mapper.readValue(responseStream, Map.class);
            callback.accept(true, responseMap);
        } catch (IOException e) {
            callback.accept(false, null);
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
            if (responseMap != null) responseMap.clear();

            if (responseStream != null) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
