package com.game.Entities;

import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.game.Exceptions.InitializeStateException;
import com.game.FX.Assets;
import com.game.Main.GamePanel;
import com.game.TileEvents.TileEvent;

public class World 
{
  private Tile[][] oMap = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  private int oNumVisibleTileRows;
  private int oNumVisibleTileCols;
  
  
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
  
  
  public void update()
  {
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    try
    {
      for(int vRowIndex = 0; vRowIndex < oMap.length; ++vRowIndex)
      {
        for(int vColIndex = 0; vColIndex < oMap[vRowIndex].length; ++vColIndex)
        {
          oMap[vRowIndex][vColIndex].draw(pGraphics);
        }
      }
    }
    catch(NullPointerException e)
    {
      System.out.println("World.draw - " + e.getMessage());
      e.printStackTrace();
    }
  }
  
}//end World class
