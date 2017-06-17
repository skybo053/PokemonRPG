package com.game.States;

import java.awt.Graphics;

import com.game.FX.Assets;

public class IntroState 
{
  private GameStateManager gameStateManager = null;
  
  private int displayWidth;
  private int displayHeight;
  
  public IntroState(GameStateManager pGameStateManager, int pDisplayWidth, int pDisplayHeight)
  {
    gameStateManager = pGameStateManager;
    displayWidth     = pDisplayWidth;
    displayHeight    = pDisplayHeight;
  }
  
  public void update()
  {
    
  }
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.drawImage(Assets.imgAshThrow, 0, 0, null);
  }
}
