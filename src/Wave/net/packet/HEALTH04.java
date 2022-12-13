package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public class HEALTH04 extends Packet{

	String username;
	float healthmp;
	public HEALTH04(byte[] data) {
		super(04);
		String[] dataArr=readData(data).split(",");
		this.username=dataArr[0];
		this.healthmp=Float.parseFloat(dataArr[1]);
		// TODO Auto-generated constructor stub
	}
	public HEALTH04(String username, float healthmp) {
		super(04);
		this.username=username;
		this.healthmp=healthmp;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeData(Client client) {
		client.sendData(getData());
		
	}

	@Override
	public void writeData(Server server) {
		// TODO Auto-generated method stub
		server.sendDataToAll(getData());
	}
	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("04"+this.username+","+this.healthmp).getBytes();
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public float gethealth()
	{
		return this.healthmp;
	}
	

}
