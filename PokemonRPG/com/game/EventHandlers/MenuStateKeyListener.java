package com.game.EventHandlers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.FX.Assets;
import com.game.States.GameStates;
import com.game.States.MenuState;

public class MenuStateKeyListener implements KeyListener
{
  private MenuState  oMenuState  = null;
  
  
  public MenuStateKeyListener(MenuState pMenuState)
  {
    oMenuState = pMenuState;
  }
  
  
  public void keyPressed(KeyEvent pKeyEvent)
  {
    int vKeyCode  = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      oMenuState.moveMenuPositionUp();
      break;
    
    case KeyEvent.VK_DOWN:
      oMenuState.moveMenuPositionDown();
      break;
      
    case KeyEvent.VK_ENTER:
      if(oMenuState.playBtnIsSelected())
      {
        oMenuState.setPlayButtonIcon(Assets.imgMenuClickedPlayBtn);
      }
      else if(oMenuState.optionsBtnIsSelected())
      {
        oMenuState.setOptionsButtonIcon(Assets.imgMenuSelectedOptionsBtn);
      }
      else if(oMenuState.exitBtnIsSelected())
      {
        oMenuState.setExitButtonIcon(Assets.imgMenuClickedExitBtn);
      }
    }
  }
  
  
  public void keyReleased(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_ENTER:
      
      if(oMenuState.playBtnIsSelected())
      {
        oMenuState.disableGamePanel();
        oMenuState.setPlayButtonIcon(Assets.imgMenuSelectedPlayBtn);
        oMenuState.playSelectPlayBtnSoundFX();
        oMenuState.addFadeEffect(Color.black, 0, 255, 3, 0L, 5000);
      }
      else if(oMenuState.optionsBtnIsSelected())
      {
        oMenuState.setOptionsButtonIcon(Assets.imgMenuFocusOptionsBtn);
      }
      else if(oMenuState.exitBtnIsSelected())
      {
        oMenuState.setExitButtonIcon(Assets.imgMenuSelectedExitBtn);
        System.exit(0);
      }
    }
  }
  
  
  public void keyTyped(KeyEvent pKeyEvent){}
}
