package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	Handler handler;
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		// TODO Auto-generated constructor stub
		velx=3;
		vely=10;
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,16,16);
		
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(y<=0 || y>=Game.Height-32) vely*=-1; //check for values
		if(x<=0 || x>=Game.Width-32) velx*=-1; //......
		
		handler.addobject(new Trail(x,y,ID.Trail,Color.yellow,16,16,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
