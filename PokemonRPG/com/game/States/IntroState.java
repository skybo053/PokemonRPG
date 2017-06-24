package com.game.States;

import java.awt.Color;
import java.awt.Graphics;

import com.game.FX.Assets;

public class IntroState implements State
{
  int displayWidth;
  int displayHeight;
  
  int ashOffsetX;
  int ashOffsetY;
  
  int alphaValue;
  int deltaAlpha;
  
  long startTime;
  
  
  public IntroState(int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    ashOffsetX = Assets.ashThrowWidth  / 2;
    ashOffsetY = Assets.ashThrowHeight / 2;
    
    alphaValue = 255;
    deltaAlpha = -1;
    
    startTime = System.currentTimeMillis();
  }
  
  
  public void update()
  {
    long vNow     = System.currentTimeMillis();
    long vElapsed = vNow - startTime;
    
    if(vElapsed < 2000)
    {
      return;
    }
    else if(vElapsed > 4000)
    {
      
      deltaAlpha = -2;
    }
    
    if(alphaValue + deltaAlpha >= 0)
    {
      alphaValue += deltaAlpha;
    }
    System.out.println("alpha = " + alphaValue);
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.drawImage(
        Assets.imgAshThrow,
        displayWidth/2 - ashOffsetX, 
        displayHeight/2 - ashOffsetY, 
        null);
    
    pGraphics.setColor(new Color(255,255, 255, alphaValue));
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
    
    
  }
}
