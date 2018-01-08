package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.Entities.Player;
import com.game.FX.Assets;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;

public class PlayState implements State 
{
  private boolean    isActive      = false;
  private GamePanel  game          = null;
  private GameStates gameStateType = null;
  private JukeBox    worldTheme    = null;
  
  private Player     player        = null;
  
  
  public PlayState(GamePanel pGamePanel)
  {
    game = pGamePanel;
    
    worldTheme  = new JukeBox(Assets.soundWorldTheme);
    player      = new Player(300,300, 37, 50);
  }
  
  
  public void initializeState()
  {
    isActive = true;
    
    player.setCurrentPlayerDirection(Player.PLAYER_STANDING);
    player.setCurrentPlayerImage(Assets.spriteAshStandForward);
    
    worldTheme.open();
    worldTheme.play();
    worldTheme.setLoopContinuous();
    
    game.setFocusable(true);
    game.requestFocusInWindow();
    
    game.showHUD();
  }
  
  
  public void update()
  {
    player.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.setColor(Color.gray);
    pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
    
    player.draw(pGraphics);
    
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
    isActive = false;
  }

  
  public GameStates getStateType() 
  {
    return gameStateType;
  }
  
  
  public void addPlayerDirection(int pDirection)
  {
    player.addPlayerDirection(pDirection);
  }
  
  
  public void removePlayerDirection(int pDirection)
  {
    player.removePlayerDirection(pDirection);
  }
  
  
  public int getLastPlayerDirection()
  {
    return player.getLastPlayerDirection();
  }
  
  
  public void setCurrentPlayerDirection(int pDirection)
  {
    player.setCurrentPlayerDirection(pDirection);
  }
  
  public void setCurrentPlayerImage(BufferedImage pImage)
  {
    player.setCurrentPlayerImage(pImage);
  }
  
}
