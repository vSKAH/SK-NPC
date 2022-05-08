package fr.skah.sknpc.api.model;

/*
 *  * @Created on 2022 - 01:14
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import fr.skah.sknpc.api.enums.EntitySkinStatus;
import fr.skah.sknpc.api.fetcher.AsyncSkinFetcher;
import fr.skah.sknpc.api.fetcher.ISkinFetcher;
import fr.skah.sknpc.api.fetcher.SyncSkinFetcher;
import fr.skah.sknpc.plugin.SkNpcPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

public class NpcModel {

    private int id;

    private boolean named;
    private String name;

    private boolean hasSkin, mirror;

    private boolean useUuid;
    private String skinUuid;

    private boolean useName;
    private String skinName;

    private NpcSkinTexture npcSkinTexture;

    private int layerMask;

    private boolean showInTablist;
    private String entityPingStatus;

    private boolean useViewPermission, useInteractPermission;
    private String viewPermission, interactPermission;

    private InteractModel rightClickInteract, leftClickInteract;

    private int displayRange, updateRange;
    private int updateInterval;

    private Location location;


    public NpcModel() {
    }

    public NpcModel(int id, boolean named, String name, boolean hasSkin, boolean mirror, boolean useUuid, String skinUuid, boolean useName, String skinName, NpcSkinTexture npcSkinTexture, int layerMask, boolean showInTablist, String entityPingStatus, boolean useViewPerimssion, boolean useInteractPermission, String viewPermission, String interactPermission, InteractModel rightClickInteract, InteractModel leftClickInteract, Location location) {
        this(id, named, name, hasSkin, mirror, useUuid, skinUuid, useName, skinName, npcSkinTexture, layerMask, showInTablist, entityPingStatus, useViewPerimssion, useInteractPermission, viewPermission, interactPermission, rightClickInteract, leftClickInteract, 20, 16, 100, location);
    }

    public NpcModel(int id, boolean named, String name, boolean hasSkin, boolean mirror, boolean useUuid, String skinUuid, boolean useName, String skinName, NpcSkinTexture npcSkinTexture, int layerMask, boolean showInTablist, String entityPingStatus, boolean useViewPermission, boolean useInteractPermission, String viewPermission, String interactPermission, InteractModel rightClickInteract, InteractModel leftClickInteract, int displayRange, int updateRange, int updateInterval, Location location) {
        this.id = id;
        this.named = named;
        this.name = name;
        this.hasSkin = hasSkin;
        this.mirror = mirror;
        this.useUuid = useUuid;
        this.skinUuid = skinUuid;
        this.useName = useName;
        this.skinName = skinName;
        this.npcSkinTexture = npcSkinTexture;
        this.layerMask = layerMask;
        this.showInTablist = showInTablist;
        this.entityPingStatus = entityPingStatus;
        this.useViewPermission = useViewPermission;
        this.useInteractPermission = useInteractPermission;
        this.viewPermission = viewPermission;
        this.interactPermission = interactPermission;
        this.rightClickInteract = rightClickInteract;
        this.leftClickInteract = leftClickInteract;
        this.displayRange = displayRange;
        this.updateRange = updateRange;
        this.updateInterval = updateInterval;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRandomId() {
        id = new SecureRandom().nextInt(Integer.MAX_VALUE);
    }

    public boolean isNamed() {
        return named;
    }

    public void setNamed(boolean named) {
        this.named = named;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasSkin() {
        return hasSkin;
    }

    public void setHasSkin(boolean hasSkin) {
        this.hasSkin = hasSkin;
    }

    public boolean isMirror() {
        return mirror;
    }

    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

    public boolean isUseUuid() {
        return useUuid;
    }

    public void setUseUuid(boolean useUuid) {
        this.useUuid = useUuid;
    }

    public String getSkinUuid() {
        return skinUuid;
    }

    public void setSkinUuid(String skinUuid) {
        this.skinUuid = skinUuid;
    }

    public boolean isUseName() {
        return useName;
    }

    public void setUseName(boolean useName) {
        this.useName = useName;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public NpcSkinTexture getNpcSkinTexture() {
        return npcSkinTexture;
    }

    public void setNpcSkinTexture(NpcSkinTexture npcSkinTexture) {
        this.npcSkinTexture = npcSkinTexture;
    }

    public int getLayerMask() {
        return layerMask;
    }

    public void setLayerMask(int layerMask) {
        this.layerMask = layerMask;
    }

    public boolean isShowInTablist() {
        return showInTablist;
    }

    public void setShowInTablist(boolean showInTablist) {
        this.showInTablist = showInTablist;
    }

    public String getEntityPingStatus() {
        return entityPingStatus;
    }

    public void setEntityPingStatus(String entityPingStatus) {
        this.entityPingStatus = entityPingStatus;
    }

    public boolean isUseViewPermission() {
        return useViewPermission;
    }

    public void setUseViewPermission(boolean useViewPermission) {
        this.useViewPermission = useViewPermission;
    }

    public boolean isUseInteractPermission() {
        return useInteractPermission;
    }

    public void setUseInteractPermission(boolean useInteractPermission) {
        this.useInteractPermission = useInteractPermission;
    }

    public String getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(String viewPermission) {
        this.viewPermission = viewPermission;
    }

    public String getInteractPermission() {
        return interactPermission;
    }

    public void setInteractPermission(String interactPermission) {
        this.interactPermission = interactPermission;
    }

    public InteractModel getRightClickInteract() {
        return rightClickInteract;
    }

    public void setRightClickInteract(InteractModel rightClickInteract) {
        this.rightClickInteract = rightClickInteract;
    }

    public InteractModel getLeftClickInteract() {
        return leftClickInteract;
    }

    public void setLeftClickInteract(InteractModel leftClickInteract) {
        this.leftClickInteract = leftClickInteract;
    }

    public int getDisplayRange() {
        return displayRange;
    }

    public void setDisplayRange(int displayRange) {
        this.displayRange = displayRange;
    }

    public int getUpdateRange() {
        return updateRange;
    }

    public void setUpdateRange(int updateRange) {
        this.updateRange = updateRange;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CompletableFuture<NpcModel> buildFromConfig(FileConfiguration config) {
        CompletableFuture<NpcModel> npcModelCompletableFuture = new CompletableFuture<>();
        this.setRandomId();

        final String defaultPath = "default_npc.";
        this.setNamed(config.getBoolean(defaultPath + "has_name"));
        this.setName(config.getString(defaultPath + "default_name"));

        final String skinPath = defaultPath + "skin.";
        this.setHasSkin(config.getBoolean(skinPath + "has_skin"));
        this.setMirror(config.getBoolean(skinPath + "is_mirror"));
        this.setUseUuid(config.getBoolean(skinPath + "use_uuid"));
        this.setSkinUuid(config.getString(skinPath + "default_skin_uuid"));
        this.setUseName(config.getBoolean(skinPath + "use_name"));
        this.setSkinName(config.getString(skinPath + "default_skin_name"));

        if (hasSkin()) {

            ISkinFetcher asyncSkinFetcher = new AsyncSkinFetcher();
            if (isUseUuid()) {
                asyncSkinFetcher.fetchSkinFromUuid(SkNpcPlugin.getSkNpcPlugin(), getSkinUuid()).thenAccept(npcSkinTexture1 -> {
                    setNpcSkinTexture(npcSkinTexture1);
                    npcModelCompletableFuture.complete(this);
                });
            } else if (isUseName()) {
                asyncSkinFetcher.fetchSkinFromUsername(SkNpcPlugin.getSkNpcPlugin(), getSkinName()).thenAccept(npcSkinTexture1 -> {
                    setNpcSkinTexture(npcSkinTexture1);
                    npcModelCompletableFuture.complete(this);
                });
            }

            final String layersPath = skinPath + "layers.";
            ArrayList<EntitySkinStatus> entitySkinStatus = new ArrayList<>();
            if (config.getBoolean(layersPath + "all")) {
                entitySkinStatus.add(EntitySkinStatus.ALL_ENABLED);
            }
            else {
                if (config.getBoolean(layersPath + "body")) entitySkinStatus.add(EntitySkinStatus.BODY_ENABLED);
                if (config.getBoolean(layersPath + "left_hand")) entitySkinStatus.add(EntitySkinStatus.LEFT_HAND_ENABLED);
                if (config.getBoolean(layersPath + "right_hand")) entitySkinStatus.add(EntitySkinStatus.RIGHT_HAND_ENABLED);
                if (config.getBoolean(layersPath + "left_leg")) entitySkinStatus.add(EntitySkinStatus.LEFT_LEG_ENABLED);
                if (config.getBoolean(layersPath + "right_leg")) entitySkinStatus.add(EntitySkinStatus.RIGHT_LEG_ENABLED);
                if (config.getBoolean(layersPath + "cape")) entitySkinStatus.add(EntitySkinStatus.CAPE_ENABLED);
            }
            setLayerMask(EntitySkinStatus.createMask(entitySkinStatus.toArray(EntitySkinStatus[]::new)));
            entitySkinStatus.clear();
        }

        final String tablistPath = defaultPath + "tablist.";
        this.setShowInTablist(config.getBoolean(tablistPath + "enabled"));
        this.setEntityPingStatus(config.getString(tablistPath + "ping"));

        return npcModelCompletableFuture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NpcModel)) return false;

        NpcModel npcModel = (NpcModel) o;

        return getId() == npcModel.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
