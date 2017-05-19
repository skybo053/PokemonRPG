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
  HudPanel hudPanel           = null;
  
  Thread   mainThread         = null;
  boolean  run;
  
  BufferedImage bImg         = null;
  Graphics2D    bImgContext  = null;
  
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
    
  }
  
  
  public void addNotify()
  {System.out.println("in add notify!");
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
    
    bImg        = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_3BYTE_BGR);
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
      draw();
      
      try
      {
        Thread.sleep(45);
      }
      catch(InterruptedException e)
      {
        
      }
    }
  }
  
  
  public void update()
  {
    
  }
  
  public void render()
  {
    bImgContext.setColor(Color.pink);
    bImgContext.fillRect(0, 0, displayWidth, displayHeight);
    
    bImgContext.setColor(Color.black);
    bImgContext.fillRect(x, 15, 45, 45);
    
  }
  
  public void draw()
  {
    Graphics panelContext = this.getGraphics();
    
    
    panelContext.drawImage(bImg, 0, 0, null);
    hudPanel.drawHUD();
    
    panelContext.dispose();
    
    
  }
  
}
