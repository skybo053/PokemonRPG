package com.game.Entities;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.Timer;

import com.game.FX.Assets;
import com.game.States.PlayState;

public class Player extends Creature implements ActionListener
{
  private final int ANIMATION_ARRAY_SIZE  = 7;
  private final int ANIMATION_START_INDEX = 1;
  private final int ANIMATION_SWITCH_TIME = 100;
  
  public static final int PLAYER_STANDING   = -1;
  public static final int PLAYER_MOVE_UP    = 0;
  public static final int PLAYER_MOVE_DOWN  = 1;
  public static final int PLAYER_MOVE_LEFT  = 2;
  public static final int PLAYER_MOVE_RIGHT = 3;
  
  private Set<Integer>      playerDirectionQueue         = null;
  private Iterator<Integer> playerDirectionQueueIterator = null;
  
  private BufferedImage[] currAnimations      = null;
  private BufferedImage[] ashForwardSprites   = null;
  private BufferedImage[] ashBackwardsSprites = null;
  private BufferedImage[] ashLeftSprites      = null;
  private BufferedImage[] ashRightSprites     = null;
  
  private int   currPlayerDirection = PLAYER_STANDING;
  private int   animationIndex      = ANIMATION_START_INDEX;
  private Timer timer               = null;
  
  private Integer oDestXPos = null;
  private Integer oDestYPos = null;
  
  private boolean oProcessingMove = false;
  
  private Tile      oTile      = null;
  private PlayState oPlayState = null;
  
  private Camera    oCamera    = null;

  
  public Player(int pWidth, int pHeight, PlayState pPlayState)
  {
    super(pWidth, pHeight);
    
    oPlayState           = pPlayState;
    playerDirectionQueue = new LinkedHashSet<Integer>();
    timer                = new Timer(ANIMATION_SWITCH_TIME, this);
  }
  
  
  public void update()
  {
    if(oProcessingMove == false)
    {
      currPlayerDirection = getPlayerDirection();
      
      if(currPlayerDirection       != PLAYER_STANDING &&
          isDestinationTileSolid() == true)
       {
         return;
       }
    }
    
    switch(currPlayerDirection)
    {
    case PLAYER_MOVE_UP:
      processUpMove();
      break;
      
    case PLAYER_MOVE_DOWN:
     processDownMove();
     break;
     
   case PLAYER_MOVE_LEFT:
     processLeftMove();
     break;
     
   case PLAYER_MOVE_RIGHT:
     processRightMove();
     break;
   }
    
    if(oProcessingMove   == false &&
       oTile.hasEvents() == true)
    {
      oTile.getEvents().forEach(vTileEvent->{
        
        vTileEvent.setPlayState(oPlayState);
        vTileEvent.processEvent();
        
      });
    }
    
    oCamera.centerOnPlayer(this);
  }
  
  
  public void draw(Graphics pGraphics)
  {
    if(currPlayerDirection == PLAYER_STANDING)
    {
      timer.stop();
      animationIndex = ANIMATION_START_INDEX;
      
      pGraphics.drawImage(
          currAnimations[0], 
          oXPos + oCamera.getXOffset(), 
          oYPos + oCamera.getYOffset(), 
          oWidth, 
          oHeight, 
          null);
    }
    else
    {
      if(timer.isRunning() == false)
      {
        timer.start();
      }
      
      pGraphics.drawImage(
          currAnimations[animationIndex], 
          oXPos + oCamera.getXOffset(), 
          oYPos + oCamera.getYOffset(), 
          oWidth, 
          oHeight, 
          null);
    }
  }
  
  
  public void actionPerformed(ActionEvent pEvent)
  {
    if(animationIndex + 1 < ANIMATION_ARRAY_SIZE)
    {
      ++animationIndex;
    }
    else
    {
      animationIndex = ANIMATION_START_INDEX;
    }
  }
  
  
  public void addPlayerDirection(int pDirection)
  {
    playerDirectionQueue.add(pDirection);
  }
  
  
  public void removePlayerDirection(int pDirection)
  {
    playerDirectionQueue.remove(pDirection);
  }
  
  
  public int getPlayerDirection()
  {
    int vDirection = 0;
    
    if(playerDirectionQueue.isEmpty())
    {
      return PLAYER_STANDING;
    }
    else
    {
      playerDirectionQueueIterator = playerDirectionQueue.iterator();
      
      while(playerDirectionQueueIterator.hasNext())
      {
        vDirection = playerDirectionQueueIterator.next();
      }
      
      setPlayerDirectionAnimations(vDirection);
      
      return vDirection;
    }
  }
  
  
  private boolean isDestinationTileSolid()
  {
    Tile    vDestTile = null;
    Boolean vIsSolid  = null;
    
    switch(currPlayerDirection)
    {
    case Player.PLAYER_MOVE_UP:
      
      vDestTile = oPlayState.getTileAtPosition(
          oTile.getRow() - 1, 
          oTile.getCol());
      
      vIsSolid = vDestTile.isSolid();
    break;
    
    case Player.PLAYER_MOVE_DOWN:
      
      vDestTile = oPlayState.getTileAtPosition(
          oTile.getRow() + 1, 
          oTile.getCol());
      
      vIsSolid = vDestTile.isSolid();
      break;
      
    case Player.PLAYER_MOVE_LEFT:
      
      vDestTile = oPlayState.getTileAtPosition(
          oTile.getRow(), 
          oTile.getCol() - 1);
      
      vIsSolid = vDestTile.isSolid();
      break;
      
    case Player.PLAYER_MOVE_RIGHT:
      
      vDestTile = oPlayState.getTileAtPosition(
          oTile.getRow(), 
          oTile.getCol() + 1);
      
      vIsSolid = vDestTile.isSolid();
      break;
    }
    
    if(vIsSolid == false)
    {
      setPlayerTile(vDestTile);
    }
 
    return vIsSolid;
  }
  
  
  public void setPlayerPositionInWorld(Tile pTile)
  {
    int  vPlayerOffset = 0;
    
    vPlayerOffset  = pTile.getHeight() / 2;
    
    oXPos = pTile.getXPos();
    oYPos = pTile.getYPos() - vPlayerOffset;
    
    setPlayerTile(pTile);
  }
  
  
  private void setPlayerTile(Tile pTile)
  {
    oTile = pTile;
    System.out.println("Player moved to tile at " + oTile.getRow() + " " + oTile.getCol());
  }
  
  
  public void setPlayerDirectionAnimations(int pPlayerDirection)
  {
    switch(pPlayerDirection)
    {
    case PLAYER_MOVE_UP:
      currAnimations = ashBackwardsSprites;
      break;
    case PLAYER_MOVE_DOWN:
      currAnimations = ashForwardSprites;
      break;
    case PLAYER_MOVE_LEFT:
      currAnimations = ashLeftSprites;
      break;
    case PLAYER_MOVE_RIGHT:
      currAnimations = ashRightSprites;
      break;
    }
  }
  
  
  private void processLeftMove()
  {
    if(oDestXPos == null)
    {
      oDestXPos       = oXPos - oWidth;
      oProcessingMove = true;
    }
    
    if(oXPos - speed < oDestXPos)
    {
      oXPos = oDestXPos;
    }
    else
    {
      oXPos -= speed;
    }
    
    if(oXPos == oDestXPos)
    {
      oDestXPos       = null;
      oProcessingMove = false;
    }
  }
  
  
  private void processRightMove()
  {
    if(oDestXPos == null)
    {
      oDestXPos       = oXPos + oWidth;
      oProcessingMove = true;
    }
    
    if(oXPos + speed > oDestXPos)
    {
      oXPos = oDestXPos;
    }
    else
    {
      oXPos += speed;
    }
    
    if(oXPos == oDestXPos)
    {
      oDestXPos       = null;
      oProcessingMove = false;
    }
  }
  
  
  private void processDownMove()
  {
    if(oDestYPos == null)
    {
      oDestYPos       = oYPos + oWidth;  //oWidth = player width = dimensions of tile
      oProcessingMove = true;
    }
    
    if(oYPos + speed > oDestYPos)
    {
      oYPos = oDestYPos;
    }
    else
    {
      oYPos += speed;
    }
    
    if(oYPos == oDestYPos)
    {
      oDestYPos       = null;
      oProcessingMove = false;
    }
  }
  
  
  private void processUpMove()
  {
    if(oDestYPos == null)
    {
      oDestYPos       = oYPos - oWidth;  //oWidth = player width = dimensions of tile
      oProcessingMove = true;
    }
    
    if(oYPos - speed < oDestYPos)
    {
      oYPos = oDestYPos;
    }
    else
    {
      oYPos -= speed;
    }
    
    if(oYPos == oDestYPos)
    {
      oDestYPos       = null;
      oProcessingMove = false;
    }
  }
  
  
  public void initializeSprites()
  {
    ashForwardSprites = new BufferedImage[]
        {
        Assets.spriteAshStandForward,
        Assets.spriteAshRunForward1,
        Assets.spriteAshRunForward2,
        Assets.spriteAshRunForward3,
        Assets.spriteAshRunForward4,
        Assets.spriteAshRunForward3,
        Assets.spriteAshRunForward2
        };
    
    ashBackwardsSprites = new BufferedImage[]
        {
        Assets.spriteAshStandBackwards,
        Assets.spriteAshRunBackwards1,
        Assets.spriteAshRunBackwards2,
        Assets.spriteAshRunBackwards3,
        Assets.spriteAshRunBackwards4,
        Assets.spriteAshRunBackwards3,
        Assets.spriteAshRunBackwards2
        };
    
    ashLeftSprites = new BufferedImage[]
        {
        Assets.spriteAshStandLeft,
        Assets.spriteAshRunLeft1,
        Assets.spriteAshRunLeft2,
        Assets.spriteAshRunLeft3,
        Assets.spriteAshRunLeft4,
        Assets.spriteAshRunLeft3,
        Assets.spriteAshRunLeft2
        };
    
    ashRightSprites = new BufferedImage[]
        {
        Assets.spriteAshStandRight,    
        Assets.spriteAshRunRight1,
        Assets.spriteAshRunRight2,
        Assets.spriteAshRunRight3,
        Assets.spriteAshRunRight4,
        Assets.spriteAshRunRight3,
        Assets.spriteAshRunRight2
        };
  }
  
  
  public void setCamera(Camera pCamera)
  {
    oCamera = pCamera;
  }
 
} // end player class
