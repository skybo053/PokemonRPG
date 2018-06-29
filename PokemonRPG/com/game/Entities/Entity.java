package com.game.Entities;

import java.awt.Graphics;

public abstract class Entity
{
  protected int oXPos   = 0;
  protected int oYPos   = 0;
  protected int oWidth  = 0;
  protected int oHeight = 0;
  
  
  public Entity(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    oXPos   = pXPos;
    oYPos   = pYPos;
    
    oWidth  = pWidth;
    oHeight = pHeight;
  }
  
  
  protected abstract void update();
  protected abstract void draw(Graphics pGraphics);
  
  
  protected int getWidth()
  {
    return oWidth;
  }
  
  
  protected int getHeight()
  {
    return oHeight;
  }
  
  
  protected int getXPos()
  {
    return oXPos;
  }
  
  
  protected int getYPos()
  {
    return oYPos;
  }
  
}
