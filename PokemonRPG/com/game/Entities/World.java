package com.game.Entities;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class World 
{
  
  private static final String MAP_FILE = "Resources/Maps/map1";
  
  private Tile[][] oTiles = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  
  public World(int pPlayerWidth, int pPlayerHeight)
  {
    oTileWidth  = pPlayerWidth;
    oTileHeight = pPlayerHeight / 2;
    
    loadMap();
    
    System.out.println();
   
  }
  
  
  public void update()
  {
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    try
    {
      for(int vRowIndex = 0; vRowIndex < oTiles.length; ++vRowIndex)
      {
        for(int vColIndex = 0; vColIndex < oTiles[vRowIndex].length; ++vColIndex)
        {
          oTiles[vRowIndex][vColIndex].draw(pGraphics);
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
    int            vRows       = 0;
    int            vCols       = 0;
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
              vRows = Integer.parseInt(vTempTokens[1]);
            }
            else if(vTempTokens[0].equals("cols"))
            {
              vCols = Integer.parseInt(vTempTokens[1]);
            }
          }
          
          oTiles = new Tile[vRows][vCols];
        }
        else if(Character.isDigit(vLineTokens[0].charAt(0)) == true)
        {
          for(int vColIndex = 0; vColIndex < oTiles[vRowIndex].length; ++vColIndex)
          {
              oTiles[vRowIndex][vColIndex] = new Tile(
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
  }

}
