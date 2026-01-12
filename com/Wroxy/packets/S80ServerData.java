package com.Wroxy.packets;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S80ServerData implements Packet<INetHandlerPlayClient>
{

    public S80ServerData()
    {
    }


    public void readPacketData(PacketBuffer buf) throws IOException
    {
    	byte[] asd = new byte[buf.readableBytes()];
        buf.readBytes(asd);
        System.out.println(new String(asd));
        
    }

    public void writePacketData(PacketBuffer buf) throws IOException
    {
    }

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
	}
}
