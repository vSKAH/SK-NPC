package fr.skah.sknpc.api.interfaces;

/*
 *  * @Created on 2022 - 13:12
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import net.minecraft.core.BlockPosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.network.syncher.DataWatcherSerializer;
import net.minecraft.world.entity.EntityPose;

public interface NpcMetadata {

    default <T> DataWatcher.Item<T> buildDataWatcher(int index, T value) {
        DataWatcherSerializer<?> serializer = null;

        if(value instanceof Byte) {
            serializer = DataWatcherRegistry.a;
        } else if(value instanceof Float) {
            serializer = DataWatcherRegistry.c;
        } else if(value instanceof Integer) {
            serializer = DataWatcherRegistry.b;
        } else if(value instanceof String) {
            serializer = DataWatcherRegistry.d;
        } else if(value instanceof Boolean) {
            serializer = DataWatcherRegistry.i;
        } else if(value instanceof NBTTagCompound) {
            serializer = DataWatcherRegistry.p;
        } else if(value instanceof BlockPosition) {
            serializer = DataWatcherRegistry.m;
        } else if(value instanceof IChatBaseComponent) {
            serializer = DataWatcherRegistry.e;
        } else if(value instanceof EntityPose) {
            serializer = DataWatcherRegistry.s;
        }
        return buildDataWatcher(index, value, (DataWatcherSerializer<T>)serializer);
    }


    private <T> DataWatcher.Item<T> buildDataWatcher(int index, T value, DataWatcherSerializer<T> serializer) {
        return new DataWatcher.Item<T>(new DataWatcherObject<T>(index, serializer), value);
    }

}
