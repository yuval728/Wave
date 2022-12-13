package com.Main;

import java.awt.*;

public class HUD {

	public static float Health=75;
	private float greenval=255;
	public int bounds=0;
	private int score=0,point=0,level=1;
	private float sssss=0;
	public void tick()
	{
		//Health-=0.5;
		Health=  Game.clamp(Health, 0 ,100+(bounds/2));
		greenval=Health*2;
		greenval= Game.clamp(greenval, 0, 255);
		greenval=Health*2;
		sssss+=0.5;
		if(sssss==2)
		{
			score+=1;
			sssss=0;
		}

		
	}
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15, 15, 150+bounds, 32);
		g.setColor(new Color(70,(int)greenval,0));
		g.fillRect(15, 15, (int)Health*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 150+bounds, 32);
		
		
		g.drawString("Score:"+score,15,70);
		if(Menu.sp==0)
		{
			g.drawString("Level:"+level,15,100);
			g.drawString("Points:"+point,15,140);
		}
		else
		{
			g.drawString("Points:"+point,15,100);
		}
		
		
	
	}
	public void score(int score)
	{
		this.score=score;
	}
	public int getscore()
	{
		return score;
	}
	public void level(int level)
	{
		this.level=level;
	}
	public int getlevel()
	{
		return level;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}

