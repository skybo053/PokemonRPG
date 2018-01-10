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
  private BufferedImage currPlayerImage = null;
  private BufferedImage tempPlayerImage = null;
  
  private Set<Integer>      playerDirectionQ   = null;
  private Iterator<Integer> playerDirectionQIt = null;
  
  public static final int PLAYER_STANDING   = -1;
  public static final int PLAYER_MOVE_UP    = 0;
  public static final int PLAYER_MOVE_DOWN  = 1;
  public static final int PLAYER_MOVE_LEFT  = 2;
  public static final int PLAYER_MOVE_RIGHT = 3;
  
  private int currPlayerDirection;
  private int lastPlayerDirection;
  
  private BufferedImage[] currAnimations      = null;
  private BufferedImage[] ashForwardSprites   = null;
  private BufferedImage[] ashBackwardsSprites = null;
  private BufferedImage[] ashLeftSprites      = null;
  private BufferedImage[] ashRightSprites     = null;
  
  private final int ANIMATION_ARRAY_SIZE = 6;
  private int       animationIndex;
  private int       animationSwitchTime = 150;
  private Timer     timer               = null;
  
  public Player(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    
    playerDirectionQ   = new LinkedHashSet<Integer>();
    timer              = new Timer(animationSwitchTime, this);
    
    setCurrentPlayerDirection(Player.PLAYER_STANDING);
    setCurrentPlayerImage(Assets.spriteAshStandForward);
   
    ashForwardSprites = new BufferedImage[]
        {
        Assets.spriteAshRunForward1,
        Assets.spriteAshRunForward2,
        Assets.spriteAshRunForward3,
        Assets.spriteAshRunForward4,
        Assets.spriteAshRunForward3,
        Assets.spriteAshRunForward2
        };
    
    ashBackwardsSprites = new BufferedImage[]
        {
        Assets.spriteAshRunBackwards1,
        Assets.spriteAshRunBackwards2,
        Assets.spriteAshRunBackwards3,
        Assets.spriteAshRunBackwards4,
        Assets.spriteAshRunBackwards3,
        Assets.spriteAshRunBackwards2
        };
    
    ashLeftSprites = new BufferedImage[]
        {
        Assets.spriteAshRunLeft1,
        Assets.spriteAshRunLeft2,
        Assets.spriteAshRunLeft3,
        Assets.spriteAshRunLeft4,
        Assets.spriteAshRunLeft3,
        Assets.spriteAshRunLeft2
        };
    
    ashRightSprites = new BufferedImage[]
        {
        Assets.spriteAshRunRight1,
        Assets.spriteAshRunRight2,
        Assets.spriteAshRunRight3,
        Assets.spriteAshRunRight4,
        Assets.spriteAshRunRight3,
        Assets.spriteAshRunRight2
        };
  }
  
  
  public void update()
  {
    
    switch(currPlayerDirection = getPlayerDirection())
    {
    case PLAYER_MOVE_UP:
      yPos += -speed;
      break;
      
    case PLAYER_MOVE_DOWN:
     yPos += speed;
     break;
     
   case PLAYER_MOVE_LEFT:
     xPos += -speed;
     break;
     
   case PLAYER_MOVE_RIGHT:
     xPos += speed;
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
      
      switch(lastPlayerDirection)
      {
      case PLAYER_MOVE_UP:
        currPlayerImage = Assets.spriteAshStandBackwards;
        break;
      case PLAYER_MOVE_DOWN:
        currPlayerImage = Assets.spriteAshStandForward;
        break;
      case PLAYER_MOVE_LEFT:
        currPlayerImage = Assets.spriteAshStandLeft;
        break;
      case PLAYER_MOVE_RIGHT:
        currPlayerImage = Assets.spriteAshStandRight;
        break;
      }
      
      pGraphics.drawImage(currPlayerImage, xPos, yPos, width, height, null);
    }
    else
    {
      timer.start();
      
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
      
      pGraphics.drawImage(currAnimations[animationIndex], xPos, yPos, width, height, null);
    
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
      animationIndex = 0;
    }
    
    System.out.println("Player.actionPerformed() - AnimationIndex = " + animationIndex);
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
      
      return (lastPlayerDirection = vDirection);
    }
  }
  
  
  public void setCurrentPlayerDirection(int pDirection)
  {
    currPlayerDirection = pDirection;
    lastPlayerDirection = pDirection;
  }
  
  
  public void setCurrentPlayerImage(BufferedImage pImage)
  {
    currPlayerImage = pImage;
  }
  
  
  public void setPlayerStop()
  {
    currPlayerDirection = PLAYER_STANDING;
  }
  
 
  
}
