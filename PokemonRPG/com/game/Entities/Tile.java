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
  private static Map<String, BufferedImage> TILE_IMAGES =
      new HashMap<String, BufferedImage>()
      {{
        put("grass",                   Assets.spriteTileGrass);
        put("dirt",                    Assets.spriteTileDirt);
        put("water",                   Assets.spriteTileWater);
        put("weed",                    Assets.spriteTileWeed);
        put("house_roof_top",          Assets.spriteHouse1_roof_top);
        put("house_roof_bottom",       Assets.spriteHouse1_roof_bottom);
        put("houseTop_left",           Assets.spriteHouse1_houseTop_left);
        put("houseTop_midLeft",        Assets.spriteHouse1_houseTop_midLeft);
        put("houseTop_midRight",       Assets.spriteHouse1_houseTop_midRight);
        put("houseTop_right",          Assets.spriteHouse1_houseTop_right);
        put("houseBottom_left",        Assets.spriteHouse1_houseBottom_left);
        put("houseBottom_midLeft",     Assets.spriteHouse1_houseBottom_midLeft);
        put("houseBottom_midRight",    Assets.spriteHouse1_houseBottom_midRight);
        put("houseBottom_right",       Assets.spriteHouse1_houseBottom_right);
        put("treeBottom_aloneLeft",    Assets.spriteTree_bottomAloneLeft);
        put("treeBottom_aloneRight",   Assets.spriteTree_bottomAloneRight);
        put("treeBottom_overlapLeft",  Assets.spriteTree_bottomOverlapLeft);
        put("treeBottom_overlapRight", Assets.spriteTree_bottomOverlapRight);
        put("treeMiddleLeft",          Assets.spriteTree_middleLeft);
        put("treeMiddleRight",         Assets.spriteTree_middleRight);
        put("treeTop_aloneLeft",       Assets.spriteTree_topAloneLeft);
        put("treeTop_aloneRight",      Assets.spriteTree_topAloneRight);
        
        put("No Image",                Assets.noImage);
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
    oImage      = TILE_IMAGES.get(pImage);
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
