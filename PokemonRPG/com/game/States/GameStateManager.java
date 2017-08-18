package com.game.States;

import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

import com.game.Main.GamePanel;

public class GameStateManager 
{
  private IntroState introState   = null;
  private MenuState  menuState    = null;
  private PlayState  playState    = null;
  
  private GameStates currentState = null;
  
  private State      state        = null;
  
  private Map<String, State> statesMap = null;
  
 
  public GameStateManager(GamePanel pGamePanel)
  {
    currentState    = GameStates.UNINITIALIZED;
    statesMap       = new LinkedHashMap<>();
    
    introState      = new IntroState(pGamePanel);
    menuState       = new MenuState(pGamePanel);
    playState       = new PlayState(pGamePanel);
    
    statesMap.put("MenuState", menuState);
    statesMap.put("PlayState", playState);
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
  
  
  public GameStates getCurrentState()
  {
    return currentState;
  }
  
  
  public IntroState getIntroState()
  {
    return introState;
  }
  
  
  public MenuState getMenuState()
  {
    return menuState;
  }
  
  
  private void manageStates()
  {
    switch(currentState)
    {
    case UNINITIALIZED:
      
      state = introState;
      currentState = GameStates.INTRO_STATE;
      state.setUpState();
      break;
      
    case INTRO_STATE:
      if(state.isActive() == false)
      {
        state = menuState;
        currentState = GameStates.MENU_STATE;
        state.setUpState();
        break;
      }
      
    case MENU_STATE:
      
      if(state.isActive() == false)
      {
        state.cleanUpState();
        state = playState;
        currentState = GameStates.PLAY_STATE;
        state.setUpState();
        break;
      }
    }
  }
  
}// end GameStateManager

