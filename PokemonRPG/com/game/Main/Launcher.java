package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Launcher
{
  public static int screenWidth;
  public static int screenHeight;
	
	public static void main(String args[])
	{
	  JFrame       vJFrame       = null;
	  JLayeredPane vLayeredPane  = null;
	  Dimension    vScreenSize   = null;
	  
	  EffectsPanel vEffectsPanel = null;
	  HudPanel     vHudPanel     = null;
	  GamePanel    vGamePanel    = null;
	  
	  vLayeredPane = new JLayeredPane(); 
	  vJFrame      = new JFrame("PokemonRPG v. 1.0.0");
	  vScreenSize  = Toolkit.getDefaultToolkit().getScreenSize();
	  
	  screenWidth  = vScreenSize.width;
	  screenHeight = vScreenSize.height;
	  
	  //vLayeredPane.setPreferredSize(new Dimension(screenWidth, screenHeight));
	  
	  vHudPanel = new HudPanel(screenWidth, screenHeight);
	  vHudPanel.setBounds(0, 0, screenWidth, screenHeight);
	  //vHudPanel.setVisible(false);
	  
	  vEffectsPanel = new EffectsPanel(screenWidth, screenHeight);
	  vEffectsPanel.setBounds(0, 0, screenWidth, screenHeight);
	  
	  vGamePanel = new GamePanel(vHudPanel, vEffectsPanel);
	  vGamePanel.setBounds(0, 0, screenWidth, screenHeight);
	  
	  
	 
	  
	 
	  vLayeredPane.add(vEffectsPanel, JLayeredPane.MODAL_LAYER);
	  vLayeredPane.add(vHudPanel, JLayeredPane.PALETTE_LAYER);
	  vLayeredPane.add(vGamePanel, JLayeredPane.DEFAULT_LAYER);
	  
	  
	  //vJFrame.getContentPane().setBackground(Color.white);
	  vJFrame.add(vLayeredPane);
	  vJFrame.setSize(screenWidth, screenHeight);
		vJFrame.setLocationRelativeTo(null);
		vJFrame.setUndecorated(true);
		vJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vJFrame.setVisible(true);
		
		
	}
	
}
