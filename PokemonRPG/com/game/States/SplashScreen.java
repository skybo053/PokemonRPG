package com.game.States;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;

import com.game.FX.Assets;
import com.game.FX.FadeEffect;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;

public class SplashScreen 
{
  private String            name;
  
  private BufferedImage     image;
  private int               imageXOffset;
  private int               imageYOffset;
  
  private JukeBox           jukeBox;
  private boolean           isDone;
  
  private FadeEffect        fadeEffect;
  private long              splashScreenDuration;
  private long              splashScreenStartTime;

  
  public SplashScreen(
      String           pName,
      long             pDuration,
      BufferedImage    pImage,
      AudioInputStream pAudioStream) 
  {
    name                  = pName;
    image                 = pImage;
    splashScreenDuration  = pDuration;
    splashScreenStartTime = 0L;
    
    imageXOffset = Assets.getWidth(image) / 2;
    imageYOffset = Assets.getHeight(image) / 2;
    
    isDone    = false;
    
    if(pAudioStream != null)
    {
      jukeBox   = new JukeBox(pAudioStream);
    }
  }
  
  
  public void initSplashScreen()
  {
    if(jukeBox != null)
    {
      jukeBox.open();
    }
    
    splashScreenStartTime = System.currentTimeMillis();
  }

  
  public void update() 
  {
    if((   System.currentTimeMillis() - splashScreenStartTime) >= fadeEffect.getWaitTime()
        && jukeBox != null 
        && jukeBox.isPlaying() == false)
    {
      jukeBox.play();
    }
    
    
    if((System.currentTimeMillis() - splashScreenStartTime) >= splashScreenDuration)
    {
      isDone = true;
      fadeEffect.setIsDone();
      
      if(jukeBox != null)
      {
        jukeBox.stop();
        jukeBox.close();
      }
    }
  }
  
  
  public void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(
        image,
        GamePanel.displayWidth/2 - imageXOffset, 
        GamePanel.displayHeight/2 - imageYOffset, 
        null);
  }
  
  
  public void closeAudio()
  {
    if(jukeBox != null)
    {
      if(jukeBox.isPlaying())
      {
        jukeBox.stop();
      }
      jukeBox.close();
    }
  }
 
  
  public String getName()
  {
    return name;
  }
  
  
  public void setName(String pName)
  {
    name = pName;
  }
  
  
  public boolean isDone()
  {
    return isDone;
  }
  
  
  public void setIsDone()
  {
    isDone = true;
  }
  
  
  public boolean hasAudio()
  {
    if(jukeBox == null)
    {
      return false;
    }
    return true;
  }
  
  
  public long getSplashScreenDuration()
  {
    return splashScreenDuration;
  }
  
  
  public FadeEffect getFadeEffect()
  {
    return fadeEffect;
  }
  
  
  public void setFadeEffect(FadeEffect pFadeEffect)
  {
    fadeEffect = pFadeEffect;
  }
  
  
  public void interrupt()
  {
    Thread.currentThread().interrupt();
  }
}
