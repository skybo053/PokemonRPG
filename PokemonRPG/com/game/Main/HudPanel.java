package com.game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class HudPanel extends JPanel implements Runnable
{
  Thread        oMainThread          = null;           
  
  BufferedImage oBufferedImg         = null;
  Graphics2D    oBufferedImgContext  = null;
  
  int oDisplayWidth;
  int oDisplayHeight;
  
  
  public HudPanel(int pWidth, int pHeight)
  {
    oDisplayWidth  = pWidth;
    oDisplayHeight = pHeight;
    
    this.setPreferredSize(new Dimension(oDisplayWidth, oDisplayHeight));
    this.setBackground(new Color(0,104, 255, 0));
    
    oMainThread = new Thread(this);
    oMainThread.start();
    
  }
  
  
  public void run()
  {
    
  }

}
