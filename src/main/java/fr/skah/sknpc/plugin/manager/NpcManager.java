package fr.skah.sknpc.plugin.manager;

/*
 *  * @Created on 2022 - 22:15
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import com.google.common.collect.Lists;
import fr.skah.sknpc.api.manager.IManager;
import fr.skah.sknpc.api.model.NpcModel;

import java.util.ArrayList;
import java.util.Optional;

public class NpcManager implements IManager<NpcModel> {

    private static NpcManager instance;
    private final ArrayList<NpcModel> npcs = Lists.newArrayList();

    public NpcManager() {
        instance = this;
    }

    @Override
    public Optional<NpcModel> load(NpcModel model) {
        if (isLoaded(model.getId())) return Optional.empty();
        npcs.add(model);
        return Optional.of(model);
    }

    @Override
    public void unload(int identifier) {
        NpcModel npc = getFromCache(identifier);
        if (npc == null) return;
        npcs.remove(npc);
    }

    @Override
    public boolean isLoaded(int identifier) {
        for (NpcModel npcModel : npcs) {
            if (npcModel.getId() == identifier) return true;
        }
        return false;
    }

    @Override
    public NpcModel getFromCache(int identifier) {
        for (NpcModel npcModel : npcs) {
            if (npcModel.getId() == identifier) return npcModel;
        }
        return null;
    }

    @Override
    public int getCacheSize() {
        return npcs.size();
    }


    public static NpcManager getInstance() {
        return instance;
    }


}
