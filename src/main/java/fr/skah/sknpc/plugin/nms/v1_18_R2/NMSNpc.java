package fr.skah.sknpc.plugin.nms.v1_18_R2;

/*
 *  * @Created on 2022 - 18:56
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.skah.sknpc.api.enums.EntityStateStatus;
import fr.skah.sknpc.api.fetcher.AsyncSkinFetcher;
import fr.skah.sknpc.api.interfaces.NpcHandler;
import fr.skah.sknpc.api.interfaces.NpcMetadata;
import fr.skah.sknpc.api.model.NpcModel;
import fr.skah.sknpc.api.model.NpcSkinTexture;
import fr.skah.sknpc.plugin.SkNpcPlugin;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EntityPose;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class NMSNpc implements NpcMetadata, NpcHandler {

    private final DataWatcher.Item<Byte> entityState = buildDataWatcher(0, (byte) EntityStateStatus.createMask(EntityStateStatus.DEFAULT));
    private final DataWatcher.Item<Optional<IChatBaseComponent>> customName = buildDataWatcher(2, Optional.empty());
    private final DataWatcher.Item<Boolean> customNameVisible = buildDataWatcher(3, false);
    private final DataWatcher.Item<Boolean> gravity = buildDataWatcher(5, false);

    //A = DEBOUT
    //F = SNEAK
    //B = SWIM

    private final DataWatcher.Item<EntityPose> pose = buildDataWatcher(6, EntityPose.a);

    //Player metadataE
    private final DataWatcher.Item<NBTTagCompound> leftShoulder = buildDataWatcher(19, new NBTTagCompound());
    private final DataWatcher.Item<NBTTagCompound> rightShoulder = buildDataWatcher(20, new NBTTagCompound());

    @Override
    public CompletableFuture<GameProfile> createGameProfile(NpcModel npcModel, Player player) {

        final CompletableFuture<GameProfile> completableFuture = new CompletableFuture<>();
        final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npcModel.getName());

        if (npcModel.isMirror()) {
            new AsyncSkinFetcher().fetchSkinFromUsername(SkNpcPlugin.getSkNpcPlugin(), player.getName()).thenAccept(playerTexture -> {
                gameProfile.getProperties().put("textures", new Property("textures", playerTexture.getTextureBase64(), playerTexture.getTextureSignature()));
                completableFuture.complete(gameProfile);
            });
        } else {
            final NpcSkinTexture npcSkinTexture = npcModel.getNpcSkinTexture();
            gameProfile.getProperties().put("textures", new Property("textures", npcSkinTexture.getTextureBase64(), npcSkinTexture.getTextureSignature()));
            completableFuture.complete(gameProfile);
        }


        return completableFuture;
    }

    @Override
    public CompletableFuture<EntityPlayer> createNpc(NpcModel npcModel, Player player) {

        final Location location = npcModel.getLocation();
        final WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
        final MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();

        final CompletableFuture<EntityPlayer> completableFuture = new CompletableFuture<>();

        createGameProfile(npcModel, player).thenAccept(gameProfile -> {
            final EntityPlayer entityNpc = new EntityPlayer(minecraftServer, worldServer, gameProfile);
            entityNpc.b(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            completableFuture.complete(entityNpc);
        });

        return completableFuture;
    }

    @Override
    public void showNpc(EntityPlayer entityNpc, NpcModel npcModel) {

        for (Player player : Bukkit.getOnlinePlayers()) {

            final CraftPlayer craftPlayer = ((CraftPlayer) player);
            final PlayerConnection playerConnection = craftPlayer.getHandle().b;
            //PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a = show npc

            playerConnection.a(new PacketPlayOutPlayerInfo((PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a), entityNpc));
            playerConnection.a(new PacketPlayOutNamedEntitySpawn(entityNpc));

            PacketDataSerializer data = new PacketDataSerializer(Unpooled.buffer());
            data.d(entityNpc.getBukkitEntity().getEntityId());
            DataWatcher.a(List.of( buildDataWatcher(17, (byte) npcModel.getLayerMask())), data);

            playerConnection.a(new PacketPlayOutEntityMetadata(data));

            playerConnection.a(new PacketPlayOutEntityHeadRotation(entityNpc, (byte) (npcModel.getLocation().getYaw() * 256 / 360)));

        }
    }


    @Override
    public void showNpcTo(NpcModel npcModel, Player player) {

    }

    @Override
    public void hideNpc(NpcModel npcModel) {

    }

    @Override
    public void hideNpcTo(NpcModel npcModel, Player player) {

    }

    @Override
    public void deleteNpc(NpcModel npcModel) {

    }


    @Override
    public NpcModel getNpcModel() {
        return null;
    }
}
