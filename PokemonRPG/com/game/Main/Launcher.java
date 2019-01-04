package com.game.Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;


public class Launcher
{
  public static int screenWidth;
  public static int screenHeight;
  
  private JFrame       oJFrame       = null;
  private EffectsPanel oEffectsPanel = null;
  private HudPanel     oHudPanel     = null;
  private GamePanel    oGamePanel    = null;
  private JLayeredPane oLayeredPane  = null;
  
  
  public static void main(String[] args)
	{
	  Launcher vMainGame = null;
	  
	  vMainGame = new Launcher();
	  
	  vMainGame.setScreenSizeVars();
	  vMainGame.buildLayeredPane();
	  vMainGame.createFrame();
	  vMainGame.startGame();
	}
  
  
  private void startGame()
  {
    oGamePanel.start();
  }
	
	
	private void createFrame()
	{
	  oJFrame = new JFrame("PokemonRPG v. 1.0.0");
	  
	  oJFrame.add(oLayeredPane);
    oJFrame.setSize(screenWidth, screenHeight);
    oJFrame.setLocationRelativeTo(null);
    oJFrame.setUndecorated(true);
    oJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    oJFrame.setVisible(true);
  }
	
	
	private void buildLayeredPane()
	{
	  oLayeredPane  = new JLayeredPane();
	  oEffectsPanel = new EffectsPanel(screenWidth, screenHeight);
	  oHudPanel     = new HudPanel(screenWidth, screenHeight);
	  oGamePanel    = new GamePanel(oHudPanel, oEffectsPanel);
	  
	  oEffectsPanel.setBounds(0, 0, screenWidth, screenHeight);
	  oHudPanel.setBounds(0, 0, screenWidth, screenHeight);
	  oGamePanel.setBounds(0, 0, screenWidth, screenHeight);
	  
	  oLayeredPane.add(oEffectsPanel, JLayeredPane.MODAL_LAYER);
    oLayeredPane.add(oHudPanel, JLayeredPane.PALETTE_LAYER);
    oLayeredPane.add(oGamePanel, JLayeredPane.DEFAULT_LAYER);
   }
	
	
	private void setScreenSizeVars()
	{
	  Dimension  vScreenSize   = null;
	  
	  vScreenSize  = Toolkit.getDefaultToolkit().getScreenSize();
	  
	  screenWidth  = vScreenSize.width;
    screenHeight = vScreenSize.height;
	}
}
