package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EffectsPanel extends JPanel
{
  int displayWidth;
  int displayHeight;
  
  int alpha = 0;
  int delta = 3;
  
  public EffectsPanel(int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    this.setOpaque(false);
    this.setPreferredSize(new Dimension(displayWidth, displayHeight));
    this.setBackground(new Color(255, 255, 255, 0));
  }
  
  public void update()
  {
    if(alpha + delta > 255)
    {
      alpha = 255;
    }
    else
    {
      alpha += delta;
    }
    
    System.out.println("EffectPanel alphaValue = " + alpha);
  }
  
  public void drawEffect()
  {
    //this.paintImmediately(0, 0, displayWidth, displayHeight);
    repaint();
  }
  
  
  public void paintComponent(Graphics pGraphics)
  {
    super.paintComponent(pGraphics);
    
    pGraphics.setColor(new Color(0,0,0,alpha));
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
  }
}
