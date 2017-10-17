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
  
  private int               alphaValue;
  private int               deltaAlpha;
  
  private BufferedImage     image;
  private int               imageXOffset;
  private int               imageYOffset;
  
  private JukeBox           jukeBox;
  
  private long              startTime;
  
  private boolean           fadeOut;
  private boolean           isDone;
  private Integer           waitTime;
  
  private FadeEffect fadeEffect;

  
  public SplashScreen(
      String           pName,
      Integer          pWaitTime,
      boolean          pFadeOut,
      BufferedImage    pImage,
      AudioInputStream pAudioStream,
      FadeEffect       pFadeEffect) 
  {
    fadeEffect    = pFadeEffect;
    name          = pName;
    fadeOut       = pFadeOut;
    image         = pImage;
   
    alphaValue = 255;
    deltaAlpha = -1;
    
    imageXOffset = Assets.getWidth(image) / 2;
    imageYOffset = Assets.getHeight(image) / 2;
    
    waitTime     = pWaitTime;
    
    isDone    = false;
    
    if(pAudioStream != null)
    {
      jukeBox   = new JukeBox(pAudioStream);
    }
  }

  
  public void update() 
  {
    
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
  
  
  private void startAudio()
  {
    if(jukeBox == null     ||
       jukeBox.isPlaying() == true) 
    {
      return;
    }
    jukeBox.play();
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
    System.out.println("setting " + this.getName() + " to done");
    isDone = true;
  }
  
  
  public void setStartTime()
  {
    startTime = System.currentTimeMillis();
  }
  
  
  public boolean hasAudio()
  {
    if(jukeBox == null)
    {
      return false;
    }
    return true;
  }
  
  
  public FadeEffect getFadeEffect()
  {
    return fadeEffect;
  }
  
  
  public void pause(int pWaitTime)
  {
    if(pWaitTime == 0) return;
    System.out.println(getName() + " beginning pause");
    
    long vStartTime   = 0L;
    long vElapsedTime = 0L;
    long vNow         = 0L;

    vStartTime = System.currentTimeMillis();
    
    while(isDone == false && vElapsedTime <= pWaitTime)
    {
      System.out.println(getName() + " pausing...");
      vNow         = System.currentTimeMillis();
      vElapsedTime = vNow - vStartTime;
    }
    waitTime = 0;
  }
  
  
  public void interrupt()
  {
    System.out.println("calling interrupt");
    Thread.currentThread().interrupt();
  }
}
