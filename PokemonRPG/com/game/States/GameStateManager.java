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
    currentState    = GameStates.INTRO_STATE;
    statesMap       = new LinkedHashMap<>();
    
    introState      = new IntroState(pGamePanel);
    menuState       = new MenuState(pGamePanel);
    playState       = new PlayState(pGamePanel);
    
    statesMap.put("IntroState", introState);
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
    if(state == null)
    {
      state = statesMap.get("IntroState");
      state.setUpState();
    }
    
    if(statesMap.containsKey("IntroState") &&
       currentState == GameStates.INTRO_STATE)
    {
      if(state.isActive() == false)
      {
        statesMap.remove(state);
        state = menuState;
        state.setIsActive(true);
        currentState = GameStates.MENU_STATE;
        state.setUpState();
      }
    }
    else if(currentState == GameStates.MENU_STATE)
    {
      if(state.isActive() == false)
      {
        state.cleanUpState();
        state = playState;
        state.setIsActive(true);
        currentState = GameStates.PLAY_STATE;
        state.setUpState();
      }
    }
    
  }
  
}
