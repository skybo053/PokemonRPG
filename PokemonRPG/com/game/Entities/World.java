package com.game.Entities;

import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.game.Exceptions.WorldLoaderException;
import com.game.Main.GamePanel;
import com.game.TileEvents.TileEvent;

public class World 
{
  
  private static final String CONFIG_MAP_FILE = "Resources/Maps/json-map.json";
  private static final String IMAGE_MAP_FILE  = "Resources/Maps/image_map.txt";
  
  private Tile[][] oMap     = null;
  
  private int oTileWidth;
  private int oTileHeight;
  
  private int oNumVisibleTileRows;
  private int oNumVisibleTileCols;
  
  private int oTotalMapRows;
  private int oTotalMapCols;
  
  
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
    BufferedWriter vImageIDWriter     = null;
    JSONParser     vParser            = null; 
    JSONObject     vMapObject         = null;
    JSONObject     vDimensions        = null;
    JSONObject     vCurrTileToProcess = null;
    JSONObject     vCurrTileObj       = null;
    JSONArray      vTileDataArray     = null;
    JSONArray      vTileEventsArray   = null;
    
    String      vTileImage  = null;
    String      vImageID    = null;
    Boolean     vIsSolid    = null;
    TileEvent[] vTileEvents = null;
    
    int vRow        = 0;
    int vCol        = 0;
    int vTileXPos   = 0;
    int vTileYPos   = 0;
    int vRowCounter = 0;
    int vColCounter = 0;

    try
    {
      vParser        = new JSONParser();
      vMapObject     = (JSONObject)vParser.parse(new InputStreamReader(new FileInputStream(CONFIG_MAP_FILE)));
      vImageIDWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(IMAGE_MAP_FILE)));
      
      vDimensions = (JSONObject)vMapObject.get("map");
      
      oTotalMapRows = Integer.parseInt(vDimensions.get("rows").toString());
      oTotalMapCols = Integer.parseInt(vDimensions.get("cols").toString());
      
      if(oTotalMapRows == 0 ||
         oTotalMapCols == 0   )
      {
        throw new WorldLoaderException("World.loadMap - Map rows " +
                                       "and columns not configured correctly");
      }
      
      oMap = new Tile[oTotalMapRows][oTotalMapCols];
      
      vTileDataArray = (JSONArray)vMapObject.get("tiles");
      
      for(int vIndex = 0; vIndex < vTileDataArray.size(); ++vIndex)
      {
        vCurrTileToProcess = (JSONObject)vTileDataArray.get(vIndex);
        vCurrTileObj       = (JSONObject)vCurrTileToProcess.get("tile");
        
        vRow       = Integer.parseInt(vCurrTileObj.get("row").toString());
        vCol       = Integer.parseInt(vCurrTileObj.get("col").toString());
        vTileImage = vCurrTileObj.get("image").toString();
        vImageID   = vCurrTileObj.get("imageID").toString();
        vIsSolid   = Boolean.valueOf(vCurrTileObj.get("solid").toString());
        
        vTileEventsArray = (JSONArray)vCurrTileObj.get("events");
        
        for(int vEventsIndex = 0; vEventsIndex < vTileEventsArray.size(); ++vEventsIndex)
        {
          JSONObject vTileEventsObj = (JSONObject)vTileEventsArray.get(vEventsIndex);
          JSONObject vTileEvent     = (JSONObject)vTileEventsObj.get("event");
          String     vEventType     = vTileEvent.get("type").toString();
          
        }
        
        oMap[vRow][vCol] = new Tile(
            vTileXPos,
            vTileYPos,
            oTileWidth,
            oTileHeight,
            vTileImage,
            vIsSolid
            );
        
        
        if(vColCounter + 1 <= oTotalMapCols)
        {
          ++vColCounter;
          
          vTileXPos += oTileWidth;
          
          vImageIDWriter.write(vImageID + "  ");
        }
        else
        {
          vColCounter = 0;
          ++vRowCounter;
          vTileXPos = 0;
          vTileYPos += oTileHeight;
          vImageIDWriter.write(vImageID + '\n');
        }
      }
      
      vImageIDWriter.close();
      
    }
    catch(NumberFormatException pNumberFormatException)
    {
      throw new WorldLoaderException("World.loadMap - " +
                                      pNumberFormatException.getMessage(),
                                      pNumberFormatException);
    }
    catch(ParseException pParseException)
    {
      throw new WorldLoaderException("World.loadMap - " +
                                      pParseException.getMessage(),
                                      pParseException);
    }
    catch(IOException pIOException)
    {
      throw new WorldLoaderException("World.loadMap - " +
                                      pIOException.getMessage(),
                                      pIOException);
    }
  }
}
