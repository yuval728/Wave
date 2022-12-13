package com.Main;

import java.net.InetAddress;
import java.util.Random;

import Wave.net.packet.Bullet03;
import Wave.net.packet.HEALTH04;
import Wave.net.packet.Move02;

public class PlayerMP extends Player{

	public InetAddress ipaddress;
	public int port;
	public String username,bulletname;
	public Random r=new Random();
	public float health2=health1;
	public PlayerMP(float x, float y, ID id, Handler handler,InetAddress ipaddress,int port,String username) {
		super(x, y, id, handler, username);
		this.ipaddress=ipaddress;
		this.port=port;
		this.bulletname=bulletusername();
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		if(Menu.sp==1)
		{
			Move02 packet=new Move02(this.getUsername(),this.x,this.y);
			packet.writeData(Game.game1.socketclient);
			if(Bullets.fire==1)
			{
				
				float bx=x+12,by=y+16;
				handler.addobject(new Bullets(bx,by,ID.Bullets,handler,this.bulletname,getbuly(),getbulx(),ipaddress,port));
				Bullet03 packet1=new Bullet03(this.bulletname,bx,by);
				i=1;
				packet1.writeData(Game.game1.socketclient);
				Bullets.fire=0;
			}
			collision2();
			HEALTH04 packet2=new HEALTH04(this.getUsername(),this.health);
			packet2.writeData(Game.game1.socketclient);
		}
	}
	public float gethealth()
	{
		return this.health2;
	}
	private void collision2()
	{
		int ind=0;
		for(int i=0;i<handler.object.size();i++)
		{
				GameObject temp2=handler.object.get(i);
				if(temp2.getID()==ID.PlayerMP)
				{
					ind=i;
					break;
				}
					
				
		}
		for(int i=0;i<handler.object.size();i++)
		{
				GameObject temp=handler.object.get(i),temp2=handler.object.get(ind);
				
				
				if(Menu.sp==1  &&temp.getID()==ID.Bullets)
				{
					//if(handler.object.get(pindex).getbounds().intersects(temp.getbounds()))
					if(temp2.getbounds().intersects(temp.getbounds()))
					{
						health2-=1;
						sethealth(this.gethealth());
					}
				}
		}
		//health2=;
	}
	public String bulletusername()
	{
		return (this.getUsername()+r.nextInt(500));
	}

}
