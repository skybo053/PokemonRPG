package com.game.States;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.game.FX.Assets;
import com.game.Main.GamePanel;

public class PlayState implements State 
{
  private boolean    isActive      = false;
  private GamePanel  game          = null;
  private GameStates gameStateType = null;
  
  
  public PlayState(GamePanel pGamePanel)
  {
    game = pGamePanel;
  }
  
  
  public void setUpState()
  {
    isActive = true;
    
    game.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    game.showHUD();
    game.validate();
  }
  
  
  public void update()
  {
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.setColor(Color.gray);
    pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
    pGraphics.drawString("THIS IS PLAY STATE", 100, 100);
  }
  
  
  public boolean isActive()
  {
    return isActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    isActive = pIsActive;
  }
  
  
  public void cleanUpState()
  {
    
  }

  
  public GameStates getStateType() 
  {
    return gameStateType;
  }

  
}
