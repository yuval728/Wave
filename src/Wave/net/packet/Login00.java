package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public class Login00 extends Packet{

	String username;
	private float x,y;
	public Login00(byte[] data) {
		super(00);
		String[] dataArr=readData(data).split(",");
		this.username=dataArr[0];
		this.x=Float.parseFloat(dataArr[1]);
		this.y=Float.parseFloat(dataArr[2]);
		//this.username=readData(data);
		// TODO Auto-generated constructor stub
	}
	public Login00(String username,float x,float y) {
		super(00);
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
		return ("00"+this.username+","+this.getX()+","+this.getY()).getBytes(); /////////////
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return x;
	}

}
