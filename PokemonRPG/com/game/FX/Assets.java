package com.game.FX;

import java.awt.image.BufferedImage;

import com.game.Exceptions.AssetLoaderException;

public class Assets 
{
  public static BufferedImage imgAshThrow = null;
  
  public static void load() throws AssetLoaderException
  {
    imgAshThrow = ImgLoader.loadImage("Resources/Images/AshThrow.png");
  }
}
