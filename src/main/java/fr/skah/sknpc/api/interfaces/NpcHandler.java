package fr.skah.sknpc.api.interfaces;

/*
 *  * @Created on 2022 - 18:53
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import com.mojang.authlib.GameProfile;
import fr.skah.sknpc.api.model.NpcModel;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public interface NpcHandler {

    CompletableFuture<GameProfile> createGameProfile(NpcModel npcModel, Player player);
    CompletableFuture<EntityPlayer> createNpc(NpcModel npcModel, Player player);

    void showNpc(EntityPlayer entityPlayer, NpcModel npcModel);
    void showNpcTo(NpcModel npcModel, Player player);

    void hideNpc(NpcModel npcModel);
    void hideNpcTo(NpcModel npcModel, Player player);
    void deleteNpc(NpcModel npcModel);

    NpcModel getNpcModel();

}
