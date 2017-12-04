package com.game.Entities;

import java.awt.Graphics;

public abstract class Entity
{
  protected int xPos   = 0;
  protected int yPos   = 0;
  protected int width  = 0;
  protected int height = 0;
  
  public Entity(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    xPos   = pXPos;
    yPos   = pYPos;
    
    width  = pWidth;
    height = pHeight;
  }
  
  protected abstract void update();
  protected abstract void draw(Graphics pGraphics);
}
