package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;

import com.game.FX.Assets;
import com.game.FX.Effects;
import com.game.FX.JukeBox;

public class SplashScreen 
{
  private Graphics          graphics;
  private String            name;
  
  private int               displayWidth;
  private int               displayHeight;
  
  private int               alphaValue;
  private int               deltaAlpha;
  
  private BufferedImage     image;
  private int               imageXOffset;
  private int               imageYOffset;
  
  private JukeBox           jukeBox;
  private AudioInputStream  audioStream;
  
  private long              startTime;
  
  private boolean           fadeOut;
  private boolean           isDone;
  private Integer           waitTime;

  public SplashScreen(
      String           pName, 
      int              pDisplayWidth, 
      int              pDisplayHeight,
      Integer          pWaitTime,
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

  
  public void update() 
  {
    long vNow     = System.currentTimeMillis();
    long vElapsed = vNow - startTime;
    
    if(vElapsed < 2000)
    {
      return;
    }
    else if(vElapsed < 4000)
    {
      startAudio();
      deltaAlpha = -2;
    }
    
    
    if(alphaValue + deltaAlpha >= 0 &&
       alphaValue + deltaAlpha <= 255)
    {
      alphaValue += deltaAlpha;
    }
    else if(vElapsed < 11000)
    {
      if(fadeOut)
      {
        Effects.pause(waitTime);
        deltaAlpha = 2;
      }
      else
      {
        alphaValue = 0;
      }
    }
    else
    {
      closeAudio();
      isDone = true;
    }
  }
  
  
  public void draw(Graphics pGraphics) 
  {
    if(graphics == null)
    {
      graphics = pGraphics;
    }
    
    pGraphics.drawImage(
        image,
        displayWidth/2 - imageXOffset, 
        displayHeight/2 - imageYOffset, 
        null);
    
    pGraphics.setColor(new Color(255,255, 255, alphaValue));
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
  }
  
  
  public void closeAudio()
  {
    if(audioStream != null)
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
    if(audioStream         != null  &&
        jukeBox.isPlaying() == false   )
     {
       jukeBox.play(audioStream);
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
  
  
  public void setStartTime()
  {
    startTime = System.currentTimeMillis();
  }
  
  
  public boolean hasAudio()
  {
    if(audioStream != null)
    {
      return true;
    }
    
    return false;
  }

}
