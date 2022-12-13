package com.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.Main.Game.State;

import Wave.net.Client;
import Wave.net.Server;
import Wave.net.packet.Disconnect01;
import Wave.net.packet.Login00;


public class Menu extends MouseAdapter {
	private Random r;
	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	int Wavex=235,Wavey=50;
	int movex=0,movey=0;
	static int x=0,yes,sp=0;
	private Color[] menubutton= {Color.white,Color.white,Color.white,Color.white,Color.white,Color.white,Color.white,
								 Color.white,Color.white,Color.white,Color.white,Color.white};
	private int[] bx= {210,210,210,210,210,210};
	private int[] bw= {200,200,200,200,200,200};
	
	private BufferedImage image1,image2,image3,image4,image5,image6,image7,image8;
	public Menu(Game game ,Handler handler,HUD hud,Spawn spawn)
	{
		this.game=game;
		this.handler=handler;
		this.hud=hud;
		this.spawn=spawn;
		 r=new Random();
		 try {
				image1=ImageIO.read(getClass().getResourceAsStream("skin2.png"));
				image2=ImageIO.read(getClass().getResourceAsStream("skin3.jpg"));
				image3=ImageIO.read(getClass().getResourceAsStream("skin.png"));
				image4=ImageIO.read(getClass().getResourceAsStream("skin4.png"));
				image5=ImageIO.read(getClass().getResourceAsStream("skin5.png"));
				image6=ImageIO.read(getClass().getResourceAsStream("skin6.png"));
				image7=ImageIO.read(getClass().getResourceAsStream("skin77.png"));
				image8=ImageIO.read(getClass().getResourceAsStream("skin8.jpg"));
			}catch(IOException e)
			{
				e.printStackTrace();		
			}
		 
		 	
	}
	public  void mousePressed(MouseEvent e)
	{
		 int mx=e.getX(),my=e.getY();
		
		 r=new Random();
		 if(Game.gamestate==State.Menu)
		 {
			 if(mouseOver(mx,my,210,150,200,64))
			 { 
				Game.gamestate=State.Skin; 
//				 AudioPlayer.getsound("click").play();
					
			 }
			 if(mouseOver(mx,my,210,250,200,64))
			 {
				 
				 Game.gamestate=State.Help;
//				 AudioPlayer.getsound("click").play();
			 }
			 if(mouseOver(mx,my,210,350,200,64))
			 {

				
				 System.exit(1);
			 }
		 }
		 
		 
		 
		 if(Game.gamestate==State.Help)
		 {
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				Game.gamestate=State.Menu;
//				AudioPlayer.getsound("click").play();
			 }	 
		 }
		 
		 
		 if(Game.gamestate==State.End)
		 {
			 if(mouseOver(mx,my,210,350,200,64))
			 {
//				AudioPlayer.getsound("click").play();
				Menu.x=0;
				Game.gamestate=State.Skin;
				hud.level(1);
				hud.score(0);
				hud.setPoint(0);
				handler.spd=5;
				HUD.Health=75;
				Player.defense=1;
				hud.bounds=0;
				spawn.scorekeep=0;
				for(int i=0;i<10;i++)
				{
					Shop.B[i]=1;
				}
				
			 }	 
		 }
		 if(Game.gamestate==State.End2)
		 {
			 Disconnect01 packet=new Disconnect01(this.game.player.getUsername());
			packet.writeData(this.game.socketclient);
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				AudioPlayer.getsound("click").play();
				Menu.x=0;
				Game.gamestate=State.Skin;
				hud.level(1);
				hud.score(0);
				hud.setPoint(0);
				handler.spd=5;
				HUD.Health=75;
				Player.defense=1;
//				HUD.Health2=;
				hud.bounds=0;
				spawn.scorekeep=0;
				for(int i=0;i<10;i++)
				{
					Shop.B[i]=1;
				}
				
				
			 }	 
		 }
		 
		 
		 if(Game.gamestate==State.Skin)
		 {
			 if(mouseOver(mx,my,100,100,40,40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(1);
			 }
			 if(mouseOver(mx,my,100,200,40,40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(2);
			 }
			 if(mouseOver(mx,my,100,300,40,40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(3);
			 }
			 if(mouseOver(mx,my,280, 100, 40, 40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(4);
			 }
			 if(mouseOver(mx,my,280, 200, 40, 40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(5);
			 }
			 if(mouseOver(mx,my,450, 100, 40, 40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(6);
			 }
			 if(mouseOver(mx,my,450, 200, 40, 40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(7);
			 }
			 if(mouseOver(mx,my,450, 300, 40, 40))
			 {
//				 AudioPlayer.getsound("click").play();
				 setskin(8);
			 }
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				Game.gamestate=State.Menu;
//				AudioPlayer.getsound("click").play();
			 }
			 if(Menu.x!=0)
			 {
				 Game.gamestate=State.MP;
			 }
			 
			 
		 }
		 
		 if(Game.gamestate==State.MP)
		 {
			 if(mouseOver(mx,my,210,150,200,64))
			 {
				 Game.gamestate=State.Select;
//				 AudioPlayer.getsound("click").play();
			 }
			 if(mouseOver(mx,my,210,250,200,64))
			 {
				 
//				 	AudioPlayer.getsound("click").play();
					yes=JOptionPane.showConfirmDialog(game, "Want to run server");
					if(Menu.yes==0)
					{
						game.socketServer=new Server(game,handler);
						game.socketServer.start();
						Menu.yes=1;
						
					}
					//25.60.165.249 //25.44.120.25
					Menu.sp=1;
					game.socketclient=new Client(game,"25.60.165.249",handler);
					game.socketclient.start();
					
					game.player=new PlayerMP(Game.Width/2-32,Game.Height/2-32,ID.Player,handler, null, -1, Game.username);
					
					Game.gamestate=State.Game;
					
					Login00 login=new Login00(game.player.getUsername(),game.player.x,game.player.y);
					if(game.socketServer != null)
					{
						game.socketServer.addConnection((PlayerMP) game.player, login);
					}
					
					login.writeData(game.socketclient); 
					
					 handler.addobject(game.player);
					 
					 game.diff=3;
				
			 }
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				 Menu.x=0;
				 Game.gamestate=State.Skin;
//				AudioPlayer.getsound("click").play();
			 }
			 
		 }
		 if(Game.gamestate==State.Select)
		 {
			
			 game.player=new Player(Game.Width/2-32,Game.Height/2-32,ID.Player,handler, Game.username);
			 int i=0;
			 if(mouseOver(mx,my,380,180,200,64))
			 {
					 Game.gamestate=State.Game;
//					 AudioPlayer.getsound("click").play();
					 
					 handler.addobject(game.player);
					 handler.addobject(new HardEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.HardEnemy,handler));
					 game.diff=1;
				 
			 }
			 if(mouseOver(mx,my,65,180,200,64))
			 {

					 //System.out.println(i);
					 Game.gamestate=State.Game;
//					 AudioPlayer.getsound("click").play();
					 
					 handler.addobject(game.player);
					 handler.addobject(new BasicEnemy(r.nextInt(Game.Width-50),r.nextInt(Game.Height-50),ID.BasicEnemy,handler));
					 game.diff=2;     
				 
			 }
			 if(mouseOver(mx,my,225,330,200,64))
			 {
				 Game.gamestate=State.MP;
//					AudioPlayer.getsound("click").play();
			 }
		 }
		 
	}
		 
	public void mouseMoved(MouseEvent e)
	{
		int mx=e.getX(),my=e.getY();
		if(Game.gamestate==State.Menu)
		 {
			 if(mouseOver(mx,my,210,150,200,64))
			 { 
				 menubutton[0]=Color.blue;
				
			 }else {
				 
				 menubutton[0]=Color.white;
			 }
			 
			 if(mouseOver(mx,my,210,250,200,64))
			 {
				 menubutton[1]=Color.green;
			 }else {
				 menubutton[1]=Color.white;
			 
			 }
			 
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				 menubutton[2]=Color.red;
			 }else {
				 menubutton[2]=Color.white;
			 
			 }
			 
			 
		 }
		if(Game.gamestate==State.MP)
		 {
			 if(mouseOver(mx,my,210,150,200,64))
			 { 
				 menubutton[3]=Color.blue;
				
			 }else {
				 
				 menubutton[3]=Color.white;
			 }
			 
			 if(mouseOver(mx,my,210,250,200,64))
			 {
				 menubutton[4]=Color.green;
			 }else {
				 menubutton[4]=Color.white;
			 
			 }
			 
			 if(mouseOver(mx,my,210,350,200,64))
			 {
				 menubutton[5]=Color.red;
			 }else {
				 menubutton[5]=Color.white;
			 
			 }
			 
			 
		 }
		if(Game.gamestate==State.Select)
		 {
			 if(mouseOver(mx,my,380,180,200,64))
			 { 
				 menubutton[7]=Color.blue;
				
			 }else {
				 
				 menubutton[7]=Color.white;
			 }
			 
			 if(mouseOver(mx,my,65,180,200,64))
			 {
				 menubutton[6]=Color.green;
			 }else {
				 menubutton[6]=Color.white;
			 
			 }
			 
			 if(mouseOver(mx,my,225,330,200,64))
			 {
				 menubutton[8]=Color.red;
			 }else {
				 menubutton[8]=Color.white;
			 
			 }
			 
			 
		 }
		if(Game.gamestate==State.Help)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				menubutton[11]=Color.red;
			 }else {
				 menubutton[11]=Color.white;
			 
			 }
		}
		if(Game.gamestate==State.Skin)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				menubutton[10]=Color.red;
			 }else {
				 menubutton[10]=Color.white;
			 
			 }
		}
		if(Game.gamestate==State.End)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				menubutton[9]=Color.red;
			 }else {
				 menubutton[9]=Color.white;
			 
			 }
		}
		
		
	}
	public  void mouseReleased(MouseEvent e)
	{
		int mx=e.getX(),my=e.getY();
		
		 
//		if(Game.gamestate==State.MP)
//		 {
//			 if(mouseOver(mx,my,210,150,200,64))
//			 {
//				 
//				 Game.gamestate=State.Select;
//			 }
//			 
//		 }

	}
	private boolean mouseOver(int mx,int my ,int x, int y,int width,int height)
	{
		if(mx>x && mx<x+width)
			if(my>y && my<y+height)
				return true;
			else return false;
		else return false;
			
	}
	public void tick()
	{
		
		movex=r.nextInt(7);
		movey=r.nextInt(7);
		
		if(menubutton[0]==Color.white)
		{
			bx[0]+=(230-bx[0])*0.05;
			bw[0]+=(200-bw[0])*0.05;
		}
		else {
			 bx[0]+=(110-bx[0])*0.05;
			 bw[0]+=(400-bw[0])*0.05;
		}
		
		if(menubutton[1]==Color.white)
		{
			bx[1]+=(230-bx[1])*0.05;
			bw[1]+=(200-bw[1])*0.05;
		}
		else {
			 bx[1]+=(110-bx[1])*0.05;
			 bw[1]+=(400-bw[1])*0.05;
		}
		
		if(menubutton[2]==Color.white)
		{
			bx[2]+=(230-bx[2])*0.05;
			bw[2]+=(200-bw[2])*0.05;
		}
		else {
			 bx[2]+=(110-bx[2])*0.05;
			 bw[2]+=(400-bw[2])*0.05;
		}
		
		
		
		if(menubutton[3]==Color.white)
		{
			bx[3]+=(230-bx[3])*0.05;
			bw[3]+=(200-bw[3])*0.05;
		}
		else {
			 bx[3]+=(110-bx[3])*0.05;
			 bw[3]+=(400-bw[3])*0.05;
		}
		
		if(menubutton[4]==Color.white)
		{
			bx[4]+=(230-bx[4])*0.05;
			bw[4]+=(200-bw[4])*0.05;
		}
		else {
			 bx[4]+=(110-bx[4])*0.05;
			 bw[4]+=(400-bw[4])*0.05;
		}
		
		if(menubutton[5]==Color.white)
		{
			bx[5]+=(230-bx[5])*0.05;
			bw[5]+=(200-bw[5])*0.05;
		}
		else {
			 bx[5]+=(110-bx[5])*0.05;
			 bw[5]+=(400-bw[5])*0.05;
		}
		
	}
	public void render(Graphics g)
	{
		
		if(Game.gamestate== State.Menu) {
			Font fnt=new Font("arial",1,50);
			Font fnt2=new Font("arial",1,30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("WAVE",(Wavex+movex),(Wavey+movey));
			g.setFont(fnt2);
			
			g.setColor(menubutton[0]);
			g.drawRect(bx[0],150,bw[0],64);	
			g.drawString("Play",270,190);
			g.setColor(Color.white);
			
			g.setColor(menubutton[1]);
			g.drawRect(bx[1],250,bw[1],64);	
			g.drawString("Help",270,290);
			g.setColor(Color.white);
			
			g.setColor(menubutton[2]);
			g.drawRect(bx[2],350,bw[2],64);	
			g.drawString("Quit",270,390);
			g.setColor(Color.white);
		}
		if(Game.gamestate== State.Help)
		{
			Font fnt2=new Font("arial",1,30);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Help",270,80);
			
			Font fnt=new Font("arial",1,20);
			g.setFont(fnt);
			g.drawString("Use WASD for player movements", 100, 150);
			g.drawString("Use spacebar for powers upgrade", 100, 220);
			g.drawString("Use arrow keys  for shooting", 100, 290);
			
			g.setFont(fnt2);
			g.setColor(menubutton[11]);
			g.drawRect(210,350,200,64);	
			g.drawString("Back",270,390);
			g.setColor(Color.white);
			
		}
		if(Game.gamestate== State.End)
		{
			Font fnt2=new Font("arial",1,30);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Game Over ",250,80);
			g.drawString("You lost with a score of:"+hud.getscore(), 50, 200);
			g.setColor(menubutton[9]);
			g.drawRect(210,350,200,64);	
			g.drawString("Try Again",250,390);
		}
		if(Game.gamestate== State.End2)
		{
			Font fnt2=new Font("arial",1,30);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Game Over ",250,80);
			g.drawString("You lost noob:", 50, 200);
			g.setColor(Color.white);
			g.drawRect(210,350,200,64);	
			g.drawString("Try Again",250,390);
		}
		
		if(Game.gamestate==State.Skin)
		{
			g.drawImage(image1, 100, 100, 40, 40, null);
			
			g.drawImage(image2, 100, 200, 40, 40, null);
			
			g.drawImage(image3, 100, 300, 40, 40, null);
			
			g.drawImage(image4, 280, 100, 40, 40, null);
			
			g.drawImage(image5, 280, 200, 40, 40, null);
			
			g.drawImage(image6, 450, 100, 40, 40, null);
			
			g.drawImage(image7, 450, 200, 40, 40, null);
			
			g.drawImage(image8, 450, 300, 40, 40, null);
			
			Font fnt2=new Font("arial",1,30);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Select Skin", 235+movex,50+movey);
			
			g.setColor(menubutton[10]);
			g.drawRect(210,350,200,64);	
			g.drawString("Back",270,390);
			g.setColor(Color.white);
		}
		if(Game.gamestate==State.Select) {
			Font fnt=new Font("arial",1,40);
			Font fnt2=new Font("arial",1,30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Mode",205,50);
			g.setFont(fnt2);
			
			g.setColor(menubutton[6]);
			g.drawRect(65,180,200,64);	
			g.drawString("Normal",110+movex,215+movey);
			g.setColor(Color.white);
			
			g.setColor(menubutton[7]);
			g.drawRect(380,180,200,64);	
			g.drawString("Hard",440+movex,215+movey);
			g.setColor(Color.white);
			
			g.setColor(menubutton[8]);
			g.drawRect(225,330,200,64);	
			g.drawString("Back",290+movex,370+movey);
			g.setColor(Color.white);
		}
		
		if(Game.gamestate== State.MP) {
			Font fnt=new Font("arial",1,40);
			Font fnt2=new Font("arial",1,30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Mode",205+movex,50+movey);
			g.setFont(fnt2);
			
			g.setColor(menubutton[3]);
			g.drawRect(bx[3],150,bw[3],64);
			g.drawString("Singleplayer",220,190);
			g.setColor(Color.white);
			
			g.setColor(menubutton[4]);
			g.drawRect(bx[4],250,bw[4],64);
			g.drawString("Multiplayer",220,290);
			g.setColor(Color.white);
			
			g.setColor(menubutton[5]);
			g.drawRect(bx[5],350,bw[5],64);
			g.drawString("Back",270,390);
			g.setColor(Color.white);
		}
		
		

	}
	public static void setskin(int x)
	{
		Menu.x=x;
	}
	public static int getskin()
	{
		return x;
	}
		
}

