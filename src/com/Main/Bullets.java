package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.util.Random;

public class Bullets extends GameObject{

	private Handler handler;
	Random r=new Random();
	public InetAddress ipaddress;
	public int port;
	public static int fire=0;
	int dir=0;
	String username;
	public Bullets(float x, float y, ID id, Handler handler, String username, float vely, float velx, InetAddress ipaddress, int port) {
		super(x, y, id);
		this.handler=handler;
		this.ipaddress=ipaddress;
		this.port=port;
		// TODO Auto-generated constructor stub
		this.velx=velx;
		this.vely=vely;
		this.username=username;  
		
	}
public String getUsername() {
		
		// TODO Auto-generated method stub
		return username;
	}
	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,8,8);
		
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(y>=Game.Height||y<=0) {handler.removeobject(this) ; Player.i=0;}
		if(x>=Game.Width||x<=0) {handler.removeobject(this);Player.i=0;}
		
		handler.addobject(new Trail(x,y,ID.Trail,Color.yellow,8,8,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 8, 8);
		
	}

}
