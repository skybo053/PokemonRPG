package com.game.EventHandlers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.States.MenuState;

public class MenuStateKeyListener implements KeyListener
{

  private KeyHandler keyHandler = null;
  private MenuState  menuState  = null;
  
  
  public MenuStateKeyListener(KeyHandler pKeyHandler)
  {
    keyHandler = pKeyHandler;
    menuState  = (MenuState)keyHandler.getState("MenuState");
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent)
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
  
  
  public void keyReleased(KeyEvent pKeyEvent)
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
        menuState.disableGamePanel();
        menuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
        menuState.playSelectPlayBtnSoundFX();
        menuState.addFadeEffect(Color.black, 0, 255, 3, 0L, 1000); //Temporarily set
                                                                   //duration to 1000 from 5000
                                                                  // for testing
        
      }
    }
  }
  
  
  public void keyTyped(KeyEvent pKeyEvent){}
}
