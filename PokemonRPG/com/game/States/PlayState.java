package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import com.game.Entities.Player;
import com.game.EventHandlers.PlayStateKeyListener;
import com.game.FX.Assets;
import com.game.FX.JukeBox;
import com.game.Main.GamePanel;

public class PlayState implements State 
{
  private boolean    oIsActive      = false;
  private GamePanel  oGame          = null;
  private GameStates oGameStateType = null;
  private JukeBox    oWorldTheme    = null;
  private Player     oPlayer        = null;
  
  
  private KeyListener oPlayStateKeyListener = null;
  
  
  public PlayState(GamePanel pGamePanel)
  {
    oGame        = pGamePanel;
    oIsActive    = false;
    oWorldTheme  = new JukeBox(Assets.soundWorldTheme);
    oPlayer      = new Player(300,300, 37, 50);
    
    oPlayStateKeyListener = new PlayStateKeyListener(this);
  }
  
  
  public void initializeState()
  {
    oIsActive = true;
    
    oWorldTheme.open();
    oWorldTheme.play();
    oWorldTheme.setLoopContinuous();
    
    oGame.setFocusable(true);
    oGame.requestFocusInWindow();
    
    oGame.showHUD();
    
    oGame.removeKeyListener();
    oGame.setKeyListener(oPlayStateKeyListener);
  }
  
  
  public void update()
  {
    oPlayer.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.setColor(Color.gray);
    pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
    
    oPlayer.draw(pGraphics);
    
  }
  

  public boolean isActive()
  {
    return oIsActive;
  }
  
  
  public void setIsActive(boolean pIsActive)
  {
    oIsActive = pIsActive;
  }
  
  
  public void cleanUpState()
  {
    oIsActive = false;
  }

  
  public GameStates getStateType() 
  {
    return oGameStateType;
  }
  
  
  public void addPlayerDirection(int pDirection)
  {
    oPlayer.addPlayerDirection(pDirection);
  }
  
  
  public void removePlayerDirection(int pDirection)
  {
    oPlayer.removePlayerDirection(pDirection);
  }
}
