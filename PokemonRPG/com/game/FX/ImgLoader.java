package com.game.FX;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.Exceptions.AssetLoaderException;

public class ImgLoader 
{
  public static BufferedImage loadImage(String pPath) throws AssetLoaderException
  {
    try
    {
      return ImageIO.read(new File(pPath));
    }
    catch(IOException pIOException)
    {
      throw new AssetLoaderException("ImgLoad.loadImage - " + 
                                      pIOException.getMessage(),
                                      pIOException);
    }
  }
}
