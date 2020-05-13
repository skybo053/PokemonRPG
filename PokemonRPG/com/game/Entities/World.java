package com.game.Entities;

import java.awt.Graphics;

import com.game.Main.GamePanel;
import com.game.States.PlayState;

public class World 
{
  private Tile[][] oMap    = null;
  
  private Camera   oCamera = null;
  
  private Integer  oStartX = null;
  private Integer  oEndX   = null;
  private Integer  oStartY = null;
  private Integer  oEndY   = null;
  
  
  public World()
  {
    oStartX = 0;
    oEndX   = 0;
    oStartY = 0;
    oEndY   = 0;
  }
  
  
  public Tile getTileAtPosition(int pRow, int pCol)
  {
    return oMap[pRow][pCol];
  }
  
  
  public void setMap(Tile[][] pMap)
  {
    oMap = pMap;
  }
  
  
  public void setCamera(Camera pCamera)
  {
    oCamera = pCamera;
  }
  
  
  public void update()
  {
    int vXOffset = 0;
    int vYOffset = 0;
    
    vXOffset = oCamera.getXOffset();
    vYOffset = oCamera.getYOffset();
    
    //Set draw limits to visible tiles only
    oStartX = Math.abs(Math.min(0, vXOffset/PlayState.PLAYER_WIDTH));
    oEndX   = Math.min(oMap[0].length, oStartX + (GamePanel.displayWidth / PlayState.PLAYER_WIDTH) + 1);
    oStartY = Math.abs(Math.min(0, vYOffset/PlayState.PLAYER_WIDTH));
    oEndY   = Math.min(oMap.length, oStartY + (GamePanel.displayHeight / PlayState.PLAYER_WIDTH) + 1);
    
    //apply X and Y offset positions to visible tiles
    for(int vRowIndex = oStartY; vRowIndex < oEndY; ++vRowIndex)
    {
      for(int vColIndex = oStartX; vColIndex < oEndX; ++vColIndex)
      {
    	  Tile vTile = oMap[vRowIndex][vColIndex];
    	  
    	  vTile.setXOffset(vXOffset);
    	  vTile.setYOffset(vYOffset);
      }
    }
  }
  
  
  public void draw(Graphics pGraphics)
  {
    for(int vRowIndex = oStartY; vRowIndex < oEndY; ++vRowIndex)
    {
      for(int vColIndex = oStartX; vColIndex < oEndX; ++vColIndex)
      {
        oMap[vRowIndex][vColIndex].draw(pGraphics);
      }
    }
  }
  
}//end World class
