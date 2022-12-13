package com.Main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r=new Random();
	 int scorekeep=0;
	float sssss=0;
	private Game game;
	public Spawn(Handler handler,HUD hud,Game game) {
		this.handler=handler;
		this.hud=hud;
		this.game=game;
	}
	
	public void tick()
	{
		sssss+=0.5;
		if(sssss==2)
		{
			scorekeep++;
			sssss=0;
		}
			if(scorekeep==100)
			{
				scorekeep=0;
				hud.level(hud.getlevel()+1);
				hud.setPoint(hud.getPoint()+1);
				if(game.diff==2)
				{
					if(hud.getlevel()==2)
					{
						handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
					}
					else if(hud.getlevel()==3)
					{
						handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
					}
					else if(hud.getlevel()==4)
					{
						handler.addobject(new TracingEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.TracingEnemy,handler));
					}
					else if(hud.getlevel()==5)
					{
						handler.addobject(new FastEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.FastEnemy,handler));
					}
					else if(hud.getlevel()==6)
					{
						handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
					}
					else if(hud.getlevel()==7)
					{
						handler.addobject(new FastEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.FastEnemy,handler));
					}
					else if(hud.getlevel()==8)
					{
						handler.clearEnemy();
						handler.addobject(new Boss((Game.Width/2)-44,-120,ID.Boss,handler));
					}
				}
				if (game.diff==1)
				{
					if(hud.getlevel()==2)
					{
						handler.addobject(new HardEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.HardEnemy,handler));
					}
					else if(hud.getlevel()==3)
					{
						handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
						handler.addobject(new HardEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.HardEnemy,handler));
					}
					else if(hud.getlevel()==4)
					{
						handler.addobject(new TracingEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.TracingEnemy,handler));
					}
					else if(hud.getlevel()==5)
					{
						handler.addobject(new FastEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.FastEnemy,handler));
					}
					else if(hud.getlevel()==6)
					{
						handler.addobject(new HardEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.HardEnemy,handler));
					}
					else if(hud.getlevel()==7)
					{
						handler.addobject(new FastEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.FastEnemy,handler));
					}
					else if(hud.getlevel()==8)
					{
						handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
					}
					else if(hud.getlevel()==9)
					{
						handler.addobject(new FastEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.FastEnemy,handler));
					}
					else if(hud.getlevel()==10)
					{
						handler.clearEnemy();
						handler.addobject(new Boss((Game.Width/2)-44,-120,ID.Boss,handler));
					}
				}
				if(game.diff==3)
				{
//					if(hud.getlevel()==2)
//					{
//						handler.addobject(new BasicEnemy((Game.Width-50),(Game.Height-50),ID.BasicEnemy,handler));
//					}
//					else if(hud.getlevel()==3)
//					{
//						handler.addobject(new BasicEnemy((Game.Width-50),(Game.Height-50),ID.BasicEnemy,handler));
//					}
				}
				
			}
	}
}
