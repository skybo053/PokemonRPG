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
    
    System.out.println("Tile created at X: " + oXPos + " Y: " + oYPos + " with ID: " + pImageID);
  }

 
  protected void update() 
  {
    
  }

  
  protected void draw(Graphics pGraphics) 
  {
    pGraphics.drawImage(oImage, oXPos, oYPos, oWidth, oHeight, null);
  }
}
