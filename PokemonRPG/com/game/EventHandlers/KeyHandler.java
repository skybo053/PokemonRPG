package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.States.GameStateManager;
import com.game.States.GameStates;

public class KeyHandler implements KeyListener
{
  private GameStateManager gameStateManager = null;
  private GameStates       currentState     = null;

  
  public KeyHandler(GameStateManager pGameStateManager)
  {
    gameStateManager = pGameStateManager;
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent) 
  {
    currentState = gameStateManager.getCurrentState();
    
    switch(currentState)
    {
    case INTRO_STATE:
      handleIntroStateKeyEvents(pKeyEvent);
      break;
    }
  }

  
  public void keyReleased(KeyEvent e) 
  {
    
  }
  
  
  public void keyTyped(KeyEvent e) 
  {
    
  }
  
  
  private void handleIntroStateKeyEvents(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_ESCAPE:
      gameStateManager.getIntroState().skipSplashScreen();
      break;
    }
  }
}
