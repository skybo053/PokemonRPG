package com.game.Entities;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.game.Main.GamePanel;

public class World 
{
  
  private static final String MAP_FILE = "Resources/Maps/map1";
  
  private Tile[][] oMap     = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  private int oNumVisibleTileRows;
  private int oNumVisibleTileCols;
  
  
  public World(int pTileWidth)
  {
    oTileWidth    = pTileWidth;
    oTileHeight   = pTileWidth;
    
    loadMap();
    
    
    oNumVisibleTileRows = (int)Math.ceil((double) GamePanel.displayHeight / oTileHeight);
    oNumVisibleTileCols = (int)Math.ceil((double) GamePanel.displayWidth / oTileWidth);
    
    System.out.println("visible rows: " + oNumVisibleTileRows +
                       " visible cols: " + oNumVisibleTileCols);
    
    System.out.println("Tile width: " + oTileWidth);
  }
  
  
  public Tile getTileAtPosition(int pRow, int pCol)
  {
    return oMap[pRow][pCol];
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
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    
  }
  
  
  private void loadMap()
  {
    BufferedReader vFileReader = null;
    String         vLine       = null;
    String[]       vLineTokens = null;
    int            vRowSize    = 0;
    int            vColSize    = 0;
    int            vTileXPos   = 0;
    int            vTileYPos   = 0;
    int            vRowIndex   = 0;
    
    try
    {
      vFileReader = new BufferedReader(new FileReader(new File(MAP_FILE)));
      
      while((vLine = vFileReader.readLine()) != null)
      {
        vLineTokens = vLine.split("\\s+");
        
        if(vLineTokens[0].equals("#")) 
        {
          for(int vIndex = 1; vIndex < vLineTokens.length; ++vIndex)
          {
            String[] vTempTokens = vLineTokens[vIndex].split("=", 2);
            
            if(vTempTokens[0].equals("rows"))
            {
              vRowSize = Integer.parseInt(vTempTokens[1]);
            }
            else if(vTempTokens[0].equals("cols"))
            {
              vColSize = Integer.parseInt(vTempTokens[1]);
            }
          }
          
          oMap = new Tile[vRowSize][vColSize];
        }
        else if(vRowIndex < vRowSize)
        {
          for(int vColIndex = 0; vColIndex < oMap[vRowIndex].length; ++vColIndex)
          {
              oMap[vRowIndex][vColIndex] = new Tile(
                  vTileXPos,
                  vTileYPos,
                  oTileWidth,
                  oTileHeight,
                  Integer.parseInt(vLineTokens[vColIndex]),
                  true
                  );
              
              vTileXPos += oTileWidth;
            }
            
            vTileYPos += oTileHeight;
            vTileXPos  = 0;
            
            ++vRowIndex;
          }
        else
        {
          break;
        }
      }
    }
    catch(FileNotFoundException pException)
    {
      
    }
    catch(IOException pIOException)
    {
      
    }
    finally
    {
      try
      {
        vFileReader.close();
      }
      catch(IOException pIOException)
      {
        return;
      }
    }
  }
}