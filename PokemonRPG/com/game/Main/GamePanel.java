package com.game.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable
{
  
  HudPanel      hudPanel      = null;
  
  Thread        mainThread    = null;
  boolean       run;
  
  BufferedImage bImg          = null;
  Graphics2D    bImgContext   = null;
  Graphics      panelContext  = null;
  
  int displayWidth;
  int displayHeight;
  
  int x = 0;
  
  
  public GamePanel(int pWidth, int pHeight)
  {
    displayWidth  = pWidth;
    displayHeight = pHeight;
    
    hudPanel = new HudPanel(pWidth, pHeight);
    
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    this.setBackground(Color.pink);
    this.add(hudPanel);
    
   /* mainThread = new Thread(this);
    mainThread.start();*/
  }
  
  
 public void addNotify()
  {
    super.addNotify();
    
    if(mainThread == null)
    {
      mainThread = new Thread(this);
      mainThread.start();
    }
  }
  
  
  private void init()
  {
    run         = true;
    
    bImg        = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_4BYTE_ABGR);
    bImgContext = bImg.createGraphics();
  }
  
  
  public void run()
  {
    
    init();
    
    while(run)
    {
      x++;
      
      update();
      render();
      repaint();
      
      try
      {
        Thread.sleep(100);
      }
      catch(InterruptedException e)
      {
        
      }
    }
  }
  
  
  public void update()
  {
    hudPanel.setHealth(-1);
  }
  
  
  public void render()
  {
    //clear buffered image by drawing rectangle over previous image
    bImgContext.setBackground(new Color(255,255,255,0));
    bImgContext.clearRect(0, 0, displayWidth, displayHeight);
    
    bImgContext.setColor(Color.black);
    bImgContext.fillRect(x, 15, 45, 45);
  }
  
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    g.drawImage(bImg, 0, 0, null);
    hudPanel.drawHUD();
    
  }
  
  
  
  
  
  public void draw()
  {
    panelContext = this.getGraphics();
    panelContext.drawImage(bImg, 0, 0, null);
    
    //hudPanel.drawHUD();
    
    panelContext.dispose();
  }
  
  
}



