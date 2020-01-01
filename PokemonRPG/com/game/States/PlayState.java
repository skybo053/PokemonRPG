package com.game.States;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import com.game.Entities.Camera;
import com.game.Entities.Player;
import com.game.Entities.Tile;
import com.game.Entities.World;
import com.game.EventHandlers.PlayStateKeyListener;
import com.game.Exceptions.InitializeStateException;
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
  public static final int PLAYER_WIDTH  = 35;
  public static final int PLAYER_HEIGHT = 55;
  
  private Camera oCamera = null;
  
  
  public PlayState(GamePanel pGamePanel) 
  {
    oGame      = pGamePanel;
    oIsActive  = false;
    
    oWorld     = new World(PLAYER_WIDTH);
    oPlayer    = new Player(PLAYER_WIDTH, PLAYER_HEIGHT, this);
    
    oGame.hudSetPlayer(oPlayer);
    
    oPlayStateKeyListener = new PlayStateKeyListener(this);
  }
  
  
  public void initializeState() throws InitializeStateException
  {
    oIsActive    = true;
    oCamera      = new Camera(0,0);
    oWorldTheme  = new JukeBox(Assets.soundWorldTheme);
    
    oWorld.setMap(Assets.Map);
    oWorld.setCamera(oCamera);
    
    oWorldTheme.open();
    oWorldTheme.play();
    oWorldTheme.setLoopContinuous();
    
    oPlayer.setHealth(100);
    oPlayer.setSpeed(5);
    oPlayer.setPlayerPositionInWorld(oWorld.getTileAtPosition(1, 8));
    oPlayer.initializeSprites();
    oPlayer.setPlayerDirectionAnimations(Player.PLAYER_MOVE_DOWN);
    oPlayer.setCamera(oCamera);
    
    oGame.setFocusable(true);
    oGame.requestFocusInWindow();
    
    oGame.hudShowHUD();
  }
  
  
  public void update()
  {
    oWorld.update();
    oPlayer.update();
  }
  
  
  public void draw(Graphics pGraphics)
  {
    try
    {
      oWorld.draw(pGraphics);
      oPlayer.draw(pGraphics);
    }
    catch(NullPointerException pNullPointerException)
    {
      return;
    }
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
  
  
  public Tile getTileAtPosition(int pRow, int pCol)
  {
    return oWorld.getTileAtPosition(pRow, pCol);
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
  
  
  public void setPlayerPositionInWorld(Tile pTile)
  {
    oPlayer.setPlayerPositionInWorld(pTile);
  }
  
  
  public Camera getCamera()
  {
    return oCamera;
  }
  
}
