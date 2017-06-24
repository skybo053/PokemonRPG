package com.game.States;

import java.awt.Graphics;

public class GameStateManager 
{
  
  //GameStates
  private IntroState introState   = null;
  private GameStates currentState = null;
  
  //Interface variable to refer to currentState
  private State state = null;
  
  
  public GameStateManager(int pDisplayWidth, int pDisplayHeight)
  {
    currentState = GameStates.INTRO_STATE;
    introState   = new IntroState(pDisplayWidth, pDisplayHeight);
    
    state        = introState;
  }
  
  
  public void update()
  {
    state.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    state.draw(pGraphics);
  }
}
