package com.game.Entities;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.game.Exceptions.WorldLoaderException;
import com.game.Main.GamePanel;

public class World 
{
  
  private static final String MAP_FILE = "Resources/Maps/map1";
  
  private Tile[][] oMap     = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  private int oNumVisibleTileRows;
  private int oNumVisibleTileCols;
  
  
  public World(int pTileWidth) throws WorldLoaderException
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
  
  
  private void loadMap() throws WorldLoaderException
  {
    BufferedReader vFileReader       = null;
    String         vLine             = null;
    String[]       vLineTokens       = null;
    String[]       vTokenElements    = null;
    int            vRowSize          = 0;
    int            vColSize          = 0;
    int            vTileXPos         = 0;
    int            vTileYPos         = 0;
    int            vCurrProcessedRow = 0;
    
    try
    {
      vFileReader = new BufferedReader(new FileReader(new File(MAP_FILE)));
      
      while((vLine = vFileReader.readLine()) != null)
      {
        vLineTokens = vLine.split("\\s+");
        
        if(vLineTokens[0].equals("#") &&
           vLineTokens.length == 3      )
        {
          vTokenElements = vLineTokens[1].split("=", 2);
          vRowSize       = Integer.parseInt(vTokenElements[1]);
          
          vTokenElements = vLineTokens[2].split("=", 2);
          vColSize       = Integer.parseInt(vTokenElements[1]);
          
          if(vRowSize == 0 || 
             vColSize == 0   )
          {
            throw new WorldLoaderException("World.loadMap - Rows and Columns " +
                "were not configured correctly.");
          }
          
          oMap = new Tile[vRowSize][vColSize];
        }
        else if(vRowSize == 0 || 
                vColSize == 0   )
        {
          throw new WorldLoaderException("World.loadMap - Rows and Columns " +
              "were not configured correctly.");
        }
        else
        {
          if(vCurrProcessedRow < vRowSize)
          {
            for(int vColIndex = 0; vColIndex < vColSize; ++vColIndex)
            {
              oMap[vCurrProcessedRow][vColIndex] = new Tile(
                  vTileXPos,
                  vTileYPos,
                  oTileWidth,
                  oTileHeight, 
                  Integer.parseInt(vLineTokens[vColIndex]),
                  true
                  );
              
              vTileXPos += oTileWidth;
            }
            
            ++vCurrProcessedRow;
            vTileXPos = 0;
            vTileYPos += oTileHeight;
          }
          else
          {
            break;
          }
        }
      }
    }
    catch(FileNotFoundException pException)
    {
      throw new WorldLoaderException("World.loadMap - " + pException.getMessage(),
                                      pException);
    }
    catch(IOException pIOException)
    {
      
    }
    catch(NullPointerException pNullPointerException)
    {
      throw new WorldLoaderException("World.loadMap - " + pNullPointerException.getMessage(),
                                      pNullPointerException);
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
