package net.minecraft.network.play.client;

import com.Wroxy.Luna;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.io.IOException;
import java.util.Random;

public class C02PacketUseEntity implements Packet<INetHandlerPlayServer> {
    private int entityId;
    private Action action;
    public Vec3 hitVec;

    public C02PacketUseEntity() {
    }

    public C02PacketUseEntity(final Entity entity, final Action action) {
        this.entityId = entity.getEntityId();
        this.action = action;
    }

    public C02PacketUseEntity(final Entity entity, final Vec3 hitVec) {
        this(entity, Action.INTERACT_AT);
        this.hitVec = hitVec;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.action = (Action) buf.readEnumValue(Action.class);

        if (this.action == Action.INTERACT_AT) {
            this.hitVec = new Vec3((double) buf.readFloat(), (double) buf.readFloat(), (double) buf.readFloat());
        }
        if(Luna.LunaSiksekMI) {
            buf.readDouble();
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeEnumValue(this.action);

        if (this.action == Action.INTERACT_AT) {
            buf.writeFloat((float) this.hitVec.xCoord);
            buf.writeFloat((float) this.hitVec.yCoord);
            buf.writeFloat((float) this.hitVec.zCoord);
        }
        Random random = new Random();
        double min = 0;
        double max = 9.9;
        double randomValue = min + (max - min) * random.nextDouble();
        if(Luna.LunaSiksekMI) {
            buf.writeDouble(randomValue);
        }
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(final INetHandlerPlayServer handler) {
        handler.processUseEntity(this);
    }

    public Entity getEntityFromWorld(final World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }

    public Action getAction() {
        return this.action;
    }

    public Vec3 getHitVec() {
        return this.hitVec;
    }

    public enum Action {
        INTERACT,
        ATTACK,
        INTERACT_AT
    }
}
