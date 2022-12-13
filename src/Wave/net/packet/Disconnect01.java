package Wave.net.packet;

import Wave.net.Client;
import Wave.net.Server;

public class Disconnect01 extends Packet{

	String username;
	public Disconnect01(byte[] data) {
		super(01);
		this.username=readData(data);
		// TODO Auto-generated constructor stub
	}
	public Disconnect01(String username) {
		super(01);
		this.username=username;
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
		return ("01"+this.username).getBytes();
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	

}
