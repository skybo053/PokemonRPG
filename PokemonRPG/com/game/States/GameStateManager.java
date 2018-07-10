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
  private GamePanel  oGame              = null;
  private State      oCurrentState      = null;
  
  private Stack<State> oGameStateStack = null;
  
  
  public GameStateManager(GamePanel pGame)
  {
    oGameStateStack = new Stack<State>();
    
    oGame           = pGame;
    
    oIntroState     = new IntroState(oGame);
    oMenuState      = new MenuState(oGame);
    oPlayState      = new PlayState(oGame);
    
    initializeManager();
  }
  
  
  public void initializeManager()
  {
    oGameStateStack.push(oPlayState);
    oGameStateStack.push(oMenuState);
    oGameStateStack.push(oIntroState);
    
    initializeNewState();
    
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
      
      initializeNewState();
    }
  }
  
  
  private void initializeNewState()
  {
    oCurrentState = oGameStateStack.peek();
    oCurrentState.initializeState();
    
    oGame.removeKeyListener();
    oGame.setKeyListener(oCurrentState.getKeyListener());
    
    oCurrentStateType = oCurrentState.getStateType();
  }
    
  
}// end GameStateManager

