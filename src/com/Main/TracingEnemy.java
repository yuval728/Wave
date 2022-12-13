package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TracingEnemy extends GameObject{

	Handler handler;
	private GameObject player;
	public TracingEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		for(int i=0;i<handler.object.size();i++)
			if(handler.object.get(i).getID()==ID.Player) player=handler.object.get(i); 

	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,24,24);
		
	}
	@Override
	public void tick() {
		
		x+=velx;
		y+=vely;
		float diffx=x- player.getX() -3; 
		float diffy=y- player.getY() -3;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		
		velx= (float) ((-1.8/distance)*diffx);
		vely= (float) ((-1.8/distance)*diffy);
		
		handler.addobject(new Trail(x,y,ID.Trail,Color.MAGENTA,24,24,0.035f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
