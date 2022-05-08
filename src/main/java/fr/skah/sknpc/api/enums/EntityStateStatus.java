package fr.skah.sknpc.api.enums;

/*
 *  * @Created on 2022 - 13:29
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import java.util.HashSet;
import java.util.Set;

public enum EntityStateStatus {


    DEFAULT(0x00),
    ON_FIRE(0x01),
    SPRINTING(0x08),
    SWIMMING(0x10),
    INVISIBLE(0x20),
    GLOWING(0x40),
    FLYING(0x80),
    ALL(0xFF);

    private final int mask;

    EntityStateStatus(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }

    public static int createMask(EntityStateStatus... entityStates) {
        int mask = 0;
        for(EntityStateStatus entityState : entityStates) mask |= entityState.mask;
        return mask;
    }

    public static EntityStateStatus[] fromMask(int mask) {
        Set<EntityStateStatus> hashSet = new HashSet<>();
        for(EntityStateStatus entityState : values()) {
            if((entityState.mask & mask) == entityState.mask) hashSet.add(entityState);
        }
        return hashSet.toArray(new EntityStateStatus[0]);
    }
}
