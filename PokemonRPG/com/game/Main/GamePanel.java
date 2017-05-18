package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable
{
  
  public GamePanel(int pWidth, int pHeight)
  {
    System.out.println("calling game panel");
    
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
    this.setBackground(Color.pink);
    this.add(new HudPanel(pWidth, pHeight));
    
  }
  
  
  public void run()
  {
    
  }
}
