package com.game.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.game.FX.Assets;
import com.game.TileEvents.TileEvent;

public class Tile extends Entity
{
  private static Map<String, BufferedImage> Tile_Images =
      new HashMap<String, BufferedImage>()
      {{
        put("grass",                Assets.spriteTileGrass);
        put("dirt",                 Assets.spriteTileDirt);
        put("water",                Assets.spriteTileWater);
        put("weed",                 Assets.spriteTileWeed);
        put("houseTop_Left",        Assets.spriteHouse1_houseTop_Left);
        put("houseTop_MidLeft",     Assets.spriteHouse1_houseTop_MidLeft);
        put("houseTop_MidRight",    Assets.spriteHouse1_houseTop_MidRight);
        put("houseTop_Right",       Assets.spriteHouse1_houseTop_Right);
        put("houseTopMid_Left",     Assets.spriteHouse1_houseTopMid_Left);
        put("houseTopMid_MidLeft",  Assets.spriteHouse1_houseTopMid_MidLeft);
        put("houseTopMid_MidRight", Assets.spriteHouse1_houseTopMid_MidRight);
        put("houseTopMid_Right",    Assets.spriteHouse1_houseTopMid_Right);
        put("houseBotMid_Left",     Assets.spriteHouse1_houseBotMid_Left);
        put("houseBotMid_MidLeft",  Assets.spriteHouse1_houseBotMid_MidLeft);
        put("houseBotMid_MidRight", Assets.spriteHouse1_houseBotMid_MidRight);
        put("houseBotMid_Right",    Assets.spriteHouse1_houseBotMid_Right);
        put("houseBot_Left",        Assets.spriteHouse1_houseBot_Left);
        put("houseBot_MidLeft",     Assets.spriteHouse1_houseBot_MidLeft);
        put("houseBot_MidRight",    Assets.spriteHouse1_houseBot_MidRight);
        put("houseBot_Right",       Assets.spriteHouse1_houseBot_Right);
      }};
      
  
  private Integer              oRow         = null;
  private Integer              oCol         = null;
  private BufferedImage        oImage       = null;
  private Boolean              oIsSolid     = null;
  private ArrayList<TileEvent> oTileEvents  = null;

 
  public Tile(
      int                  pRow,
      int                  pCol,
      int                  pXPos, 
      int                  pYPos, 
      int                  pWidth, 
      int                  pHeight, 
      String               pImage, 
      Boolean              pIsSolid,
      ArrayList<TileEvent> pTileEvents)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    
    oRow        = pRow;
    oCol        = pCol;
    oImage      = Tile_Images.get(pImage);
    oIsSolid    = pIsSolid;
    oTileEvents = pTileEvents;
  }

 
  public void update() 
  {
    
  }
  
  
  public void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(oImage, oXPos, oYPos, oWidth, oHeight, null);
  }
  
  
  public Integer getRow()
  {
    return oRow;
  }
  
  
  public Integer getCol()
  {
    return oCol;
  }
  
  
  public Boolean isSolid()
  {
    return oIsSolid;
  }
  
  
  public Boolean hasEvents()
  {
    if(oTileEvents == null || oTileEvents.size() == 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  
  
  public ArrayList<TileEvent> getEvents()
  {
    return oTileEvents;
  }
  
}
