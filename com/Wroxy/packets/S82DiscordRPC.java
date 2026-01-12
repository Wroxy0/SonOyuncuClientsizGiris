package com.Wroxy.packets;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S82DiscordRPC implements Packet<INetHandlerPlayClient>
{

    public S82DiscordRPC()
    {
    }

    public void readPacketData(PacketBuffer buf) throws IOException
    {
    	byte[] asd = new byte[buf.readableBytes()];
        buf.readBytes(asd);
    }

    public void writePacketData(PacketBuffer buf) throws IOException
    {
    }

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
	}
}
