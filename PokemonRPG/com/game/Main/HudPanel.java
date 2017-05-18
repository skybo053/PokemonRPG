package com.game.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class HudPanel extends JPanel implements Runnable
{
  Thread        mainThread          = null; 
  boolean       run;
  
  BufferedImage bufferedImg         = null;
  Graphics2D    bufferedImgContext  = null;
  
  //Panel and BufferedImage width and height
  
  int displayWidth;
  int displayHeight;
  
  //Health meter variables
  
  Graphics2D  graphics2D           = null;
  int         distanceFromLeft     = 25;
  int         distanceFromTop      = 20;
  int         healthMeterWidth     = 50;
  int         percentHealth        = 100;
  int         healthMeterHeight    = 100;
  int         damageMeterStartYPos = healthMeterHeight + distanceFromTop - percentHealth;
  
  
  public HudPanel(int pWidth, int pHeight)
  {
    displayWidth  = pWidth;
    displayHeight = pHeight;
    
    this.setPreferredSize(new Dimension(displayWidth, displayHeight));
    this.setBackground(new Color(255, 255, 255, 0));
    
    run        = true;
    mainThread = new Thread(this);
    mainThread.start();
    
  }
  
  
  public void run()
  {
    
    while(run)
    {
      repaint();
    }
  }
  
  
  public void paintComponent(Graphics g)
  {
    graphics2D = (Graphics2D) g;
    super.paintComponent(graphics2D);
    
    //Health meter 
    graphics2D.setColor(Color.black);
    graphics2D.fillRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
    
    //health bar
    graphics2D.setStroke(new BasicStroke(0));
    graphics2D.setColor(Color.red);
    graphics2D.fillRect(distanceFromLeft, damageMeterStartYPos, healthMeterWidth, percentHealth);
    
    //Border
    graphics2D.setStroke(new BasicStroke(5));
    graphics2D.setColor(Color.black);
    graphics2D.drawRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
    
    
  }

}
