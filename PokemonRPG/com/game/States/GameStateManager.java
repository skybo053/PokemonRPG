package com.game.States;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameStateManager 
{
  private IntroState introState   = null;
  private MenuState  menuState    = null;
  
  private GameStates currentState = null;
  
  private State      state        = null;
  
  private ArrayList<State> states = null;
  
 
  public GameStateManager(int pDisplayWidth, int pDisplayHeight)
  {
    currentState    = GameStates.INTRO_STATE;
    states          = new ArrayList<>();
    
    introState      = new IntroState("IntroState", pDisplayWidth, pDisplayHeight);
    menuState       = new MenuState("MenuState", pDisplayWidth, pDisplayHeight);
    
    state           = introState;
    
    states.add(introState);
    states.add(menuState);
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
  
  
  private void checkStates()
  {
    if(currentState == GameStates.INTRO_STATE)
    {
      if(state.isActive() == false)
      {
        states.remove(state);
        state = menuState;
        currentState = GameStates.MENU_STATE;
      }
    }
    
  }
  
}
