package fr.skah.sknpc.api.manager;

/*
 *  * @Created on 2022 - 22:07
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface IManager<T> {

    default T fromFile(File file, Class<T> tClass) throws IOException {
        return new ObjectMapper().readValue(file, tClass);
    }

    default T fromJson(String json, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, tClass);
    }

    default void toFile(File file, T model) throws IOException {
        new ObjectMapper().writeValue(file, model);
    }

    Optional<T> load(T model);

    void unload(int identifier);

    boolean isLoaded(int identifier);

    T getFromCache(int identifier);

    int getCacheSize();
}
