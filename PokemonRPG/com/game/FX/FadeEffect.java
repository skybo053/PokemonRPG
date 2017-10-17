package com.game.FX;

import java.awt.Color;
import java.awt.Graphics;

import com.game.Main.GamePanel;
import com.game.States.State;

public class FadeEffect 
{
  Color oColor           = null;
  int   oStartAlpha      = 0;
  int   oEndAlpha        = 0;
  int   oDeltaAlpha      = 0;
  long  oDuration        = 0L;
  long  oEffectStartTime = 0L;
  State oCurrentState    = null;
  
  int oRed;
  int oGreen;
  int oBlue;
  
  boolean isDone = false;
  
  
  public FadeEffect(
      Color pColor, 
      int   pStartAlpha, 
      int   pEndAlpha,
      int   pDeltaAlpha,
      long  pDuration)
  {
    oColor        = pColor;
    oStartAlpha   = pStartAlpha;
    oEndAlpha     = pEndAlpha;
    oDeltaAlpha   = pDeltaAlpha;
    oDuration     = pDuration;
    
    oRed   = oColor.getRed();
    oGreen = oColor.getGreen();
    oBlue  = oColor.getBlue();
    
    isDone           = false;
    oEffectStartTime = 0L;
  }
  
  
  public void update()
  {
    if(oEffectStartTime <= 0L)
    {
      oEffectStartTime = System.currentTimeMillis();
    }
    
    if(oDeltaAlpha > 0 && oStartAlpha + oDeltaAlpha > 255)
    {
      oStartAlpha = 255;
    }
    else if(oDeltaAlpha < 0 && oStartAlpha + oDeltaAlpha < 0)
    {
      oStartAlpha = 0;
    }
    else
    {
      oStartAlpha += oDeltaAlpha;
    }
    
    if(System.currentTimeMillis() - oEffectStartTime >= oDuration)
    {
      isDone = true;
      oCurrentState.setEffectDone();
    }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.setColor(new Color(oRed, oGreen, oBlue, oStartAlpha));
    pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
  }
  
  
  public boolean isDone()
  {
    return isDone;
  }
  
  public void setState(State pState)
  {
    oCurrentState = pState;
  }

}
