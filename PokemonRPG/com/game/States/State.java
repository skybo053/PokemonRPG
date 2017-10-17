package com.game.States;

import java.awt.Graphics;

public interface State 
{
  public void update();
  public void draw(Graphics pGraphics);
  
  public boolean isActive();
  public void setIsActive(boolean pIsActive);
  
  public void setUpState();
  public void cleanUpState();
  
  public GameStates getStateType();
 
  public void checkEffectDone();
}
