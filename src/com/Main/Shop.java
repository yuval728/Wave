package com.Main;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;

public class Shop extends MouseAdapter{

	static int[] B=new int[10];
	Handler handler;
	HUD hud;
	public Shop(Handler handler,HUD hud)
	{
		this.handler=handler;
		this.hud=hud;
		for(int i=0;i<10;i++)
		{ 
			B[i]=1;	
		}
		B[2]=3;
	}
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(new Font("arial",0,24));
		g.drawString("Shop", Game.Width/2-60, 50);
		g.drawString("Points:"+hud.getPoint(), Game.Width/2-70, 400);
		
		if(B[0]<=3)
		{
			g.setColor(check(0,B));
			g.setFont(new Font("arial",0,12));
			g.drawString("Upgrade health", 110, 120);
			g.drawString("Cost :"+B[0], 110, 140);
			g.drawRect(100, 100, 100, 80);
		}
		else {
			g.setColor(Color.red);
			g.setFont(new Font("arial",0,12));
			g.drawString("Max Health", 120, 130);
//			g.drawString("Cost :"+B[0], 110, 140);
			g.drawRect(100, 100, 100, 80);
		}
		
		if(handler.spd<8)
		{
			g.setColor(check(1,B));
			g.drawString("Upgrade Speed", 260, 120);
			g.drawString("Cost :"+B[1], 260, 140);
			g.drawRect(250, 100, 100, 80);
		}
		else {
			g.setColor(Color.red);
			g.drawString("Max Speed", 270, 130);
//			g.drawString("Cost :"+B[1], 260, 140);
			g.drawRect(250, 100, 100, 80);
		}
		B[2]=3;
		g.setColor(check(2,B));
		g.drawString("Refill health", 410, 120);
		g.drawString("Cost :"+B[2], 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		if(Player.defense>0.5)
		{
			g.setColor(check(3,B));
			g.drawString("Upgrade Defense", 255, 240);
			g.drawString("Cost :"+B[3], 260, 260);
			g.drawRect(250, 220, 100, 80);
			
		}
		else {
			g.setColor(Color.red);
			g.drawString("Max Defense", 265, 240);
//			g.drawString("Cost :"+B[3], 260, 260);
			g.drawRect(250, 220, 100, 80);
			
		}

		
	}
	public Color check(int x,int B[]) {
		if(hud.getPoint()>=B[x])
			return Color.white;
		else
			return Color.green;
		
	}
	public void mousePressed(MouseEvent e)
	{
		int mx=e.getX();
		int my=e.getY();
		
		if (mx>=100 && mx<=200)
		{
			if(my>=100 &&my<=180)
			{
				if(B[0]<=3)
				{
					if(hud.getPoint()>=B[0])
					{
						hud.setPoint(hud.getPoint()-B[0]);
						B[0]+=1;
						hud.bounds+=10;
						HUD.Health+=hud.bounds/3;
					}
				}
				
			}
		}
		if (mx>=250 && mx<=350)
		{
			if(my>=100 &&my<=180)
			{
				if(handler.spd<8)
				{
					if(hud.getPoint()>=B[1])
					{
						hud.setPoint(hud.getPoint()-B[1]);
						B[1]+=1;
						handler.spd+=1;
					}
				}
				
			}
		}
		if (mx>=400 &&mx<=500)
		{
			if(my>=100 && my<=180)
			{
				if(hud.getPoint()>=3)
				{
					hud.setPoint(hud.getPoint()-3);
					
					HUD.Health=75+(hud.bounds/2);
				}
			}
		}
		if (mx>=250 && mx<=350)
		{
			if(my>=220 &&my<=300)
			{
				if(Player.defense>0.5)
				{
					if(hud.getPoint()>=B[3])
					{
						hud.setPoint(hud.getPoint()-B[3]);
						B[3]+=1;
						Player.defense-=0.1;
					}
				}
				
			}
		}
	}
	
}
