package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.States.GameStates;
import com.game.States.PlayState;

public class PlayStateKeyListener implements KeyListener
{
  
  private KeyHandler keyHandler = null;
  private PlayState  playState  = null;
  
  
  public PlayStateKeyListener(KeyHandler pKeyHandler)
  {
    keyHandler = pKeyHandler;
    playState  = (PlayState)pKeyHandler.getState(GameStates.PLAY_STATE);
  }

 
  public void keyPressed(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_ESCAPE:
      System.exit(0);
      break;
    }
  }

  
  public void keyReleased(KeyEvent pKeyEvent) {}

 
  public void keyTyped(KeyEvent pKeyEvent) {}

}
