package com.game.Entities;

import java.awt.Graphics;

public abstract class Creature extends Entity
{
  protected int pHealth = 0;
  protected int pSpeed  = 0;
  protected int pAttack = 0;
  
  public Creature(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    pHealth = 100;
    pSpeed  = 5;
    pAttack = 0;
  }
}
