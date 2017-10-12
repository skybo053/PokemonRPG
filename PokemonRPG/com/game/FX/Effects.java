package com.game.FX;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.game.Main.GamePanel;

public class Effects extends Object
{
  
  public static void fade(
      GamePanel pGame, 
      int       pStartAlpha, 
      int       pDeltaAlpha, 
      Color     pColor, 
      long      pFadeTime)
  {
    System.out.println("in Effects.fade.");
    
    Fade vFade     = null;
    Thread vThread = null;
    
    vFade = new Fade(
        pGame,
        pStartAlpha,
        pDeltaAlpha,
        pColor,
        pFadeTime);
    
    vThread = new Thread(vFade, "EFFECT_THREAD");
    vThread.start();
    
    while(vThread.isAlive());
  }
  
}//end of effects class



class Fade implements Runnable
{
  private GamePanel     oGame        = null;
  private Integer       oStartAlpha  = null;
  private Integer       oDeltaAlpha  = null;
  private Color         oColor       = null;
  private Long          oFadeTime    = null;
  
  private int oRed   = 0;
  private int oGreen = 0;
  private int oBlue  = 0;
  
  public Fade(
      GamePanel pGame,
      Integer   pStartAlpha,
      Integer   pDeltaAlpha,
      Color     pColor,
      Long      pFadeTime)
  {
    oGame       = pGame;
    oStartAlpha = pStartAlpha;
    oDeltaAlpha = pDeltaAlpha;
    oColor      = pColor;
    oFadeTime   = pFadeTime;
    
    oRed   = oColor.getRed();
    oGreen = oColor.getGreen();
    oBlue  = oColor.getBlue();
  }
  
  public void run()
  {
    long          vStartTime         = 0L;
    long          vElapsedTime       = 0L;
    Graphics      vGamePanelCanvas   = null;
    BufferedImage vBufferedImage     = null;
    Graphics2D    vBImageContext     = null;
    Graphics2D    vGamePanelContext  = null;
    
    vBufferedImage = new BufferedImage(
        GamePanel.displayWidth, 
        GamePanel.displayHeight,
        BufferedImage.TYPE_INT_ARGB);
    
    vBImageContext = vBufferedImage.createGraphics();
    
    vStartTime   = System.currentTimeMillis();
    vElapsedTime = System.currentTimeMillis() - vStartTime;
    
    while(vElapsedTime < oFadeTime)
    {
      vBImageContext.setColor(new Color(0, 0, 0, oStartAlpha));
      vBImageContext.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
     
      vGamePanelContext = (Graphics2D)oGame.getGraphics();
      vGamePanelContext.drawImage(vBufferedImage, 0, 0, null);
      vGamePanelContext.dispose();
      
     if(oStartAlpha + oDeltaAlpha <= 255)
      {
        oStartAlpha += oDeltaAlpha;
      }
      else
      {
        oStartAlpha = 255;
      }
      
      System.out.println("Alpha is: " + oStartAlpha + " elapsedtime: " + vElapsedTime);
      try
      {
        Thread.sleep(60);
      }
      catch(InterruptedException e)
      {
        return;
      }
      
      
      vElapsedTime = System.currentTimeMillis() - vStartTime;
    }
  }
}


  



