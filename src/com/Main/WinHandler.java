package com.Main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Wave.net.packet.Disconnect01;

public class WinHandler implements WindowListener{

	private Game game;
	public WinHandler(Game game)
	{
		this.game=game;
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(Menu.sp==1)
		{
			Disconnect01 packet=new Disconnect01(this.game.player.getUsername());
			packet.writeData(this.game.socketclient);
		}
		else {
			System.exit(1);
			}
		
		//System.out.println("hello");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
