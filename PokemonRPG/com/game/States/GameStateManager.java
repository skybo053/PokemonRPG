package com.game.States;

import java.awt.Graphics;
import java.util.Stack;

import com.game.Exceptions.AssetLoaderException;
import com.game.Exceptions.InitializeStateException;
import com.game.FX.Assets;
import com.game.Main.GamePanel;

public class GameStateManager 
{
  private GameStates   oCurrentStateType  = GameStates.UNINITIALIZED;
  private GamePanel    oGame              = null;
  private State        oCurrentState      = null;
  private Stack<State> oGameStateStack    = null;
  
  
  public GameStateManager(GamePanel pGame) throws InitializeStateException
  {
    oGameStateStack = new Stack<State>();
    oGame           = pGame;
    
    oGameStateStack.push(new PlayState(oGame));
    oGameStateStack.push(new MenuState(oGame));
    oGameStateStack.push(new IntroState(oGame));
    
    oCurrentState = oGameStateStack.peek();
  }
  
  
  public void update() throws InitializeStateException, AssetLoaderException
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
  
  
  private void manageStates() throws InitializeStateException, AssetLoaderException
  {
    if(oCurrentState.isActive() == false)
    {
      if(Assets.IsLoaded == false)
      {
        if(oGame.assetsLoading() == false)
        {
          throw new AssetLoaderException("GameStateManager.manageStates - " +
                                         "Error loading assets");
        }
      }
      
      oCurrentState.cleanUpState();
      
      initializeNewState();
    }
  }
  
  
  private void initializeNewState() throws InitializeStateException
  {
    oCurrentState = oGameStateStack.pop();
    oCurrentState.initializeState();
    
    oGame.removeKeyListener();
    oGame.setKeyListener(oCurrentState.getKeyListener());
    
    oCurrentStateType = oCurrentState.getStateType();
  }
    
  
}// end GameStateManager

