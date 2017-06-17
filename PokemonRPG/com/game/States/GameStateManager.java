package com.game.States;

import java.awt.Graphics;

public class GameStateManager 
{
  
  //GameStates
  private GameStates gameStates = null;
  private IntroState introState = null;
  
  //display width and height
  private int displayWidth;
  private int displayHeight;
  
  
  public GameStateManager(int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    gameStates    = GameStates.INTRO_STATE;
    introState    = new IntroState(this, pDisplayWidth, pDisplayHeight);
  }
  
  
  public void update()
  {
    if(gameStates == GameStates.INTRO_STATE)
    {
      introState.update();
    }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    if(gameStates == GameStates.INTRO_STATE)
    {
      introState.draw(pGraphics);
    }
  }
  
  
  public void setGameState(GameStates pGameStates)
  {
    gameStates = pGameStates;
  }
  
  
  public GameStates getGameState()
  {
    return gameStates;
  }
}
