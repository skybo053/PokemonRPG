package com.game.EventHandlers;

import java.awt.event.KeyListener;
import java.util.HashMap;

import com.game.Main.GamePanel;
import com.game.States.GameStates;
import com.game.States.State;

public class KeyHandler
{
  private GamePanel game = null;

  private IntroStateKeyListener introStateKeyListener    = null;
  private MenuStateKeyListener  menuStateKeyListener     = null;
  private PlayStateKeyListener  playStateKeyListener     = null;
  
  private HashMap<GameStates, KeyListener> keyListeners = null;
  
  public KeyHandler(GamePanel pGame)
  {
    game = pGame;
    
    keyListeners = new HashMap<GameStates, KeyListener>();
    
    introStateKeyListener = new IntroStateKeyListener(this);
    menuStateKeyListener  = new MenuStateKeyListener(this);
    playStateKeyListener  = new PlayStateKeyListener(this);
    
    keyListeners.put(GameStates.INTRO_STATE, introStateKeyListener);
    keyListeners.put(GameStates.MENU_STATE, menuStateKeyListener);
    keyListeners.put(GameStates.PLAY_STATE, playStateKeyListener);
  }
  
  
  public State getState(String pStateName)
  {
    return game.getState(pStateName);
  }
  
  
  public void setKeyListener(GameStates pGameState)
  {
    KeyListener vListener = null;
    
    switch(pGameState)
    {
    case INTRO_STATE:
      vListener = keyListeners.get(pGameState);
      game.addKeyListener(vListener);
      break;
      
    case MENU_STATE:
      vListener = keyListeners.get(pGameState);
      game.addKeyListener(vListener);
      break;
      
    case PLAY_STATE:
      vListener = keyListeners.get(pGameState);
      game.addKeyListener(vListener);
      break;
    }
  }
}// end KeyHandler class
