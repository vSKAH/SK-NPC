package fr.skah.sknpc.api.enums;

/*
 *  * @Created on 2022 - 13:26
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import java.util.HashSet;
import java.util.Set;

public enum EntitySkinStatus {

    @Deprecated UNUSED(0x80),
    HAT_ENABLED(0x40),
    BODY_ENABLED(0x02),
    LEFT_HAND_ENABLED(0x04),
    RIGHT_HAND_ENABLED(0x08),
    LEFT_LEG_ENABLED(0x10),
    RIGHT_LEG_ENABLED(0x20),
    CAPE_ENABLED(0x01),
    ALL_ENABLED(0xFF);

    private final int mask;

    EntitySkinStatus(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }

    public static int createMask(EntitySkinStatus... entitySkinStatuses) {
        int mask = 0;
        for(EntitySkinStatus skinStatus : entitySkinStatuses) mask |= skinStatus.mask;
        return mask;
    }

    public static EntitySkinStatus[] fromMask(int mask) {
        Set<EntitySkinStatus> hashSet = new HashSet<>();
        for(EntitySkinStatus entitySkinStatus : values()) {
            if((entitySkinStatus.mask & mask) == entitySkinStatus.mask) hashSet.add(entitySkinStatus);
        }
        return hashSet.toArray(new EntitySkinStatus[0]);
    }
}
