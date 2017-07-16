package com.game.States;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;

import com.game.FX.Assets;
import com.game.FX.JukeBox;

public abstract class BaseSplashScreen 
{
  protected String            name;
  
  protected int               displayWidth;
  protected int               displayHeight;
  
  protected int               alphaValue;
  protected int               deltaAlpha;
  
  protected BufferedImage     image;
  protected int               imageXOffset;
  protected int               imageYOffset;
  
  protected JukeBox           jukeBox;
  protected AudioInputStream  audioStream;
  
  protected long              startTime;
  
  protected boolean           fadeOut;
  protected boolean           isDone;
  protected int               waitTime;
  
  
  public BaseSplashScreen(
      String           pName, 
      int              pDisplayWidth, 
      int              pDisplayHeight,
      int              pWaitTime,
      boolean          pFadeOut,
      BufferedImage    pImage,
      AudioInputStream pAudioStream)
  {
    
    name          = pName;
    fadeOut       = pFadeOut;
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    image         = pImage;
    
    alphaValue = 255;
    deltaAlpha = -1;
    
    imageXOffset = Assets.getWidth(image) / 2;
    imageYOffset = Assets.getHeight(image) / 2;
    
    audioStream  = pAudioStream;
    waitTime     = pWaitTime;
    
    isDone    = false;
    
    jukeBox   = new JukeBox();
  }
  
  
  public abstract void update();
  
  
  public abstract void draw(Graphics pGraphics);
  
  
  public void wait(int pWaitTime)
  {
    long vStartTime   = System.currentTimeMillis();
    long vElapsedTime = 0L;
    long vNow         = 0L;
    
    while(true)
    {
      vNow         = System.currentTimeMillis();
      vElapsedTime = vNow - vStartTime;
      
      if(vElapsedTime >= pWaitTime)
      {
        break;
      }
    }
  }
  
  
  protected String getName()
  {
    return name;
  }
  
  
  protected void setName(String pName)
  {
    name = pName;
  }
  
  
  protected boolean isDone()
  {
    return isDone;
  }
  
  
  protected void setStartTime()
  {
    startTime = System.currentTimeMillis();
  }
}
