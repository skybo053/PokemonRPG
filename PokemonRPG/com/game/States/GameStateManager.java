package com.game.States;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.game.Main.GamePanel;

public class GameStateManager 
{
  private IntroState oIntroState        = null;
  private MenuState  oMenuState         = null;
  private PlayState  oPlayState         = null;
  
  private GameStates oCurrentStateType  = GameStates.UNINITIALIZED;
  private State      oCurrentState      = null;
  
  private Stack<State> oGameStateStack = null;
  
  
  public GameStateManager(GamePanel pGame)
  {
    oGameStateStack = new Stack<State>();
    
    oIntroState     = new IntroState(pGame);
    oMenuState      = new MenuState(pGame);
    oPlayState      = new PlayState(pGame);
    
    initialize();
  }
  
  
  public void initialize()
  {
    if(oCurrentStateType == GameStates.UNINITIALIZED)
    {
      oGameStateStack.push(oPlayState);
      oGameStateStack.push(oMenuState);
      oGameStateStack.push(oIntroState);
      
      oCurrentState = oGameStateStack.peek();
      oCurrentState.initializeState();
      oCurrentStateType = oCurrentState.getStateType();
    }
  }
  
  
  public void update()
  {
    manageStates();
    oCurrentState.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    oCurrentState.draw(pGraphics);
  }
  
  
  public GameStates getCurrentGameStateType()
  {
    return oCurrentStateType;
  }
  
  
  public State getCurrentGameState()
  {
    return oCurrentState;
  }
  
  
  private void manageStates()
  {
    State vCurrState = null;
    
    vCurrState = oGameStateStack.peek();
    
    if(vCurrState.isActive() == false)
    {
      vCurrState = oGameStateStack.pop();
      vCurrState.cleanUpState();
      
      oCurrentState = oGameStateStack.peek();
      oCurrentState.initializeState();
      oCurrentStateType = oCurrentState.getStateType();
    }
  }
    
  
}// end GameStateManager

