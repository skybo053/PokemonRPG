package com.game.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import com.game.Entities.Player;
import com.game.Entities.World;
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
  private World      oWorld         = null;
  
  private KeyListener oPlayStateKeyListener = null;
  
  
  //hardcoded for now, will be based on screen size
  private static int PLAYER_WIDTH  = 37;
  private static int PLAYER_HEIGHT = 57;
  
  public PlayState(GamePanel pGamePanel)
  {
    oGame        = pGamePanel;
    oIsActive    = false;
    oWorldTheme  = new JukeBox(Assets.soundWorldTheme);
    
    
    oWorld       = new World(PLAYER_WIDTH,  PLAYER_HEIGHT);
    oPlayer      = new Player(PLAYER_WIDTH, PLAYER_HEIGHT, oWorld);
    
    
    oGame.hudSetPlayer(oPlayer);
    
    oPlayStateKeyListener = new PlayStateKeyListener(this);
  }
  
  
  public void initializeState()
  {
    oIsActive = true;
    
    oWorldTheme.open();
    oWorldTheme.play();
    oWorldTheme.setLoopContinuous();
    
    oPlayer.setHealth(100);
    oPlayer.setSpeed(5);
    oPlayer.setTilePositionX(2, 20);
    oPlayer.setTilePositionY(2, 20);
    
    oGame.setFocusable(true);
    oGame.requestFocusInWindow();
    
    oGame.hudSetHealthBarZero();
    oGame.hudShowHUD();
  }
  
  
  public void update()
  {
    oPlayer.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    //pGraphics.setColor(Color.gray);
    //pGraphics.fillRect(0, 0, GamePanel.displayWidth, GamePanel.displayHeight);
    
    oWorld.draw(pGraphics);
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
  
  
  public KeyListener getKeyListener()
  {
    return oPlayStateKeyListener;
  }
}
