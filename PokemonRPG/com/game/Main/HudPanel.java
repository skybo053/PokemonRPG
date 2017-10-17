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
  int         displayWidth;
  int         displayHeight;
  Graphics2D  oGraphics2d;
  
  //Health meter variables
  Graphics2D  graphics2D           = null;
  int         distanceFromLeft     = 0;    
  double      percentFromLeft      = .08;
  int         distanceFromTop      = 20;
  int         healthMeterWidth     = 50;
  int         percentHealth        = 50;
  int         healthMeterHeight    = 100;
  int         damageMeterStartYPos = healthMeterHeight + distanceFromTop - percentHealth;
  
  
  public HudPanel(int pWidth, int pHeight)
  {
    displayWidth  = pWidth;
    displayHeight = pHeight;
    
    this.setVisible(false);
    this.setOpaque(false);
    
    distanceFromLeft = (int)(displayWidth * percentFromLeft);
  }
  
  
  public void drawHUD()
  {
    //this.paintImmediately(0, 0, displayWidth, displayHeight);
   repaint();
  }
  
  
  public void paintComponent(Graphics pGraphics)
  {
    super.paintComponent(pGraphics);
    
    drawHealthMeter(pGraphics);
  }
  
  
  private void drawHealthMeter(Graphics pGraphics)
  {
    oGraphics2d = (Graphics2D)pGraphics;
    
    //Health meter 
    oGraphics2d.setColor(Color.black);
    oGraphics2d.fillRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
    
    //health bar
    oGraphics2d.setStroke(new BasicStroke(0));
    oGraphics2d.setColor(Color.red);
    oGraphics2d.fillRect(distanceFromLeft, damageMeterStartYPos, healthMeterWidth, percentHealth);
    
    //Border
    oGraphics2d.setStroke(new BasicStroke(5));
    oGraphics2d.setColor(Color.black);
    oGraphics2d.drawRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
  }
  
  
  public void setHealth(int pHealth)
  {
    if(percentHealth + pHealth < 0)
    {
      percentHealth = 0;
      return;
    }
    
    if(percentHealth + pHealth > 100)
    {
      percentHealth = 100;
      return;
    }
    
    percentHealth        += pHealth;
    damageMeterStartYPos -= pHealth;
  }
 
}


