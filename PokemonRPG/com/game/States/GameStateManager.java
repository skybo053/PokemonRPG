package com.game.States;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.game.Main.GamePanel;

public class GameStateManager 
{
  private int currStatePos = 0;
  
  private IntroState introState               = null;
  private MenuState  menuState                = null;
  private PlayState  playState                = null;
  
  private GameStates currentGameState         = null;
  private State      state                    = null;
  
  private Map<GameStates, State>   statesCollection    = null;
  private Map<Integer, GameStates> positionCollection  = null;
  private Map<Integer, GameStates> gamestateCollection = null;
  
  private GamePanel game = null;
  
 
  public GameStateManager(GamePanel pGamePanel)
  {
    game                = pGamePanel;
    
    statesCollection    = new HashMap<>();
    positionCollection  = new HashMap<>();
    gamestateCollection = new HashMap<>();
    
    introState      = new IntroState(pGamePanel);
    menuState       = new MenuState(pGamePanel);
    playState       = new PlayState(pGamePanel);
    
    state               = introState;
    currentGameState    = GameStates.UNINITIALIZED;
    
    statesCollection.put(GameStates.UNINITIALIZED, null);
    statesCollection.put(GameStates.INTRO_STATE,   introState);
    statesCollection.put(GameStates.MENU_STATE,    menuState);
    statesCollection.put(GameStates.PLAY_STATE,    playState);
    
    positionCollection.put(0, GameStates.UNINITIALIZED);
    positionCollection.put(1, GameStates.INTRO_STATE);
    positionCollection.put(2, GameStates.MENU_STATE);
    positionCollection.put(3, GameStates.PLAY_STATE);
    
    gamestateCollection.put(0, GameStates.UNINITIALIZED);
    gamestateCollection.put(1, GameStates.INTRO_STATE);
    gamestateCollection.put(2, GameStates.MENU_STATE);
    gamestateCollection.put(3, GameStates.PLAY_STATE);
    
  }
  
  
  public void update()
  {
    manageStates();
    state.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    state.draw(pGraphics);
  }
  
  
  public GameStates getCurrentGameStateType()
  {
    return currentGameState;
  }
  
  
  public State getCurrentGameState()
  {
    return state;
  }
  
  
  public State getState(GameStates pStateType)
  {
    return statesCollection.get(pStateType);
  }
  
  
  private void manageStates()
  {
    if( currentGameState == GameStates.UNINITIALIZED           || 
        statesCollection.get(positionCollection.get(currStatePos)).isActive() == false   )
      {
        statesCollection.remove(positionCollection.get(currStatePos));
        positionCollection.remove(currStatePos);
        gamestateCollection.remove(currStatePos);
        
        currStatePos++;
        
        state.cleanUpState();
        state            = statesCollection.get(positionCollection.get(currStatePos));
        state.initializeState();
        currentGameState = gamestateCollection.get(currStatePos);
        game.setKeyListener(currentGameState);
      }
    
    
    
    
    /*switch(currentGameState)
    {
    case UNINITIALIZED:
      
      state = introState;
      currentGameState = GameStates.INTRO_STATE;
      state.setUpState();
      break;
      
    case INTRO_STATE:
      if(state.isActive() == false)
      {
        state = menuState;
        currentGameState = GameStates.MENU_STATE;
        state.setUpState();
        break;
      }
      
    case MENU_STATE:
      
      if(state.isActive() == false)
      {
        state.cleanUpState();
        state = playState;
        currentGameState = GameStates.PLAY_STATE;
        state.setUpState();
        break;
      }
    }*/
    
  }
  
}// end GameStateManager

