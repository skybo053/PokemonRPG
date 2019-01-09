package com.game.Entities;

public abstract class Creature extends Entity
{
  protected int health = 0;
  protected int speed  = 0;
  protected int attack = 0;
  
  
  public Creature(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    health = 0;
    speed  = 5;
    attack = 0;
  }
  
  
  public Creature(int pWidth, int pHeight)
  {
    super(pWidth, pHeight);
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
  
  
  public void setSpeed(int pSpeed)
  {
    speed = pSpeed;
  }
  
  
  public int getHealth()
  {
    return health;
  }
  
  
  public void setHealth(int pHealth)
  {
    health = pHealth;
  }
}
