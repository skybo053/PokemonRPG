package com.game.FX;

import java.awt.Color;
import java.awt.Graphics;

public class Effects 
{

  public static void pause(int pWaitTime)
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
  
  
  public static void fade(int pDelta, Graphics pGraphics, Color pColor)
  {
    
  }
}
