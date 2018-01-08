package com.game.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.game.FX.Assets;

public class Player extends Creature
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
  
  
  public Player(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    playerDirectionQ   = new LinkedHashSet<Integer>();
   
  }
  
  
  public void update()
  {
    System.out.println("Player.update() - direction Queue contains: " + playerDirectionQ);
    
    
    
    switch(getLastPlayerDirection())
   {
   case PLAYER_MOVE_UP:
     yPos += -speed;
     setCurrentPlayerImage(Assets.spriteAshStandBackwards);
     break;
     case PLAYER_MOVE_DOWN:
     yPos += speed;
     setCurrentPlayerImage(Assets.spriteAshStandForward);
     break;
   case PLAYER_MOVE_LEFT:
     xPos += -speed;
     setCurrentPlayerImage(Assets.spriteAshStandLeft);
     break;
   case PLAYER_MOVE_RIGHT:
     xPos += speed;
     setCurrentPlayerImage(Assets.spriteAshStandRight);
     break;
   case PLAYER_STANDING:
     break;
   }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.drawImage(currPlayerImage, xPos, yPos, width, height, null);
  }
  
  
  public void addPlayerDirection(int pDirection)
  {
    playerDirectionQ.add(pDirection);
  }
  
  
  public void removePlayerDirection(int pDirection)
  {
    playerDirectionQ.remove(pDirection);
  }
  
  
  public int getLastPlayerDirection()
  {
    int vDirection = 0;
    
    playerDirectionQIt = playerDirectionQ.iterator();
    
    if(playerDirectionQ.isEmpty())
    {
      return PLAYER_STANDING;
    }
    else
    {
      while(playerDirectionQIt.hasNext())
      {
        vDirection = playerDirectionQIt.next();
      }
      
      return vDirection;
    }
  }
  
  
  public void setCurrentPlayerDirection(int pDirection)
  {
    currPlayerDirection = pDirection;
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
