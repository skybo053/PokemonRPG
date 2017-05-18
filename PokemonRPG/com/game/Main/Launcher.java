package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Launcher
{
	
	public static void main(String args[])
	{
	  JFrame    vJFrame = new JFrame("PokemonRPG v. 1.0.0");
	  
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  
    vJFrame.setSize(screenSize.width, screenSize.height);
		vJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vJFrame.setLocationRelativeTo(null);
		vJFrame.setUndecorated(true);
		
		vJFrame.add(new GamePanel(screenSize.width, screenSize.height));
		vJFrame.setVisible(true);
		
	}
}
