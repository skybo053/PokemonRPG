package com.game.Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Launcher
{
	
	public static void main(String args[])
	{
	  JFrame    vJFrame = new JFrame("PokemonRPG v. 1.0.0");
	  
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  
	  vJFrame.add(new GamePanel(screenSize.width, screenSize.height));
	  
    vJFrame.setSize(screenSize.width, screenSize.height);
		vJFrame.setLocationRelativeTo(null);
		vJFrame.setUndecorated(true);
		vJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vJFrame.setVisible(true);
		
		
	}
	
}
