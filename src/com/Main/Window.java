package com.Main;

import java.awt.*;
import javax.swing.*;

public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	//private BufferedImage image;
	public JFrame frame;
	public Window(int height,int width, String title, Game game) 
	{
		frame=new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//frame.addWindowListener(win);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
	}
}

