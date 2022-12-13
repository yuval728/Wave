package com.Main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Wave.net.packet.Bullet03;
import Wave.net.packet.Move02;

public class Player extends GameObject{

	Random r=new Random();
	Handler handler;
	String username;
	protected float health1=HUD.Health;
	protected BufferedImage image,image1,image2,image3,image4,image5,image6,image7;
	static int i=0;
	static float bulx=-5,buly=-5,defense=1;
	public Player(float x, float y, ID id, Handler handler,String username) {
		super(x, y, id);
		this.handler=handler;
		this.username=username;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("skin.png"));
			image1=ImageIO.read(getClass().getResourceAsStream("skin2.png"));
			image2=ImageIO.read(getClass().getResourceAsStream("skin3.jpg"));
			image3=ImageIO.read(getClass().getResourceAsStream("skin4.png"));
			image4=ImageIO.read(getClass().getResourceAsStream("skin5.png"));
			image5=ImageIO.read(getClass().getResourceAsStream("skin6.png"));
			image6=ImageIO.read(getClass().getResourceAsStream("skin77.png"));
			image7=ImageIO.read(getClass().getResourceAsStream("skin8.jpg"));
		}catch(IOException e)
		{
			e.printStackTrace();		}
		
	}
	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	public String getUsername() {
		
		// TODO Auto-generated method stub
		return username;
	}
	
	
	public static float getbulx()
	{
		return bulx;
	}
	public static float getbuly()
	{
		return buly;
	}
	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		
		x=Game.clamp(x, 0, Game.Width-46);
		
		y=Game.clamp(y, 0, Game.Height-66);
		
		collision();
	}
	private void collision()
	{
		
		for(int i=0;i<handler.object.size();i++)
		{
				GameObject temp=handler.object.get(i);
				
				int pindex=handler.getMPindex(this.getUsername());
				if(temp.getID()==ID.BasicEnemy || temp.getID()==ID.FastEnemy || temp.getID()==ID.TracingEnemy ||temp.getID()==ID.Boss 
						||temp.getID()==ID.HardEnemy)
				{
					
					if(Menu.sp==0  && getbounds().intersects(temp.getbounds()))
					{
//						sethealth(gethealth()-defense);
						HUD.Health-=defense;
					}
				}
				
//				if(Menu.sp==1  &&temp.getID()==ID.Bullets)
//				{
//					//if(handler.object.get(pindex).getbounds().intersects(temp.getbounds()))
//					if(temp2.getbounds().intersects(temp.getbounds()))
//					{
//						health2-=1;
//					}
//				}
				

		}
//		HUD.Health=gethealth();
	}

	
	public void render(Graphics g) {
		
		try {
			if(Menu.getskin()==1)
			{
				g.drawImage(image1, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==2)
			{
				g.drawImage(image2, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==3)
			{
				g.drawImage(image, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==4)
			{
				g.drawImage(image3, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==5)
			{
				g.drawImage(image4, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==6)
			{
				g.drawImage(image5, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==7)
			{
				g.drawImage(image6, (int)x, (int)y, 32, 32, null);
			}
			if(Menu.getskin()==8)
			{
				g.drawImage(image7, (int)x, (int)y, 32, 32, null);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		g.drawString(getUsername(), (int)x, (int)y-20);
		
		
	}
	
	
	
}
