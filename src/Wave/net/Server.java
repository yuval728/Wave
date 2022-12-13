package Wave.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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


public class Server extends Thread{
	
	private DatagramSocket socket;
	private Game game;
	public LinkedList<PlayerMP> connectedPlayer=new LinkedList<PlayerMP>();
	public LinkedList<Bullets>  bullestshoot=new LinkedList<Bullets>();
	private Handler handler;
	Random r;
	public Server(Game game,Handler handler)
	{
		this.game=game;
		this.handler=handler;
		r=new Random();
		try {
			this.socket=new DatagramSocket(1331);
		} catch (SocketException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		} 
	}
	public void run() {
		while (true)
		{
			byte[] data=new byte[2056];
			DatagramPacket packet=new DatagramPacket(data,data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			System.out.println("[" +address.getHostAddress()+":"+port+"]"+((Login00) packet).getUsername()+" has connected");
			PlayerMP player=new PlayerMP(Game.Width/2-32, Game.Height/2-32, ID.PlayerMP, handler, address, port,((Login00) packet).getUsername());
				this.addConnection(player,((Login00)packet)); 
			
			//check ID
			break;
		case Disconnect:
			packet=new Disconnect01(data);
			System.out.println("[" +address.getHostAddress()+":"+port+"]"+((Disconnect01) packet).getUsername()+" has left");
			this.removeConnection(((Disconnect01)packet)); 
			break;
		
		case Move:
			packet=new Move02(data);
			
			this.handleMove(((Move02) packet));
			//break;
		case Bullet:
			packet=new Bullet03(data);
//			if(Bullets.fire==1)
//			{
//				Bullets bull=new Bullets(((Bullet03)packet).getX(),((Bullet03)packet).getY(),ID.Bullets,handler,((Bullet03)packet).getUsername(), PlayerMP.getbuly(),PlayerMP.getbulx(),address,port);
//				this.addbullets(bull, (Bullet03)packet);
//				System.out.println(((Bullet03) packet).getUsername()+" has moved to"+((Bullet03) packet).getX()+","+((Bullet03) packet).getY());
//				this.handlebullet((Bullet03)packet);
//			}
				
			break;
		
		case Health:
			packet=new HEALTH04(data);
			this.handlehealth((HEALTH04)packet);
		}
		
	}
	private void handlebullet(Bullet03 packet) {
		// TODO Auto-generated method stub
		if(getBulletMP(packet.getUsername())!=null )
		{
			int index=getBulletMPindex(packet.getUsername());
			this.bullestshoot.get(index).setX(packet.getX());
			this.bullestshoot.get(index).setY(packet.getY());
			packet.writeData(this);
		}
	}
	private void handleMove(Move02 move02) {

		if(getplayerMP(move02.getUsername())!=null)
		{
			int index=getplayerMPindex(move02.getUsername());
			this.connectedPlayer.get(index).setX(move02.getX());
			this.connectedPlayer.get(index).setY(move02.getY());
			move02.writeData(this);
		}
		
	}
	private void handlehealth(HEALTH04 move02) {

		if(getplayerMP(move02.getUsername())!=null)
		{
			int index=getplayerMPindex(move02.getUsername());
			this.connectedPlayer.get(index).sethealth(move02.gethealth());
			move02.writeData(this);
		}
		
	}
	
	public void removeConnection(Disconnect01 disconnect01) {
//		PlayerMP player=getplayerMP(disconnect01.getUsername());
		this.connectedPlayer.remove(getplayerMPindex(disconnect01.getUsername()));
		disconnect01.writeData(this);
		
	}
	public PlayerMP getplayerMP(String user)
	{
		for(PlayerMP  player:this.connectedPlayer)
		{
			if(player.getUsername().equals(user))
			{
				return player;
			}
		
		}
		return null;
	}
	public int getplayerMPindex(String user)
	{
		int index=0;
		for(PlayerMP  player:this.connectedPlayer)
		{
			if(player.getUsername().equals(user))
			{
				break;
			}
		index++;
		}
		return index;
	}
	public Bullets getBulletMP(String user)
	{
		for(Bullets  bullet:this.bullestshoot)
		{
			if(bullet.getUsername().equals(user))
			{
				return bullet;
			}
		
		}
		return null;
	}
	public int getBulletMPindex(String user)
	{
		int index=0;
		for(Bullets  bullet:this.bullestshoot)
		{
			if(bullet.getUsername().equals(user))
			{
				break;
			}
		index++;
		}
		return index;
	}
	
	public void addConnection(PlayerMP player, Login00 packet) {
		// TODO Auto-generated method stub
		boolean alreadycon=false;
		for(PlayerMP p: connectedPlayer)
		{
			if(player.getUsername().equalsIgnoreCase(p.getUsername()))
			{
				if(p.ipaddress==null)
					p.ipaddress=player.ipaddress;
				if(p.port==-1)
				{
					p.port=player.port;
				}
				alreadycon=true;
			}
			else
			{
				sendData(packet.getData(),p.ipaddress,p.port);
				packet=new Login00(p.getUsername(),p.getX(),p.getY());
//				Login00 login=new Login00(((Login00) packet).getUsername());
				sendData(packet.getData(),player.ipaddress,player.port);
				
			}
		}
		if(!alreadycon)
		{
			this.connectedPlayer.add(player); 
			
		}
	}
	public void addbullets(Bullets bull,Bullet03 packet)
	{
		boolean alreadycon=false;
		for(Bullets b: bullestshoot)
		{
			if(bull.getUsername().equalsIgnoreCase(b.getUsername()))
			{
				if(b.ipaddress==null)
					b.ipaddress=b.ipaddress;
				if(b.port==-1)
				{
					b.port=bull.port;
				}
				alreadycon=true;
			}
			else
			{
				sendData(packet.getData(),b.ipaddress,b.port);
				packet=new Bullet03(b.getUsername(),b.getX(),b.getY());
//				Login00 login=new Login00(((Login00) packet).getUsername());
				sendData(packet.getData(),bull.ipaddress,bull.port);
				
			}
		}
		if(!alreadycon)
		{
			this.bullestshoot.add(bull); 
			
		}
	}
	public void sendData(byte[] data,InetAddress ipaddress ,int port) {
		DatagramPacket packet=new DatagramPacket(data ,data.length,ipaddress,port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void sendDataToAll(byte[] data) {
		// TODO Auto-generated method stub
		for(PlayerMP p: connectedPlayer)
		{
			sendData(data, p.ipaddress, p.port);
		}
		for(Bullets b: bullestshoot)
		{
			sendData(data,b.ipaddress,b.port);
		}
		
	}

	
}
