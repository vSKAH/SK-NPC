package fr.skah.sknpc.api.interfaces;

/*
 *  * @Created on 2022 - 01:24
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

public interface NpcUpdate {


    String getId();

    void tick();

    long getInterval();

    default boolean shouldTick(long tick) {
        return tick % getInterval() == 0;
    }

    default void register() {
    }

    default void unregister() {
    }

}
