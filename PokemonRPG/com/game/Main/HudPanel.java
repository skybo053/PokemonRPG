package com.game.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class HudPanel extends JPanel
{
  
  Thread     mainThread   = null; 
  Graphics2D panelContext = null;   
  boolean    run;
  
  int        displayWidth;
  int        displayHeight;
  
  //buffered image variables
  BufferedImage bImg        = null;
  Graphics2D    bImgContext = null;
  
  //Health meter variables
  Graphics2D  graphics2D           = null;
  int         distanceFromLeft     = 45;
  int         distanceFromTop      = 20;
  int         healthMeterWidth     = 50;
  int         percentHealth        = 100;
  int         healthMeterHeight    = 100;
  int         damageMeterStartYPos = healthMeterHeight + distanceFromTop - percentHealth;
  
  
  public HudPanel(int pWidth, int pHeight)
  {
    displayWidth  = pWidth;
    displayHeight = pHeight;
    
    bImg        = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_4BYTE_ABGR);
    bImgContext = bImg.createGraphics();
    
    this.setPreferredSize(new Dimension(displayWidth-1, displayHeight));
    this.setBackground(new Color(255, 255, 255, 0));
  }
  
  
  public void drawHUD()
  {
    renderHUD();
    repaint();
  }
  
  
  private void renderHUD()
  {
    //clear buffer
    bImgContext.setBackground(new Color(255, 255, 255, 0));
    bImgContext.clearRect(0, 0, displayWidth, displayHeight);
    
    
    drawHealthMeter();
  }
  
  
  private void drawHealthMeter()
  {
    //Health meter 
    bImgContext.setColor(Color.black);
    bImgContext.fillRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
    
    //health bar
    bImgContext.setStroke(new BasicStroke(0));
    bImgContext.setColor(Color.red);
    bImgContext.fillRect(distanceFromLeft, damageMeterStartYPos, healthMeterWidth, percentHealth);
    
    //Border
    bImgContext.setStroke(new BasicStroke(5));
    bImgContext.setColor(Color.black);
    bImgContext.drawRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
  }
  
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(bImg, 0, 0, null);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  /*public void drawHUD()
  {
    Graphics2D graphics2D = (Graphics2D)this.getGraphics();
    
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
    
    graphics2D.dispose();
  }*/
  
  
  public void setHealth(int pHealth)
  {
    if(percentHealth + pHealth < 0)
    {
      percentHealth = 0;
      return;
    }
    
    percentHealth        += pHealth;
    damageMeterStartYPos -= pHealth;
  }
 
}


