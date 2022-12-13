package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public class Move02 extends Packet{

	private float x,y;
	String username;
	public Move02(byte[] data) {
		super(02);
		String[] dataArr=readData(data).split(",");
		this.username=dataArr[0];
		this.x=Float.parseFloat(dataArr[1]);
		this.y=Float.parseFloat(dataArr[2]);
		// TODO Auto-generated constructor stub
	}
	public Move02(String username, float x, float y) {
		super(02);
		this.username=username;
		this.x=x;
		this.y=y;
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
		return ("02"+this.username+","+this.x+","+this.y).getBytes();
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public float getX()
	{
		return this.x;
	}
	public float getY()
	{
		return this.y;
	}
	

}
