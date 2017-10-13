package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.Main.GamePanel;
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
      handleMenuStatePressedKeyEvents(pKeyEvent);
      break;
    }
  }

  
  public void keyReleased(KeyEvent pKeyEvent) 
  {
    switch(currentGameState)
    {
    case MENU_STATE:
      handleMenuStateReleasedKeyEvents(pKeyEvent);
      break;
    }
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
  
  
  private void handleMenuStatePressedKeyEvents(KeyEvent pKeyEvent)
  {
    int vKeyCode  = pKeyEvent.getKeyCode();
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      menuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
      menuState.setExitButtonIcon(Assets.imgMenuExitBtn);
      menuState.setPlayBtnSelected(true);
      menuState.setExitBtnSelected(false);
      menuState.playBTNSoundFX();
      menuState.rewindBTNSoundFX();
      break;
    
    case KeyEvent.VK_DOWN:
      menuState.setPlayButtonIcon(Assets.imgMenuPlayBtn);
      menuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
      menuState.setPlayBtnSelected(false);
      menuState.setExitBtnSelected(true);
      menuState.playBTNSoundFX();
      menuState.rewindBTNSoundFX();
      break;
      
    case KeyEvent.VK_ENTER:
      if(menuState.exitBtnIsSelected())
      {
        menuState.setExitButtonIcon(Assets.imgMenuClickedExitBtn);
      }
      else if(menuState.playBtnIsSelected())
      {
        menuState.setPlayButtonIcon(Assets.imgMenuClickedPlayBtn);
      }
    }
  }
  
  //released key events
  private void handleMenuStateReleasedKeyEvents(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_ENTER:
      if(menuState.exitBtnIsSelected())
      {
        menuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
        System.exit(0);
      }
      else if(menuState.playBtnIsSelected())
      {
        menuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
        menuState.playSelectPlayBtnSoundFX();
        menuState.addFadeEffect();
        //menuState.removeMenuButtons();
      }
      
    }
  }
  
  
}
