package com.game.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.game.Entities.Player;


public class HudPanel extends JPanel
{
  private Player      oPlayer              = null;
  private int         oHealthBarHealth      = 0;
  private int         oPlayerHealth         = 0;
  
  private int         displayWidth;
  private int         displayHeight;
  private Graphics2D  oGraphics2d;
  
  //Health meter variables
  private Graphics2D  graphics2D           = null;
  private int         distanceFromLeft     = 0;    
  private double      percentFromLeft      = .08;
  private int         distanceFromTop      = 20;
  private int         healthMeterWidth     = 50;
  private int         healthMeterHeight    = 100;
  private int         damageMeterStartYPos = healthMeterHeight + distanceFromTop - oHealthBarHealth;
  
  
  public HudPanel(int pWidth, int pHeight)
  {
    displayWidth  = pWidth;
    displayHeight = pHeight;
    
    this.setVisible(false);
    this.setOpaque(false);
    
    distanceFromLeft = (int)(displayWidth * percentFromLeft);
  }
  
  
  public void update()
  {
    oPlayerHealth = oPlayer.getHealth();
    
   if(oHealthBarHealth < oPlayerHealth)
    {
      setHealthBar(1);
    }
    else if(oHealthBarHealth > oPlayerHealth)
    {
      setHealthBar(-1);
    }
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
    oGraphics2d.fillRect(distanceFromLeft, damageMeterStartYPos, healthMeterWidth, oHealthBarHealth);
    
    //Border
    oGraphics2d.setStroke(new BasicStroke(5));
    oGraphics2d.setColor(Color.black);
    oGraphics2d.drawRect(distanceFromLeft, distanceFromTop, healthMeterWidth, healthMeterHeight);
  }
  
  
  public void setHealthBar(int pHealth)
  {
    if(oHealthBarHealth + pHealth < 0)
    {
      oHealthBarHealth = 0;
    }
    else if(oHealthBarHealth + pHealth > 100)
    {
      oHealthBarHealth = 100;
    }
    else
    {
      oHealthBarHealth     += pHealth;
      damageMeterStartYPos -= pHealth;
    }
  }
  
  
  public void setPlayer(Player pPlayer)
  {
    oPlayer = pPlayer;
  }
 
}