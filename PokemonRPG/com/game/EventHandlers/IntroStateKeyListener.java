package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.States.IntroState;


public class IntroStateKeyListener implements KeyListener
{
  
  private IntroState oIntroState = null;

  
  public IntroStateKeyListener(IntroState pIntroState)
  {
    oIntroState = pIntroState;
  }
  
  
  public void keyPressed(KeyEvent pEvent)
  {
    
    switch(pEvent.getKeyCode())
    {
    case KeyEvent.VK_ESCAPE:
      if(Assets.IsLoaded == true)
      {
        oIntroState.skipSplashScreen();
      }
      break;
    }
  }
  
  
  public void keyReleased(KeyEvent pEvent){}

  
  public void keyTyped(KeyEvent pEvent){}
  
}