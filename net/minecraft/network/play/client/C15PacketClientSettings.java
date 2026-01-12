package net.minecraft.network.play.client;

import com.Wroxy.Luna;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

import java.io.IOException;

public class C15PacketClientSettings implements Packet<INetHandlerPlayServer> {
    private String lang;
    private int view;
    private EntityPlayer.EnumChatVisibility chatVisibility;
    private boolean enableColors;
    private int modelPartFlags;

    public C15PacketClientSettings() {
    }

    public C15PacketClientSettings(String langIn, int viewIn, EntityPlayer.EnumChatVisibility chatVisibilityIn, boolean enableColorsIn, int modelPartFlagsIn)
    {
        this.lang = langIn;
        this.view = viewIn;
        this.chatVisibility = chatVisibilityIn;
        this.enableColors = enableColorsIn;
        this.modelPartFlags = modelPartFlagsIn;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.lang = buf.readStringFromBuffer(7);
        this.view = buf.readByte();
        this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(buf.readByte());
        this.enableColors = buf.readBoolean();
        this.modelPartFlags = buf.readUnsignedByte();
        if(buf.readableBytes() > 0)
        {
            buf.readStringFromBuffer(32767);
        }
    }


    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeString(this.lang);
        buf.writeByte(this.view);
        buf.writeByte(this.chatVisibility.getChatVisibility());
        buf.writeBoolean(this.enableColors);
        buf.writeByte(this.modelPartFlags);
        if(Luna.LunaSiksekMI) {
            buf.writeString("{\"customization\":{\"playerName\":\"" + Minecraft.getMinecraft().getSession().getUsername() + "\",\"character\":{\"model\":1,\"capeType\":29,\"scale\":0.97390425,\"defaultSkinType\":0}},\"cosmetic\":{\"playerName\":\"RealKonts\",\"wingData\":{\"wingScale\":0.85,\"particleSpawnRate\":1.0,\"centerOffset\":0.0,\"heightOffset\":0.8,\"color\":-1}},\"emotionWheelLayout\":[\"handwave\",\"jump\",\"angry\",\"snowflake\",\"default\"]}");
        }
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayServer handler)
    {
        handler.processClientSettings(this);
    }

    public String getLang()
    {
        return this.lang;
    }

    public EntityPlayer.EnumChatVisibility getChatVisibility()
    {
        return this.chatVisibility;
    }

    public boolean isColorsEnabled()
    {
        return this.enableColors;
    }

    public int getModelPartFlags()
    {
        return this.modelPartFlags;
    }
}
