package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject{

	Handler handler;
	private Random r=new Random();
	private int timer=65,timer2=50;
	public Boss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		// TODO Auto-generated constructor stub
		velx=0;
		vely=2;
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,96,96);
		
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(timer<=0)
		{
			vely=0;
		}
		else
			timer--;
		
		if(timer<=0) timer2--;
		if(timer2<=0) 
		{
			if(velx==0) velx=2;
			int spawn=r.nextInt(7);
			if(spawn==0) handler.addobject(new Boss1Bullets((int)x+48,(int)y+64,ID.BasicEnemy,handler));
		}
			
//		if(y<=0 || y>=Game.Height-32) vely*=-1; //check for values
		if(x<=0 || x>=Game.Width-96) velx*=-1; //......
		
		//handler.addobject(new Trail(x,y,ID.Trail,Color.red,64,64,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
		
	}

}
