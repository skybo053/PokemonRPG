package com.game.States;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState implements State
{
  
  private String  name;
  private int     displayWidth;
  private int     displayHeight;
  private boolean isActive;
  
  public MenuState(String pName, int pDisplayWidth, int pDisplayHeight)
  {
    displayWidth  = pDisplayWidth;
    displayHeight = pDisplayHeight;
    
    isActive      = true;
    name          = pName;
  }
  
  
  public void update() 
  {
    
  }
  
  
  public void draw(Graphics pGraphics) 
  {
    pGraphics.setColor(Color.cyan);
    pGraphics.fillRect(0, 0, displayWidth, displayHeight);
    pGraphics.setColor(Color.black);
    pGraphics.drawString("This will be the menu", 40, 60);
  }
  
  
  public boolean isActive()
  {
    return isActive;
  }
  
  
  public String getName()
  {
    return name;
  }

}
