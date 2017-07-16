package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;

public class SplashScreen extends BaseSplashScreen
{

  public SplashScreen(
      String           pName, 
      int              pDisplayWidth, 
      int              pDisplayHeight,
      int              pWaitTime,
      boolean          pFadeOut,
      BufferedImage    pImage,
      AudioInputStream pAudioStream) 
  {
    super(pName, 
          pDisplayWidth, 
          pDisplayHeight,
          pWaitTime,
          pFadeOut,
          pImage, 
          pAudioStream);
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
      if(audioStream         != null  &&
         jukeBox.isPlaying() == false   )
      {
        jukeBox.play(audioStream);
      }
      
      deltaAlpha = -2;
    }
    else if(vElapsed < 6000)
    {
      deltaAlpha = -3;
    }
    
    if(alphaValue + deltaAlpha >= 0)
    {
      alphaValue += deltaAlpha;
    }
    else
    {
      alphaValue = 0;
      wait(waitTime);
      isDone = true;
    }
  }


  public void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(
        image,
        displayWidth/2 - imageXOffset, 
        displayHeight/2 - imageYOffset, 
        null);
    
    pGraphics.setColor(new Color(255,255, 255, alphaValue));
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
  }

}
