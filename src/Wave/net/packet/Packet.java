package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public abstract class Packet {

	public static enum PacketTypes{
		Invalid(-1),Login(00),Disconnect(01), Move(02),Bullet(03),Health(04);
		private int packID;
		private PacketTypes(int packID)
		{
			this.packID=packID;
		}
		public int getPackID() {
			return packID;
		}
	
	}
	public byte packID;
	public Packet(int packID)
	{
		this.packID=(byte) packID;
	}
	public abstract void writeData(Client client);
	public abstract void writeData(Server server);
	public abstract byte[] getData();
	public String readData(byte[] data)
	{
		String message=new String(data).trim();
		return message.substring(2);
	}	
	public static PacketTypes lookupPacket(String id) {
		try {
			return lookupPacket(Integer.parseInt(id));
		}
		catch(NumberFormatException e)
		{
			return PacketTypes.Invalid;
		}
	}
	public static PacketTypes lookupPacket(int id)
	{
		for(PacketTypes p: PacketTypes.values())
		{
			if(p.getPackID()==id)
				return p;
		}
		return PacketTypes.Invalid;
	}
}
