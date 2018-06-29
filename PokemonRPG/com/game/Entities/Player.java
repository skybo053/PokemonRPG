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

public class Player extends Creature implements ActionListener
{
  private final int ANIMATION_ARRAY_SIZE  = 7;
  private final int ANIMATION_START_INDEX = 1;
  private final int ANIMATION_SWITCH_TIME = 100;
  
  private Set<Integer>      playerDirectionQ   = null;
  private Iterator<Integer> playerDirectionQIt = null;
  
  public static final int PLAYER_STANDING   = -1;
  public static final int PLAYER_MOVE_UP    = 0;
  public static final int PLAYER_MOVE_DOWN  = 1;
  public static final int PLAYER_MOVE_LEFT  = 2;
  public static final int PLAYER_MOVE_RIGHT = 3;
  
  private BufferedImage[] currAnimations      = null;
  private BufferedImage[] ashForwardSprites   = null;
  private BufferedImage[] ashBackwardsSprites = null;
  private BufferedImage[] ashLeftSprites      = null;
  private BufferedImage[] ashRightSprites     = null;
  
  private int   currPlayerDirection = PLAYER_STANDING;
  private int   animationIndex      = ANIMATION_START_INDEX;
  private Timer timer               = null;
  
  
  public Player(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    
    playerDirectionQ   = new LinkedHashSet<Integer>();
    timer              = new Timer(ANIMATION_SWITCH_TIME, this);
   
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
    
    currAnimations = ashForwardSprites;
  }
  
  
  public void update()
  {
    currPlayerDirection = getPlayerDirection();
    
    switch(currPlayerDirection)
    {
    case PLAYER_MOVE_UP:
      oYPos += -speed;
      break;
      
    case PLAYER_MOVE_DOWN:
     oYPos += speed;
     break;
     
   case PLAYER_MOVE_LEFT:
     oXPos += -speed;
     break;
     
   case PLAYER_MOVE_RIGHT:
     oXPos += speed;
     break;
     
   case PLAYER_STANDING:
     break;
   }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    if(currPlayerDirection == PLAYER_STANDING)
    {
      timer.stop();
      animationIndex = ANIMATION_START_INDEX;
      
      pGraphics.drawImage(currAnimations[0], oXPos, oYPos, oWidth, oHeight, null);
    }
    else
    {
      
      if(timer.isRunning() == false)
      {
        timer.start();
      }
      
      switch(currPlayerDirection)
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
      
      pGraphics.drawImage(currAnimations[animationIndex], oXPos, oYPos, oWidth, oHeight, null);
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
    playerDirectionQ.add(pDirection);
  }
  
  
  public void removePlayerDirection(int pDirection)
  {
    playerDirectionQ.remove(pDirection);
  }
  
  
  public int getPlayerDirection()
  {
    int vDirection = 0;
    
    if(playerDirectionQ.isEmpty())
    {
      return PLAYER_STANDING;
    }
    else
    {
      playerDirectionQIt = playerDirectionQ.iterator();
      
      while(playerDirectionQIt.hasNext())
      {
        vDirection = playerDirectionQIt.next();
      }
      
      return vDirection;
    }
  }
  
  
  public int getPlayerWidth()
  {
    return oWidth;
  }
  
  
  public int getPlayerHeight()
  {
    return oHeight;
  }
}
