package com.game.States;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import com.game.Exceptions.InitializeStateException;

public interface State 
{
  public void update();
  public void draw(Graphics pGraphics);
  
  public boolean isActive();
  public void setIsActive(boolean pIsActive);
  
  public void initializeState() throws InitializeStateException;
  public void cleanUpState();
  
  public GameStates  getStateType();
  public KeyListener getKeyListener();
}
