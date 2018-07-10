package com.game.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.game.FX.Assets;

public class Tile extends Entity
{
  private static Map<Integer, BufferedImage> TILE_IMAGES =
      new HashMap<Integer, BufferedImage>()
  {{
    put(0, Assets.spriteTileGrass);
    put(1, Assets.spriteTileDirt);
    put(2, Assets.spriteTileWater);
    put(3, Assets.spriteTileWeed);
    put(4, Assets.spriteTileTreeTopLeft);
    put(5, Assets.spriteTileTreeTopRight);
    put(6, Assets.spriteTileTreeBottomLeft);
    put(7, Assets.spriteTileTreeBottomRight);
  }};
  
  private BufferedImage oImage   = null;
  private Boolean       oIsSolid = null;

  public Tile(
      int           pXPos, 
      int           pYPos, 
      int           pWidth, 
      int           pHeight, 
      int           pImageID, 
      Boolean       pIsSolid)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    
    oImage   = TILE_IMAGES.get(pImageID);
    oIsSolid = pIsSolid;
  }

 
  protected void update() 
  {
    
  }

  
  protected void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(oImage, oXPos, oYPos, oWidth, oHeight, null);
  }
}
