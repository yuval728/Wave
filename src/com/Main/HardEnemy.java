package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject{

	Handler handler;
	private Random r=new Random();
	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velx=5;
		vely=5;
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,16,16);
		
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(y<=25|| y>=Game.Height-60) //check for values
		{
			if(vely<0) vely=-(r.nextInt(7)+1)*-1;
			else vely=(r.nextInt(7)+1)*-1;
		}
		if(x<=15 || x>=Game.Width-50) {
			if(velx<0) velx=-(r.nextInt(7)+1)*-1;
			else velx=(r.nextInt(7)+1)*-1;
		}
		
		handler.addobject(new Trail(x,y,ID.Trail,Color.white,16,16,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
