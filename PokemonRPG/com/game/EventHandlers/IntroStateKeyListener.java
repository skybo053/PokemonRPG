package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.States.GameStates;
import com.game.States.IntroState;


public class IntroStateKeyListener implements KeyListener
{
  
  private KeyHandler keyHandler = null;
  private IntroState introState = null;

  
  public IntroStateKeyListener(KeyHandler pKeyHandler)
  {
    keyHandler = pKeyHandler;
    introState = (IntroState)keyHandler.getState(GameStates.INTRO_STATE);
  }
  
  
  public void keyPressed(KeyEvent pEvent)
  {
    
    switch(pEvent.getKeyCode())
    {
    case KeyEvent.VK_ESCAPE:
      introState.skipSplashScreen();
      break;
    }
  }
  
  
  public void keyReleased(KeyEvent pEvent){}

  
  public void keyTyped(KeyEvent pEvent){}
  
}