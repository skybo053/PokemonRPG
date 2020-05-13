package com.game.Entities;

import com.game.Main.GamePanel;

public class Camera 
{
  Integer oXOffset = null;
  Integer oYOffset = null;
  
  
  public Camera(Integer pOffsetX, Integer pOffsetY)
  {
    oXOffset = pOffsetX;
    oYOffset = pOffsetY;
  }
  
  
  public void moveCamera(Integer pXAmount, Integer pYAmount)
  {
    oXOffset = oXOffset + pXAmount;
    oYOffset = oYOffset + pYAmount;
  }
  
  
  public void centerOnPlayer(Player pPlayer)
  {
  	oXOffset = (GamePanel.displayWidth / 2) - pPlayer.getXPos(); 
  	oYOffset = (GamePanel.displayHeight / 2) - pPlayer.getYPos();
  }
  
  
  public Integer getXOffset()
  {
    return oXOffset;
  }
  
  
  public void setXOffset(Integer pXOffset)
  {
    oXOffset = pXOffset;
  }
  
  
  public Integer getYOffset()
  {
    return oYOffset;
  }
  
  
  public void setYOffset(Integer pYOffset)
  {
    oYOffset = pYOffset;
  }

}
