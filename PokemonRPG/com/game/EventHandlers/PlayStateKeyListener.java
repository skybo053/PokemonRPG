package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.Entities.Player;
import com.game.FX.Assets;
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
    case KeyEvent.VK_UP:
      playState.addPlayerDirection(Player.PLAYER_MOVE_UP);
      break;
      
    case KeyEvent.VK_DOWN:
      playState.addPlayerDirection(Player.PLAYER_MOVE_DOWN);
      
      break;
      
    case KeyEvent.VK_LEFT:
      playState.addPlayerDirection(Player.PLAYER_MOVE_LEFT);
     
      break;
      
    case KeyEvent.VK_RIGHT:
      playState.addPlayerDirection(Player.PLAYER_MOVE_RIGHT);
      
     
      
      break;
      
    case KeyEvent.VK_ESCAPE:
      System.exit(0);
      break;
    }
  }

  
  public void keyReleased(KeyEvent pKeyEvent) 
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      playState.removePlayerDirection(Player.PLAYER_MOVE_UP);
      break;
      
    case KeyEvent.VK_DOWN:
      playState.removePlayerDirection(Player.PLAYER_MOVE_DOWN);
      break;
      
    case KeyEvent.VK_LEFT:
      playState.removePlayerDirection(Player.PLAYER_MOVE_LEFT);
      break;
      
    case KeyEvent.VK_RIGHT:
      playState.removePlayerDirection(Player.PLAYER_MOVE_RIGHT);
      break;
    }
  }

 
  public void keyTyped(KeyEvent pKeyEvent) {}

}
