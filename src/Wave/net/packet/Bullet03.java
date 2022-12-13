package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public class Bullet03 extends Packet{

	private float x,y;
	private float velx,vely;
	String username;
	public Bullet03(byte[] data) {
		super(03);
		String[] dataArr=readData(data).split(",");
		this.username=dataArr[0];
		this.x=Float.parseFloat(dataArr[1]);
		this.y=Float.parseFloat(dataArr[2]);
//		this.velx=Float.parseFloat(dataArr[4]);
//		this.vely=Float.parseFloat(dataArr[3]);
		// TODO Auto-generated constructor stub
	}
	public Bullet03(String username, float x, float y) {
		super(03);
		this.username=username;
		this.x=x;
		this.y=y;
//		this.velx=velx;
//		this.vely=vely;
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
		return ("03"+this.username+","+this.x+","+this.y).getBytes();
//		return ("03"+this.username+","+this.x+","+this.y+","+this.vely+","+this.velx).getBytes();
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
