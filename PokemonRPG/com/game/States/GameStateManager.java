package com.game.States;

import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStateManager 
{
  private IntroState introState   = null;
  private MenuState  menuState    = null;
  
  private GameStates currentState = null;
  
  private State      state        = null;
  
  private Map<String, State> statesMap = null;
  
 
  public GameStateManager(int pDisplayWidth, int pDisplayHeight)
  {
    currentState    = GameStates.INTRO_STATE;
    statesMap       = new LinkedHashMap<>();
    
    introState      = new IntroState("IntroState", pDisplayWidth, pDisplayHeight);
    menuState       = new MenuState("MenuState", pDisplayWidth, pDisplayHeight);
    
    state           = introState;
    
    statesMap.put("IntroState", introState);
    statesMap.put("MenuState", menuState);
  }
  
  
  public void update()
  {
    state.update();
    
    checkStates();
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
  
  
  private void checkStates()
  {
    if(statesMap.containsKey("IntroState") &&
       currentState == GameStates.INTRO_STATE)
    {
      if(state.isActive() == false)
      {
        statesMap.remove(state);
        state = menuState;
        state.setIsActive(true);
        currentState = GameStates.MENU_STATE;
      }
    }
    
  }
  
}
