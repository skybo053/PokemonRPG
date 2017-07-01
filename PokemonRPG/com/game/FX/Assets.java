package com.game.FX;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.Exceptions.AssetLoaderException;

public class Assets 
{
  public static BufferedImage 
  imgAshThrow      = null,
  imgGameFreakLogo = null,
  imgPkmnIntLogo   = null;
  
  
  public static void load() throws AssetLoaderException
  {
    try
    {
      imgAshThrow      = ImageIO.read(new File("Resources/Images/AshThrow.png"));
      imgGameFreakLogo = ImageIO.read(new File("Resources/Images/GameFreakLogo.png"));
      imgPkmnIntLogo   = ImageIO.read(new File("Resources/Images/PkmnIntLogo.png"));
    }
    catch(IOException pException)
    { 
      throw new AssetLoaderException("Assets.load - " + pException.getMessage(), 
                                     pException);
    }
  }
  
  
  public static int getHeight(BufferedImage pImage)
  {
    return pImage.getHeight();
  }
  
  
  public static int getWidth(BufferedImage pImage)
  {
    return pImage.getWidth();
  }
}
