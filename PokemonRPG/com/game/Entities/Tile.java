package com.game.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.game.FX.Assets;
import com.game.TileEvents.TileEvent;

public class Tile extends Entity
{
  private static Map<Integer, BufferedImage> TILE_IMAGES =
      new HashMap<Integer, BufferedImage>()
  {{
    put(0, Assets.spriteTileGrass);
    put(1, Assets.spriteTileDirt);
    put(2, Assets.spriteTileWater);
    put(3, Assets.spriteTileWeed);
    put(4, Assets.spriteHouse1);
    put(5, Assets.spriteHouse2);
    put(6, Assets.spriteHouse3);
    put(7, Assets.spriteHouse4);
    put(8, Assets.spriteHouse5);
    put(9, Assets.spriteHouse6);
    put(10, Assets.spriteHouse7);
    put(11, Assets.spriteHouse8);
    put(12, Assets.spriteHouse9);
    put(13, Assets.spriteHouse10);
    put(14, Assets.spriteHouse11);
    put(15, Assets.spriteHouse12);
    put(16, Assets.spriteHouse13);
    put(17, Assets.spriteHouse14);
    put(18, Assets.spriteHouse15);
    put(19, Assets.spriteHouse16);
  }};
  
  
  private static Map<String, BufferedImage> TILE_STRING_IMAGE =
      new HashMap<String, BufferedImage>()
      {{
        put("grass", Assets.spriteTileGrass);
        put("dirt",  Assets.spriteTileDirt);
        put("water", Assets.spriteTileWater);
        put("weed",  Assets.spriteTileWeed);
      }};
  
  
  private BufferedImage oImage       = null;
  private Boolean       oIsSolid     = null;
  private TileEvent[]   oTileEvents  = null;

 
  public Tile(
      int           pXPos, 
      int           pYPos, 
      int           pWidth, 
      int           pHeight, 
      String        pImage, 
      Boolean       pIsSolid,
      TileEvent[]   pTileEvents)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    
    oImage      = TILE_STRING_IMAGE.get(pImage);
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
}
