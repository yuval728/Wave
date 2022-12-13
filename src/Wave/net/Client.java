package Wave.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import com.Main.Bullets;
import com.Main.Game;
import com.Main.Handler;
import com.Main.ID;
import com.Main.Player;
import com.Main.PlayerMP;

import Wave.net.packet.Bullet03;
import Wave.net.packet.Disconnect01;
import Wave.net.packet.HEALTH04;
import Wave.net.packet.Login00;
import Wave.net.packet.Move02;
import Wave.net.packet.Packet;
import Wave.net.packet.Packet.PacketTypes;

public class Client extends Thread{
	private InetAddress ipaddress;
	private DatagramSocket socket;
	private Game game;
	Handler handler;
	Random r=new Random();
	public Client(Game game ,String ipaddress,Handler handler)
	{
			this.game=game;
			this.handler=handler;
		try {
			this.socket=new DatagramSocket();
			this.ipaddress=InetAddress.getByName(ipaddress);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run() {
		while (true)
		{
			byte[] data=new byte[1024];
			DatagramPacket packet=new DatagramPacket(data,data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Server >"+new String(packet.getData())); 
			this.parsePacket(packet.getData(),packet.getAddress(),packet.getPort());
			
		}
	}
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String msg=new String(data).trim();
		PacketTypes type=Packet.lookupPacket(msg.substring(0,2));
		Packet packet=null;
		switch(type){
		default:
			
		case Invalid:
			break;
		case Login:
			packet=new Login00(data);
			handleLogin((Login00)packet,address,port);
			
			break;
		case Disconnect:
			packet=new Disconnect01(data);
			System.out.println("[" +address.getHostAddress()+":"+port+"]"+((Disconnect01) packet).getUsername()+"has left the game");
			
			handler.removeMP(((Disconnect01)packet).getUsername());
			
			break;
			
		case Move:
			packet=new Move02(data);
			handlepacket(((Move02)packet));
			break;
		case Bullet:
//			if(Bullets.fire==1)
//			{
//				packet=new Bullet03(data);
//				handlebullet((Bullet03) packet,address,port);
//				System.out.println("gg");
//			}
			
			break;
		case Health:
			packet=new HEALTH04(data);
			handleheal((HEALTH04)packet);
			break;
		
		}		
	}
	private void handleLogin(Login00 packet, InetAddress address, int port)
	{
		System.out.println("[" +address.getHostAddress()+":"+port+"]"+(packet).getUsername()+" has joined");
		PlayerMP player=new PlayerMP(packet.getX(), packet.getY()-80, ID.PlayerMP, handler, address, port,(packet).getUsername());
		///check values 
			handler.addobject(player);
		
	}
	
	private void handlepacket(Move02 packet) {
		this.handler.moveMP((packet).getUsername(), (packet).getX(), (packet).getY());
		
	}
	private void handleheal(HEALTH04 packet)
	{
		this.handler.healMP((packet).getUsername(), (packet).gethealth());
	}
	private void handlebullet(Bullet03 packet,InetAddress address, int port)
	{
		if(Bullets.fire==1)	
		{
			Bullets bull=new Bullets(packet.getX(), packet.getY(), ID.Bullets, handler,packet.getUsername(),PlayerMP.getbuly(),PlayerMP.getbulx(),address,port);
			handler.addobject(bull);
			this.handler.bulletMP((packet).getUsername(), (packet).getX(), (packet).getY());
		}
		
			
	}
	public void sendData(byte[] data) {
		DatagramPacket packet=new DatagramPacket(data ,data.length,ipaddress,1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
