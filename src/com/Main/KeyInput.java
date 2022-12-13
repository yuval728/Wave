package com.Main;

import java.awt.event.KeyAdapter;

import com.Main.Game.State;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keys=new boolean[4];
	private boolean[] bullkeys=new boolean[4];
	public KeyInput(Handler handler)
	{
		this.handler=handler;
		keys[0]=false;
		keys[1]=false;
		keys[2]=false;
		keys[3]=false;
		bullkeys[0]=false;
		bullkeys[1]=false;
		bullkeys[2]=false;
		bullkeys[3]=false;
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			//if(key!=0)
			{
				if(temp.getID()==ID.Player)
				{
					if(key== KeyEvent.VK_W) {temp.setvely(-handler.spd); keys[0]=true;}
					if(key== KeyEvent.VK_S) {temp.setvely(handler.spd); keys[1]=true;}
					if(key== KeyEvent.VK_A) {temp.setvelx(-handler.spd); keys[2]=true;}
					if(key== KeyEvent.VK_D) {temp.setvelx(handler.spd); keys[3]=true;}
					if(key== KeyEvent.VK_UP){
						Bullets.fire=1;
						Player.buly=-5;
						Player.bulx=0;
						bullkeys[0]=true;
					}
					if(key== KeyEvent.VK_DOWN){
						Bullets.fire=1;
						Player.buly=5;
						Player.bulx=0;
						bullkeys[1]=true;
					}
					if(key== KeyEvent.VK_LEFT){
						Bullets.fire=1;
						Player.buly=0;
						Player.bulx=-5;
						bullkeys[2]=true;
					}
					if(key== KeyEvent.VK_RIGHT){
						Bullets.fire=1;
						Player.buly=0;
						Player.bulx=5;
						bullkeys[3]=true;
					}
					if(bullkeys[1] && bullkeys[2]){
						Bullets.fire=1;
						Player.buly=5;
						Player.bulx=-5;
					}
					if(bullkeys[1] && bullkeys[3]){
						Bullets.fire=1;
						Player.buly=5;
						Player.bulx=5;
					}
					if(bullkeys[0] && bullkeys[2]){
						Bullets.fire=1;
						Player.buly=-5;
						Player.bulx=-5;
					}
					if(bullkeys[0] && bullkeys[3]){
						Bullets.fire=1;
						Player.buly=-5;
						Player.bulx=5;
					}
				}
			}
			
			
		}
		
		if(key==KeyEvent.VK_ESCAPE)
		{
			if(Game.gamestate==State.Game)
			{
				if(Game.paused) Game.paused=false;
				else Game.paused=true;
			}
			
		}
		
		if(key==KeyEvent.VK_SPACE)
		{
			if(Game.gamestate==State.Game)
			{
				Game.gamestate=State.Shop;
				
			}
			else if(Game.gamestate==State.Shop) {
				Game.gamestate=State.Game;
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			
			if(temp.getID()==ID.Player)
			{
				if(key== KeyEvent.VK_W) keys[0]=false;
				if(key== KeyEvent.VK_S) keys[1]=false;
				if(key== KeyEvent.VK_A) keys[2]=false;
				if(key== KeyEvent.VK_D) keys[3]=false;
				
				if(!keys[0] &&!keys[1]) temp.setvely(0);
				if(!keys[2] &&!keys[3]) temp.setvelx(0);
				
				if(key== KeyEvent.VK_UP) bullkeys[0]=false;
				if(key== KeyEvent.VK_DOWN) bullkeys[1]=false;
				if(key== KeyEvent.VK_LEFT) bullkeys[2]=false;
				if(key== KeyEvent.VK_RIGHT) bullkeys[3]=false;
			}
			
		}
	}
}
