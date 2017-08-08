package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.States.GameStateManager;
import com.game.States.GameStates;
import com.game.States.MenuState;

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
    
    case MENU_STATE:
      handleMenuStateKeyEvents(pKeyEvent);
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
  
  
  private void handleMenuStateKeyEvents(KeyEvent pKeyEvent)
  {
    int       vKeyCode     = pKeyEvent.getKeyCode();
    MenuState vMenuState   = gameStateManager.getMenuState();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      vMenuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
      vMenuState.setExitButtonIcon(Assets.imgMenuExitBtn);
      vMenuState.setPlaySelected(true);
      vMenuState.setExitSelected(false);
      break;
    
    case KeyEvent.VK_DOWN:
      vMenuState.setPlayButtonIcon(Assets.imgMenuPlayBtn);
      vMenuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
      vMenuState.setPlaySelected(false);
      vMenuState.setExitSelected(true);
      break;
      
    case KeyEvent.VK_ENTER:
      if(vMenuState.exitSelected())
      {
        vMenuState.setExitButtonIcon(Assets.imgMenuClickedExitBtn);
        System.exit(0);
      }
    }
  }
  
  
}
