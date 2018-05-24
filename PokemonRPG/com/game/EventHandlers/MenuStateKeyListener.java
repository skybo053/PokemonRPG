package com.game.EventHandlers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.States.GameStates;
import com.game.States.MenuState;

public class MenuStateKeyListener implements KeyListener
{

  private KeyHandler keyHandler = null;
  private MenuState  menuState  = null;
  
  
  public MenuStateKeyListener(KeyHandler pKeyHandler)
  {
    keyHandler = pKeyHandler;
    menuState  = (MenuState)keyHandler.getState(GameStates.MENU_STATE);
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent)
  {
    int vKeyCode  = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      menuState.moveMenuPositionUp();
      break;
    
    case KeyEvent.VK_DOWN:
      menuState.moveMenuPositionDown();
      break;
      
    case KeyEvent.VK_ENTER:
      if(menuState.playBtnIsSelected())
      {
        menuState.setPlayButtonIcon(Assets.imgMenuClickedPlayBtn);
      }
      else if(menuState.optionsBtnIsSelected())
      {
        menuState.setOptionsButtonIcon(Assets.imgMenuSelectedOptionsBtn);
      }
      else if(menuState.exitBtnIsSelected())
      {
        menuState.setExitButtonIcon(Assets.imgMenuClickedExitBtn);
      }
    }
  }
  
  
  public void keyReleased(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_ENTER:
      
      if(menuState.playBtnIsSelected())
      {
        menuState.disableGamePanel();
        menuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
        menuState.playSelectPlayBtnSoundFX();
        menuState.addFadeEffect(Color.black, 0, 255, 3, 0L, 5000); //Temporarily set
                                                                   //duration to 1000 from 5000
                                                                  // for testing
      }
      else if(menuState.optionsBtnIsSelected())
      {
        menuState.setOptionsButtonIcon(Assets.imgMenuFocusOptionsBtn);
      }
      else if(menuState.exitBtnIsSelected())
      {
        menuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
        System.exit(0);
      }
    
    }
  
  }
  
  
  public void keyTyped(KeyEvent pKeyEvent){}
}
