package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.FX.JukeBox;
import com.game.States.GameStateManager;
import com.game.States.GameStates;
import com.game.States.IntroState;
import com.game.States.MenuState;

public class KeyHandler implements KeyListener
{
  private GameStateManager gameStateManager    = null;
  private IntroState       introState          = null;
  private MenuState        menuState           = null;
  private GameStates       currentGameState    = null;
  
  private JukeBox          menuStateBtnJukeBox = null;

  
  public KeyHandler(GameStateManager pGameStateManager)
  {
    gameStateManager    = pGameStateManager;
    introState          = gameStateManager.getIntroState();
    menuState           = gameStateManager.getMenuState();
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent) 
  {
    currentGameState = gameStateManager.getCurrentGameState();
    
    switch(currentGameState)
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
      introState.skipSplashScreen();
      break;
    }
  }
  
  
  private void handleMenuStateKeyEvents(KeyEvent pKeyEvent)
  {
    int vKeyCode  = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      menuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
      menuState.setExitButtonIcon(Assets.imgMenuExitBtn);
      menuState.setPlaySelected(true);
      menuState.setExitSelected(false);
      menuState.playBTNSoundFX();
      menuState.rewindBTNSoundFX();
      break;
    
    case KeyEvent.VK_DOWN:
      menuState.setPlayButtonIcon(Assets.imgMenuPlayBtn);
      menuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
      menuState.setPlaySelected(false);
      menuState.setExitSelected(true);
      menuState.playBTNSoundFX();
      menuState.rewindBTNSoundFX();
      break;
      
    case KeyEvent.VK_ENTER:
      if(menuState.exitSelected())
      {
        menuState.setExitButtonIcon(Assets.imgMenuClickedExitBtn);
        System.exit(0);
      }
      else if(menuState.playSelected())
      {
        menuState.setPlayButtonIcon(Assets.imgMenuClickedPlayBtn);
        menuState.setIsActive(false);
      }
    }
  }
  
  
}
