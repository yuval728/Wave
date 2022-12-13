package com.Main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> object=new LinkedList<GameObject>();
	public int spd=5;
	public void tick() {
		for(int i=0; i<object.size();i++)
		{
			GameObject temp=object.get(i);
			
			temp.tick();
		}
		
	}

	public void render(Graphics g) {
		for(int i=0; i<object.size();i++)
		{
			GameObject temp=object.get(i);
			
			temp.render(g);
		}
	}
	public void clearEnemy()
	{
		for(int i=0; i<object.size();i++)
		{
			GameObject temp=object.get(i);
			
			if(temp.getID()==ID.Player)
			{
				object.clear();
				if(Game.gamestate!=Game.State.End)
				{
					if(Menu.sp==0)
					{
						addobject(new Player((int)temp.getX(),(int)temp.getY(),ID.Player,this, Game.username ));
					}
					
					//addobject(new PlayerMP((int)temp.getX(),(int)temp.getY(),ID.PlayerMP,this,null,-1, Game.username ));
				}
				
			}

		}
	}
	
	public void addobject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeobject(GameObject object)
	{
		 this.object.remove(object);
	}
	public void removeMP(String username)
	{
		int index=0;
		for(GameObject e:object)
		{
			if(e instanceof PlayerMP &&((PlayerMP)e).getUsername().equals(username))
			{
				break;
			}
		index++;
		}
		this.object.remove(index);		
	}
	public int getMPindex(String username)
	{
		int index=0;
		for(GameObject e:object)
		{
			if(e instanceof PlayerMP &&((PlayerMP)e).getUsername().equals(username))
			{
				break;
			}
		index++;
		}
		return index;
	}
	public int getMPbull(String username)
	{
		int index=0;
		for(GameObject e:object)
		{
			if(e instanceof Bullets &&((Bullets)e).getUsername().equals(username))
			{
				break;
			}
		index++;
		}
		return index;
	}
	public void moveMP(String username,float x,float y)
	{
		int index=getMPindex(username);
		this.object.get(index).x=x;
		this.object.get(index).y=y;
	}
	public void bulletMP(String username, float x, float y)
	{
		if(Bullets.fire==1)
		{
			int index=getMPbull(username);
			this.object.get(index).x=x;
			this.object.get(index).y=y;
		}
		
	}
	public void healMP(String username,float health)
	{
		int index=getMPindex(username);
		this.object.get(index).sethealth(health);;
	}


}
