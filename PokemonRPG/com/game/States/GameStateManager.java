package com.game.States;

import java.awt.Graphics;
import java.util.Stack;

import com.game.Exceptions.WorldLoaderException;
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
  
  
  public GameStateManager(GamePanel pGame) throws WorldLoaderException
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
    if(oCurrentState.isActive() == false)
    {
      oCurrentState.cleanUpState();
      
      initializeNewState();
    }
  }
  
  
  private void initializeNewState()
  {
    oCurrentState = oGameStateStack.pop();
    oCurrentState.initializeState();
    
    oGame.removeKeyListener();
    oGame.setKeyListener(oCurrentState.getKeyListener());
    
    oCurrentStateType = oCurrentState.getStateType();
  }
    
  
}// end GameStateManager

