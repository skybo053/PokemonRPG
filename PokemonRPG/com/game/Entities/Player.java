package com.game.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.FX.Assets;

public class Player extends Creature
{
  private BufferedImage spritePlayerStandForward = null;
  
  public Player(int pXPos, int pYPos, int pWidth, int pHeight)
  {
    super(pXPos, pYPos, pWidth, pHeight);
    spritePlayerStandForward = Assets.spriteAshStandForward;
  }
  
  
  public void update()
  {
    
  }
  
  
  public void draw(Graphics pGraphics)
  {
    pGraphics.drawImage(spritePlayerStandForward, xPos, yPos, width, height, null);
  }
}
