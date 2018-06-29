package com.game.Entities;

import java.awt.Graphics;

public abstract class Creature extends Entity
{
  protected int health = 0;
  protected int speed  = 0;
  protected int attack = 0;
  
  public Creature(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    health = 100;
    speed  = 5;
    attack = 0;
  }
  
  
  public void setXPos(int pXPos)
  {
    oXPos += pXPos;
  }
  
  
  public void setYPos(int pYPos)
  {
    oYPos += pYPos;
  }
  
  
  public int getSpeed()
  {
    return speed;
  }
}
