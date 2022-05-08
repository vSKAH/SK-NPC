package fr.skah.sknpc.api.enums;

/*
 *  * @Created on 2022 - 13:31
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

public enum EntityPingStatus {

    NO_CONNECTION(-1),
    ONE_BAR(1000),
    TWO_BARS(999),
    THREE_BARS(599),
    FOUR_BARS(299),
    FIVE_BARS(149);

    private final int milliseconds;

    EntityPingStatus(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

}
