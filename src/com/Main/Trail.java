package com.Main;

import java.awt.*;

public class Trail extends GameObject{

	private float alpha=1;
	private float life;
	private Handler handler;
	private Color color;
	private int h,w;
	
	//life =0.001-0.1
	public Trail(float x, float y, ID id,Color color,int w,int h,float life,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler=handler;
		this.color=color;
		this.w=w;
		this.h=h;
		this.life=life; // to fix exception alpha out of range
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(alpha>=life)
		{
			alpha-= (life-0.0001f);
		}
		else 
			handler.removeobject(this);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d=(Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int)x,(int)y,w,h);
		g2d.setComposite(makeTransparent(1)); //to fix errors;
	}
	
	private AlphaComposite makeTransparent(float alpha)
	{
		int type=AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	@Override
	public Rectangle getbounds() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
