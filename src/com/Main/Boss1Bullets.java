package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullets extends GameObject{

	Handler handler;
	 Random r=new Random();
	public Boss1Bullets(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		// TODO Auto-generated constructor stub
		velx=(r.nextInt(5- -5)+ -5);
		vely=5;
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,16,16);
		
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(y>=Game.Height) handler.removeobject(this);
 		
		handler.addobject(new Trail(x,y,ID.Trail,Color.red,16,16,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
