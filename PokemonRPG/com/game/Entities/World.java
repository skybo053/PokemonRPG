package com.game.Entities;

import java.awt.Graphics;

import com.game.Main.GamePanel;
import com.game.States.PlayState;

public class World 
{
  private Tile[][] oMap = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  private int oNumVisibleTileRows;
  private int oNumVisibleTileCols;
  
  private Camera oCamera = null;
  
  
  public World(int pTileWidth)
  {
    oTileWidth    = pTileWidth;
    oTileHeight   = pTileWidth;
    
    oNumVisibleTileRows = (int)Math.ceil((double) GamePanel.displayHeight / oTileHeight);
    oNumVisibleTileCols = (int)Math.ceil((double) GamePanel.displayWidth / oTileWidth);
    
    System.out.println("World - screen visible rows: "                 + 
                        oNumVisibleTileRows + " screen visible cols: " + 
                        oNumVisibleTileCols);
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
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    for(int vRowIndex = 0; vRowIndex < oMap.length; ++vRowIndex)
    {
      for(int vColIndex = 0; vColIndex < oMap[vRowIndex].length; ++vColIndex)
      {
        Tile t = oMap[vRowIndex][vColIndex];
        
        t.setXOffset(oCamera.getXOffset());
        t.setYOffset(oCamera.getYOffset());
        
        t.draw(pGraphics);
      }
    }
  }
  
}//end World class
