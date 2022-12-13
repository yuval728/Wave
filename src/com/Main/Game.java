package com.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.Main.Game.State;

import Wave.net.Client;
import Wave.net.Server;
import Wave.net.packet.Login00;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running=false;
	public static boolean paused=false;
	private BufferedImage image,image2;
	public static int Width= 660, Height=Width/12*9,ser;
	private HUD hud;
	private Menu menu;
	private Shop shop;
	public static Game game1;
	private Handler handler;
	private Spawn spawn;
	public int diff=0,time=400;
	public Player player;
	public Client socketclient;
	public Server socketServer;
	public static String username;
	public WinHandler win;
	public enum State{
		Menu,Help,Game,End,Select,Skin
		,Shop,MP,End2;
	};
	
	public static State gamestate=State.Menu;
	public Game()
	{
		game1=this;
		handler=new Handler();
		try {
			image=ImageIO.read(getClass().getResourceAsStream("menu.png"));
			image2=ImageIO.read(getClass().getResourceAsStream("gggg.png"));
		}catch(IOException e)
		{
			e.printStackTrace();	
		}
		
		hud=new HUD();
		spawn=new Spawn(handler,hud,this);
		menu=new Menu(this,handler,hud,spawn);
		shop=new Shop(handler,hud);
		win=new WinHandler(this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		this.addMouseMotionListener(menu);
		
//		AudioPlayer.load();
//		AudioPlayer.getmusic("music").loop();
//		
		
		new Window(Height,Width,"Wave",this).frame.addWindowListener(win);
		
		username=JOptionPane.showInputDialog(this,"Please enter a username");
	
			
	}
	public synchronized void start()
	{
		thread=new Thread(this);
		thread.start();
		running=true;
		
	}
	public synchronized void stop()
	{
		try {
			thread.join();
			running=false;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Game();
		

	}
	public void run() {
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountofticks=60.0;
		double ns= 1000000000/amountofticks,delta=0;
		long timer=System.currentTimeMillis();
		int frames=0,ok=0;
		while(running)
		{
			long now= System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running)
			{
				frames++;
				render(ok);
				
			}
				
			if(System.currentTimeMillis()-timer>=1000)
			{
				timer+=1000; //remove one zero for changing interval
				//System.out.println("FPS:"+frames);
				ok=frames;
				frames=0;
			}
		}
		stop();
	}
	private void tick()
	{
		
		if(gamestate== State.Game)
		{
			
			if(time<=99)
			{
				if(!paused)
				{
					handler.tick();
					hud.tick();
					spawn.tick();
					if(HUD.Health<=0&& Menu.sp==0)
					{
						HUD.Health=75;
						time=400;
						gamestate=State.End;
						handler.clearEnemy();
					}
					if(HUD.Health<=0 &&Menu.sp==1)
					{
						HUD.Health=75;
						time=400;
						gamestate=State.End2;
						handler.clearEnemy();
					}
				}
			}
			else
				time-=2;
			
			
			//socketclient.sendData("ping".getBytes());
			
		}
		else if(gamestate== State.Menu || gamestate== State.End ||gamestate==State.Skin || gamestate==State.MP  || gamestate==State.Select ||gamestate==State.End2 )
		{
			menu.tick();
		}
		
		
	}
	private void render(int frames)
	{
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		

		if(gamestate== State.Game)
		{	
			g.drawImage(image2, 0,0, Width,Height, null);
		}
		else {
			g.drawImage(image, 0,0, Width,Height, null);
		}
		
		Font fnt2=new Font("arial",1,10);
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawString("FPS: "+frames, 580,450 );
		
		
		
		if(paused)
		{
			
//			g.setFont(new Font("arial",1,30));
			g.drawString("Pause", 300, 100);
			
		}
		if(gamestate== State.Game)
		{	
			//g.drawImage(image2, 0,0, Width,Height, null);
			if(time>99)
			{	
				Font fnt3=new Font("arial",1,30);
				g.setFont(fnt3);
				g.drawString(""+time/100, Game.Width/2-50, Game.Height/2-50);
			}
			else
			{
				handler.render(g);
				hud.render(g);
			}
			
		}
		else if(gamestate==State.Shop)
		{
			
			shop.render(g);
			
		}
		else if(gamestate== State.Menu || gamestate== State.Help || gamestate== State.End ||gamestate== State.Skin ||gamestate==State.MP
				||gamestate==State.Select ||gamestate==State.End2 )
		{
			//g.drawImage(image, 0,0, Width,Height, null);
			handler.render(g);
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	public static float clamp(float x,float min,float max)
	{
		if(x>=max)
		{
			return x=max;
		}
		else if(x<=min) return x=min;
		else 
			return x;
	}

}

