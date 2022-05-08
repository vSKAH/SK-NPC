package fr.skah.sknpc.api.model;

/*
 *  * @Created on 2022 - 16:40
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import java.util.List;

public class InteractModel {

    private boolean useInteract;
    private boolean useClickCooldown;
    private int clickCooldown;
    private List<String> clickActions;


    public InteractModel(boolean useInteract, boolean useClickCooldown, int clickCooldown, List<String> clickActions) {
        this.useInteract = useInteract;
        this.useClickCooldown = useClickCooldown;
        this.clickCooldown = clickCooldown;
        this.clickActions = clickActions;
    }

    public boolean isUseInteract() {
        return useInteract;
    }

    public void setUseInteract(boolean useInteract) {
        this.useInteract = useInteract;
    }

    public boolean isUseClickCooldown() {
        return useClickCooldown;
    }

    public void setUseClickCooldown(boolean useClickCooldown) {
        this.useClickCooldown = useClickCooldown;
    }

    public int getClickCooldown() {
        return clickCooldown;
    }

    public void setClickCooldown(int clickCooldown) {
        this.clickCooldown = clickCooldown;
    }

    public List<String> getClickActions() {
        return clickActions;
    }

    public void setClickActions(List<String> clickActions) {
        this.clickActions = clickActions;
    }
}
