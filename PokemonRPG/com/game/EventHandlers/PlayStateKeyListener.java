package com.game.EventHandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.Entities.Player;
import com.game.States.PlayState;

public class PlayStateKeyListener implements KeyListener
{
  private PlayState oPlayState  = null;
  
  
  public PlayStateKeyListener(PlayState pPlayState)
  {
    oPlayState = pPlayState;
  }

 
  public void keyPressed(KeyEvent pKeyEvent)
  {
    int vKeyCode = pKeyEvent.getKeyCode();
    
    switch(vKeyCode)
    {
    case KeyEvent.VK_UP:
      oPlayState.addPlayerDirection(Player.PLAYER_MOVE_UP);
      break;
      
    case KeyEvent.VK_DOWN:
      oPlayState.addPlayerDirection(Player.PLAYER_MOVE_DOWN);
      
      break;
      
    case KeyEvent.VK_LEFT:
      oPlayState.addPlayerDirection(Player.PLAYER_MOVE_LEFT);
     
      break;
      
    case KeyEvent.VK_RIGHT:
      oPlayState.addPlayerDirection(Player.PLAYER_MOVE_RIGHT);
      
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
      oPlayState.removePlayerDirection(Player.PLAYER_MOVE_UP);
      break;
      
    case KeyEvent.VK_DOWN:
      oPlayState.removePlayerDirection(Player.PLAYER_MOVE_DOWN);
      break;
      
    case KeyEvent.VK_LEFT:
      oPlayState.removePlayerDirection(Player.PLAYER_MOVE_LEFT);
      break;
      
    case KeyEvent.VK_RIGHT:
      oPlayState.removePlayerDirection(Player.PLAYER_MOVE_RIGHT);
      break;
    }
  }

 
  public void keyTyped(KeyEvent pKeyEvent) {}

}
